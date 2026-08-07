package racecondition;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {

    private Queue<Integer> fila = new LinkedList<>();
    private static final int TAMANHO_BUFFER = 5;

    public void produzir(int item) {

        if (fila.size() < TAMANHO_BUFFER) {
            fila.add(item);

            System.out.println(Thread.currentThread().getName()
                + " produziu " + item
                + " | tamanho = " + fila.size());
        } else {
            System.out.println("Buffer cheio!");
        }
    }

    public Integer consumir() {
        if (!fila.isEmpty()) {
            Integer item = fila.poll();
            System.out.println(Thread.currentThread().getName()
                + " consumiu " + item
                + " | tamanho = " + fila.size());

            return item;
        }
        System.out.println("Buffer vazio!");
        return null;
    }
}