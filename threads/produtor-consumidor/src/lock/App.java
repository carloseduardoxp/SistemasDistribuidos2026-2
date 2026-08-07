package lock;

public class App {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Produtor produtor = new Produtor(buffer);
        Consumidor consumidor = new Consumidor(buffer);

        new Thread(produtor,"Thread Produtora").start();
        new Thread(consumidor,"Thread Consumidora").start();

        System.out.println("Que os jogos comecem");
    }
}