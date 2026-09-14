package objeto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {
        try (Socket conexao = new Socket("127.0.0.1", 2001);
             ObjectOutputStream saida = new ObjectOutputStream(conexao.getOutputStream());
             ObjectInputStream entrada = new ObjectInputStream(conexao.getInputStream());
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Conectado ao servidor.");
            while (true) {
                System.out.print("Digite o CPF ou pressione Enter para sair: ");
                String cpf = teclado.readLine();
                if (cpf == null || cpf.isBlank()) {
                    break;
                }
                System.out.print("Digite o valor: ");
                String valorDigitado = teclado.readLine();
                try {
                    double valor = Double.parseDouble(
                            valorDigitado.replace(",", "."));
                    Pedido pedido = new Pedido(cpf, valor);
                    saida.writeObject(pedido);
                    saida.flush();
                    Object resposta = entrada.readObject();
                    System.out.println("Servidor: " + resposta);
                } catch (NumberFormatException e) {
                    System.out.println(
                            "Valor inválido. Digite um número válido.");
                }
            }
            System.out.println("Conexão encerrada.");
        } catch (ClassNotFoundException e) {
            System.err.println("Não foi possível interpretar a resposta do servidor.");
        } catch (IOException e) {
            System.err.println("Erro de comunicação: " + e.getMessage());
        }
    }
}