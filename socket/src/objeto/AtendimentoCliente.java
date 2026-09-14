package objeto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AtendimentoCliente implements Runnable {

    private final Socket socketCliente;

    public AtendimentoCliente(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }

    @Override
    public void run() {
        try (var saida = new ObjectOutputStream(socketCliente.getOutputStream()); 
             var entrada = new ObjectInputStream(socketCliente.getInputStream())) {

            Object objeto = entrada.readObject();

            if (objeto instanceof Pedido pedido) {
                System.out.println("Pedido recebido: " + pedido);

                saida.writeObject("Pedido recebido com sucesso! ");
                saida.flush();
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao receber objeto: " + e.getMessage());
        }
    }

}
