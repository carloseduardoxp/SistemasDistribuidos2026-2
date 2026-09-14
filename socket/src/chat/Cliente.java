package chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {

    private static final String FIM = "<fim>";

    public static void main(String[] args) {

        try (Socket conexao = new Socket("127.0.0.1", 2001);
            DataInputStream entrada = new DataInputStream(conexao.getInputStream());
            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            Thread threadRecebimento = new Thread(new ThreadInput(entrada));
            threadRecebimento.start();

            while (true) {
                System.out.print("> ");
                String mensagem = teclado.readLine();

                saida.writeUTF(mensagem);
                saida.flush();

                if (FIM.equalsIgnoreCase(mensagem.trim())) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println(
                    "Erro na comunicação: " + e.getMessage());
        }
    }
}