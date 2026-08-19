
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SalvarArquivo implements Runnable {

    private StringBuffer buffer;

    private static final String PATH = "C:\\Users\\carlo\\Dowloads\\SAVE.TXT";

    public SalvarArquivo(StringBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                File file = new File(PATH);
                PrintWriter pw = new PrintWriter(file);
                pw.write(buffer.toString());
                pw.close();
                Thread.sleep(10000);
            } catch (FileNotFoundException|InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}