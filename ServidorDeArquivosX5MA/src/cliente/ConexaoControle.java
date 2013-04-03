/**
 * Classe que modela a Conexão de Controle de um Cliente
 * @Descrição:
 * Esta classe modela a Conexão do Servidor com um Escravo.
 */

package cliente;

import base.Arquivo;
import base.Host;
import base.Mensagem;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ConexaoControle implements Runnable {
    private Socket                  socket;
    private ObjectInputStream       entrada;
    private ObjectOutputStream      saida;
    private Mensagem                mensagemRecebida;
    private Mensagem                mensagemEnviada;
    private JanelaCliente           janelaCliente;
    private ArrayList<Arquivo>      listaArquivos;

    /**
     * Construtor
     */
    public ConexaoControle(JanelaCliente janelaCliente) throws Exception {
        this.janelaCliente  = janelaCliente;
        socket              = new Socket(Host.SERVIDOR.ip, Host.SERVIDOR.porta);
        saida               = new ObjectOutputStream(socket.getOutputStream());
        saida.flush();
        entrada             = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * Retorna a lista de arquivos para atualizar na janela
     */
    public ArrayList<Arquivo> getListaArquivos() {
        return listaArquivos;
    }

    /**
     * Este método vai rodar em uma nova Thread
     * e ele é responsável por responder as mensagem para o servidor principal
     */
    @Override
    public void run() {

        // Envia mensagem solicitando lista de arquivos...
        solicitarListaArquivos();

        for(;;) {
            // Se o socke esta fechado então terminar Thread.
            if(socket.isClosed()) return;

            try {
                mensagemRecebida = (Mensagem) entrada.readObject();

                switch(mensagemRecebida.getTipoMensagem()) {
                    case LISTA_ARQUIVOS:
                        processarListaArquivos();
                    break;
                }
            }
            catch(Exception ex) {
                System.err.println("Erro ao processamer mensagem do servido");
            }
        }
    }

    // Métodos privados
    /**
     * Este método envia ao servidor uma solicitação para que mesmo
     * envie uma lista contento os nomes dos arquivos que estão disponíveis
     */
    private void solicitarListaArquivos() {
        try {
            mensagemEnviada = new Mensagem(Mensagem.TipoMensagem.LISTA_ARQUIVOS, null);

            saida.writeObject(mensagemEnviada);
            saida.flush();
        }
        catch(Exception ex) {
            janelaCliente.escreverNaBarraStatus("Erro enviando requisiação de lista de arquivos para servidor");
        }
    }

    /**
     * Este método processa a mensagem de recebimento de uma lista de arquivos do servidor
     */
    private void processarListaArquivos() {
        listaArquivos = (ArrayList<Arquivo>) mensagemRecebida.getInfoMensagem();
        janelaCliente.atualizarTabelaArquivos();
    }
}
