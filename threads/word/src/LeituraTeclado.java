import java.util.Scanner;

public class LeituraTeclado implements Runnable {

    private StringBuffer buffer;

    public LeituraTeclado(StringBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) { 
                buffer.append(scanner.nextLine()+"\n");
            }
        }
    }

}