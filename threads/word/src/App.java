public class App {
    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer("");
        
        LeituraTeclado leituraTeclado = new LeituraTeclado(buffer);
        SalvarArquivo salvarArquivo = new SalvarArquivo(buffer);
        VerificarOrtografia verificarOrtografia = new VerificarOrtografia(buffer);

        new Thread(leituraTeclado).start();
        new Thread(salvarArquivo).start();
        new Thread(verificarOrtografia).start();

        System.out.println("Tchau Programa Principal.");
    }
}