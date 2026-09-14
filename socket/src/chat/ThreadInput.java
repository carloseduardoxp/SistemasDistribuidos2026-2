package chat;

import java.io.DataInputStream;
import java.io.IOException;

public class ThreadInput implements Runnable {

    private final DataInputStream entrada;

    public ThreadInput(DataInputStream entrada) {
        this.entrada = entrada;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String mensagem = entrada.readUTF();
                System.out.println("\n" + mensagem);
                System.out.print("> ");
            }
        } catch (IOException e) {
            System.out.println("\nConexão encerrada.");
        }
    }
}