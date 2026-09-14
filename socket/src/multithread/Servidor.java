package multithread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try (ServerSocket servidorSocket = new ServerSocket(2001)) {
            System.out.println("Servidor iniciado. Aguardando conexões...");
            while (true) {
                Socket conexao = servidorSocket.accept();
                System.out.println("Cliente conectado!");
                Thread thread = new Thread(new AtendimentoCliente(conexao));
                thread.start();
            }
        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
        }
    }
}