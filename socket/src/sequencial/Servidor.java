package sequencial;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private static final String FIM = "<fim>";

    public static void main(String[] args) throws IOException {
        try (var servidor = new ServerSocket(2001)) {
            while (true) {
                System.out.println("Esperando conexão...");
                try (Socket conexao = servidor.accept();
                    var entrada = new DataInputStream(conexao.getInputStream());
                    var saida = new DataOutputStream(conexao.getOutputStream())) {
                    System.out.println("Cliente conectado!");
                    String mensagem;
                    do {
                        mensagem = entrada.readUTF();
                        String resposta = "Mensagem recebida: " + mensagem;
                        System.out.println(resposta);
                        saida.writeUTF(resposta);
                    } while (!FIM.equalsIgnoreCase(mensagem.trim()));       
                    System.out.println("Conexão encerrada.");                
                } catch (EOFException e) {
                    System.out.println("Cliente desconectado.");
                }
            }
        }
    }
}