import java.util.Random;

public class Corrida implements Runnable {

    private Random random = new Random();

    private String nome;

    public Corrida(String nome) {
        this.nome = nome;
    }

    @Override
    public void run() {
        for (int i = 1; i < 20; i++) {
            System.out.println("Piloto "+nome+" completou "+i+" voltas");
            try {
                Thread.sleep(random.nextInt(2001));
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Piloto "+nome+" terminou a corrida");
    }
    
}
