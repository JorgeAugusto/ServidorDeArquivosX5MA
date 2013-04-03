/**
 * Classe que modela a Conexão de Controle de um Cliente
 * @Descrição:
 * Esta classe modela a Conexão do Servidor com um Escravo.
 */

package cliente;

import base.Arquivo;
import base.Mensagem;
import base.TipoConexao;
import java.io.*;
import java.net.Socket;
import javax.swing.JOptionPane;

public class ConexaoDadosCliente implements Runnable {
    // Constantes
    private static final String     PASTA_DOWNLOADS = "Downloads";
    public  static final int        TAMANHO_BUFFER  = 1;     // Buffer de 1 KB, quando maior mais rápido...

    private Socket                  socket;
    private ObjectOutputStream      saida;          // para enviar mensagem de UP ou DOWN
    private ObjectInputStream       entrada;
    private Mensagem                mensagemEnviada;
    private JanelaCliente           janelaCliente;
    private Arquivo                 arquivoTrans;   // dados do arquivos a ser transmitido (UP ou DOWN)
    private TipoConexao             tipoConexao;
    private Mensagem.TipoMensagem   tipoMensagem;

    /**
     * Construtor
     */
    public ConexaoDadosCliente(JanelaCliente janelaCliente, Arquivo arquivoTrans, Mensagem.TipoMensagem tipoMensagem) throws Exception {
        this.arquivoTrans   = arquivoTrans;
        this.janelaCliente  = janelaCliente;
        this.tipoConexao    = TipoConexao.CLIENTE;
        this.tipoMensagem   = tipoMensagem;         // se vai ser UPLOAD ou DOWNLOAD
        socket              = new Socket(arquivoTrans.getHost().ip, arquivoTrans.getHost().porta);
        saida               = new ObjectOutputStream(socket.getOutputStream());
        saida.flush();
        entrada             = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * Este método vai rodar em uma nova Thread
     * e ele é responsável por responder as mensagem para o servidor principal
     */
    @Override
    public void run() {
        // JOptionPane.showMessageDialog(janelaCliente, "Entrou na Thread de Dados...");

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
        try {
            mensagemEnviada = new Mensagem(Mensagem.TipoMensagem.DOWNLOAD, arquivoTrans);
            saida.writeObject(mensagemEnviada);
            saida.flush();
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(janelaCliente, "Erro ao solicitar Download...");
        }
    }

    /**
     * Este método realiza o download de uma arquivo... (de fato)
     */
    private void downloadDeArquivo() {
        try {
            FileInputStream     entradaDados        = (FileInputStream) socket.getInputStream();
            File                novoArquivo         = new File(PASTA_DOWNLOADS + "\\" + arquivoTrans.getNome());
            FileOutputStream    saidaNovoArquivo    = new FileOutputStream(novoArquivo);

            byte[] b = new byte[TAMANHO_BUFFER];

            janelaCliente.setProgressBarMax((int) arquivoTrans.getTamanho());
            int i = 1;
            while(entradaDados.read(b) != -1) {
                janelaCliente.setProgessBarValor(i);
                saidaNovoArquivo.write(b);
                i += b.length;
            }

            saidaNovoArquivo.close();   // fecha arquivo
            entradaDados.close();       // fecha stream de entrada
            socket.close();             // fecha socket
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(janelaCliente, "Erro ao Download o arquivo do servidor: " + ex);
        }
    }
}
