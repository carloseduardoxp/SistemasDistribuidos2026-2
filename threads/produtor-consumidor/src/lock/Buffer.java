package lock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private final Queue<Integer> fila = new LinkedList<>();
    private static final int TAMANHO_BUFFER = 5;
    private final Lock lock = new ReentrantLock();
    private final Condition bufferCheio = lock.newCondition();
    private final Condition bufferVazio = lock.newCondition();

    public void produzir(int item) throws InterruptedException {
        lock.lock(); 
        try {
            while (fila.size() == TAMANHO_BUFFER) {
                System.out.println("Buffer cheio, aguardando consumidor");
                bufferCheio.await(); 
            }
            fila.add(item);
            System.out.println(Thread.currentThread().getName()
                    + " produziu " + item
                    + " | tamanho = " + fila.size());

            bufferVazio.signal(); 

        } finally {
            lock.unlock();
        }
    }

    public int consumir() throws InterruptedException {
        lock.lock(); 
        try {
            while (fila.isEmpty()) {
                System.out.println("Buffer vazio, aguardando produtor");
                bufferVazio.await(); 
            }
            int item = fila.poll();
            System.out.println(Thread.currentThread().getName()
                    + " consumiu " + item
                    + " | tamanho = " + fila.size());
            
            bufferCheio.signal();

            return item;
        } finally {
            lock.unlock(); 
        }
    }
}