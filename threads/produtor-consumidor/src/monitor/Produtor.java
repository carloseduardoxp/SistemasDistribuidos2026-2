package monitor;

import java.util.Random;

public class Produtor implements Runnable {

    private Buffer buffer;
    private Random random = new Random();
    private static final int LIMITE_PRODUTOR = 1000;
    private static final int PRODUTOR_SLEEP = 5000;

    public Produtor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int item = random.nextInt(LIMITE_PRODUTOR);
                System.out.println("Produtor produziu "+item);
                buffer.produzir(item);
                System.out.println("Produtor entregou "+item+" no buffer");
                Thread.sleep(random.nextInt(PRODUTOR_SLEEP));
            } catch(InterruptedException e) {
                System.out.println(e);
            }
        }
    }
    
}