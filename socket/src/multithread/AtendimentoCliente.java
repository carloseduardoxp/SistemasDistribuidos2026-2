package multithread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class AtendimentoCliente implements Runnable {
    
    private static final String FIM = "<fim>";
    private final Socket socketCliente;

    public AtendimentoCliente(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }

    @Override
    public void run() {
        try (socketCliente;
             var entrada = new DataInputStream(socketCliente.getInputStream());
             var saida = new DataOutputStream(socketCliente.getOutputStream())) {
            while (true) {
                String mensagem = entrada.readUTF();
                if (FIM.equalsIgnoreCase(mensagem.trim())) {
                    break;
                }
                saida.writeUTF("O servidor leu: " + mensagem);
                saida.flush();
            }

        } catch (EOFException e) {
            System.out.println("Cliente desconectado.");
        } catch (IOException e) {
            System.err.println("Erro no atendimento ao cliente: "+ e.getMessage());
        }
    }
}