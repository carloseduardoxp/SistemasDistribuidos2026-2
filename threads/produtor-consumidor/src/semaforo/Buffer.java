package semaforo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Buffer {

    private final Queue<Integer> fila = new LinkedList<>();
    private static final int TAMANHO_BUFFER = 5;
    private final Semaphore empty = new Semaphore(TAMANHO_BUFFER);
    private final Semaphore full = new Semaphore(0);
    private final Semaphore mutex = new Semaphore(1);

    public void produzir(int item) throws InterruptedException {
        empty.acquire();
        mutex.acquire();
        try {
            fila.add(item);
            System.out.println(Thread.currentThread().getName()
                    + " produziu " + item
                    + " | tamanho = " + fila.size());
        } finally {
            mutex.release(); 
        }
        full.release();
    }

    public int consumir() throws InterruptedException {
        int item;
        full.acquire();        
        mutex.acquire();
        try {
            item = fila.poll();
            System.out.println(Thread.currentThread().getName()
                    + " consumiu " + item
                    + " | tamanho = " + fila.size());
        } finally {
            mutex.release();
        }
        empty.release();        
        return item;
    }
}