package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor implements DistribuidorMensagens {

    private final List<AtendimentoCliente> conexoes = new ArrayList<>();

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        try (ServerSocket servidorSocket = new ServerSocket(2001)) {
            System.out.println("Servidor iniciado. Aguardando conexões...");
            while (true) {
                Socket conexao = servidorSocket.accept();
                AtendimentoCliente atendimento = new AtendimentoCliente(conexao, servidor);
                servidor.adicionar(atendimento);
                new Thread(atendimento).start();
                System.out.println("Nova conexão estabelecida!");
            }
        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
        }
    }

    public synchronized void adicionar(AtendimentoCliente atendimento) {
        conexoes.add(atendimento);
    }

    @Override
    public synchronized void remover(AtendimentoCliente atendimento) {
        conexoes.remove(atendimento);
    }

    @Override
    public synchronized void enviarMensagem(String mensagem) {
        List<AtendimentoCliente> desconectados = new ArrayList<>();
        for (AtendimentoCliente atendimento : conexoes) {
            try {
                atendimento.enviar(mensagem);
            } catch (IOException e) {
                desconectados.add(atendimento);
            }
        }
        conexoes.removeAll(desconectados);
    }
}