package lock;

import java.util.Random;

public class Consumidor implements Runnable {

    private static final int CONSUMIDOR_SLEEP = 5000;

    private Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) { 
            try {
                System.out.println("Consumidor tentando consumir");
                Integer item = buffer.consumir();
                if (item != null) {
                    System.out.println("Consumindo item "+item);
                }                
                Thread.sleep(new Random().nextInt(CONSUMIDOR_SLEEP));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }    
    }


    
}