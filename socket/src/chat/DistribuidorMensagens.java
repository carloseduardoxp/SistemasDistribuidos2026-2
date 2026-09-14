package chat;

import java.io.IOException;

public interface DistribuidorMensagens {

    void enviarMensagem(String mensagem) throws IOException;
    void remover(AtendimentoCliente atendimento);
    
}
