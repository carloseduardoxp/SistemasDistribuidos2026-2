import java.util.LinkedList;
import java.util.Queue;

public class Buffer {

    private Queue<Integer> fila = new LinkedList<>();
    private static final Integer TAMANHO_BUFFER = 5;

    public synchronized void produzir(int item) throws InterruptedException {
        while (fila.size() == TAMANHO_BUFFER) { 
            System.out.println("Buffer cheio, aguardando consumidor");
            wait();
        }
        fila.add(item);
        System.out.println(Thread.currentThread().getName()
                + " produziu " + item
                + " | tamanho = " + fila.size());

        if (fila.size() == 1) {
            notifyAll();
        }
    }

    public synchronized int consumir() throws InterruptedException {
        while (fila.isEmpty()) {
            System.out.println("Buffer vazio, aguardando produtor");
            wait();
        }
        int item = fila.poll();
		
		System.out.println(Thread.currentThread().getName()
                + " consumiu " + item
                + " | tamanho = " + fila.size());
        if (fila.size() == TAMANHO_BUFFER - 1) {
            notifyAll();
        }
        return item;
    }

} 