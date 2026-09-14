package sequencial;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {

    private static final String FIM = "<fim>";
    public static void main(String[] args) {
        try (var conexao = new Socket("127.0.0.1", 2001); 
             var entrada = new DataInputStream(conexao.getInputStream()); 
             var saida = new DataOutputStream(conexao.getOutputStream()); 
             var teclado = new BufferedReader(new InputStreamReader(System.in))) {
            String mensagem;
            do {
                System.out.print("> ");
                mensagem = teclado.readLine();
                saida.writeUTF(mensagem);
                saida.flush();
                String resposta = entrada.readUTF();
                System.out.println(resposta);                
            } while (!FIM.equalsIgnoreCase(mensagem.trim()));
            System.out.println("Conexão encerrada.");
        } catch (EOFException e) {
            System.out.println("O servidor encerrou a conexão inesperadamente.");
        } catch (IOException e) {
            System.out.println("Erro de comunicação: " + e.getMessage());
        }
    }
}
