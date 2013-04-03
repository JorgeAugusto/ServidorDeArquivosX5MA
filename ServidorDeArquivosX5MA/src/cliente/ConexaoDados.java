/**
 * Classe que modela a Conexão de Controle de um Cliente
 * @Descrição:
 * Esta classe modela a Conexão do Servidor com um Escravo.
 */

package cliente;

import base.Arquivo;
import base.Host;
import base.Mensagem;
import base.TipoConexao;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ConexaoDados implements Runnable {
    private Socket                  socket;
    private ObjectOutputStream      saida;          // para enviar mensagem de UP ou DOWN
    private DataInputStream         entradaDados;
    private DataOutputStream        saidaDados;
    private Mensagem                mensagemEnviada;
    private JanelaCliente           janelaCliente;
    private Arquivo                 arquivoTrans;   // dados do arquivos a ser transmitido (UP ou DOWN)
    private TipoConexao             tipoConexao;
    private Mensagem.TipoMensagem   tipoMensagem;

    /**
     * Construtor
     */
    public ConexaoDados(JanelaCliente janelaCliente, Arquivo arquivoTrans, Mensagem.TipoMensagem tipoMensagem) throws Exception {
        this.arquivoTrans   = arquivoTrans;
        this.janelaCliente  = janelaCliente;
        this.tipoConexao    = TipoConexao.CLIENTE;
        this.tipoMensagem   = tipoMensagem;         // se vai ser UPLOAD ou DOWNLOAD
        socket              = new Socket(arquivoTrans.getHost().ip, arquivoTrans.getHost().porta);
        saida               = new ObjectOutputStream(socket.getOutputStream());
        saida.flush();
    }

    /**
     * Este método vai rodar em uma nova Thread
     * e ele é responsável por responder as mensagem para o servidor principal
     */
    @Override
    public void run() {
        JOptionPane.showMessageDialog(janelaCliente, "Entrou na Thread de Dados...");

        if(tipoMensagem == Mensagem.TipoMensagem.UPLOAD) {
            processarUpload();
        }
        else if(tipoMensagem == Mensagem.TipoMensagem.DOWNLOAD) {
            processarDownload();
        }
    }

    /**
     * Este método processa um Upload de arquivo...
     */
    private void processarUpload() {
        solicitarUpload();
        UploadDeArquivo();
    }

    /**
     * Este método envia uma solicitação de Upload de arquivo...
     */
    private void solicitarUpload() {

    }

    /**
     * Este método realiza o download de uma arquivo... (de fato)
     */
    private void UploadDeArquivo() {

    }

    /**
     * Este método processa um Download de arquivo...
     */
    private void processarDownload() {
        solicitarDownload();
        downloadDeArquivo();
    }

    /**
     * Este método envia uma solicitação de Download de arquivo...
     */
    private void solicitarDownload(){

    }


    /**
     * Este método realiza o download de uma arquivo... (de fato)
     */
    private void downloadDeArquivo() {

    }
}
