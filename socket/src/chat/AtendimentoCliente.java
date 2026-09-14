package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class AtendimentoCliente implements Runnable {

    private static final String FIM = "<fim>";
    private final Socket socketCliente;
    private final DistribuidorMensagens observador;
    private final DataInputStream entrada;
    private final DataOutputStream saida;

    public AtendimentoCliente(Socket socketCliente,DistribuidorMensagens observador) throws IOException {
        this.socketCliente = socketCliente;
        this.observador = observador;
        this.entrada = new DataInputStream(socketCliente.getInputStream());
        this.saida = new DataOutputStream(socketCliente.getOutputStream());
    }

    public synchronized void enviar(String mensagem) throws IOException {
        saida.writeUTF(mensagem);
        saida.flush();
    }

    @Override
    public void run() {
        try {
            while (true) {
                String mensagem = entrada.readUTF();
                if (FIM.equalsIgnoreCase(mensagem.trim())) {
                    break;
                }
                if (mensagem.contains("<todos>")) {
                    observador.enviarMensagem(mensagem);
                } else {
                    enviar("O servidor leu: " + mensagem);
                }
            }
        } catch (EOFException e) {
            System.out.println("Cliente desconectado.");
        } catch (IOException e) {
            System.err.println("Erro na comunicação: " + e.getMessage());
        } finally {
            observador.remover(this);
            
            try {
                socketCliente.close();
            } catch (IOException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}