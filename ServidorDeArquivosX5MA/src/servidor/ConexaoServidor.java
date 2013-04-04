/**
 * Classe que modela a Conexão com um cliente ou co
 * @Descrição:
 * Esta classe modela a Conexão do Servidor com um Escravo.
 */

package servidor;

import base.Arquivo;
import base.Mensagem;
import base.TipoConexao;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ConexaoServidor implements Runnable {
    private Socket                  socket;
    private ObjectInputStream       entrada;
    private ObjectOutputStream      saida;
    private Servidor                servidor;
    private Mensagem                mensagemRecebida;
    private Mensagem                mensagemEnviada;
    private ArrayList<Arquivo>      listaArquivos;
    private TipoConexao             tipoConexao;

    public ConexaoServidor(Socket socket, Servidor servidor) throws Exception {
        this.socket     = socket;
        this.servidor   = servidor;
        listaArquivos   = new ArrayList<Arquivo>();

        // limpa cabeçalho...
        saida       = new ObjectOutputStream(socket.getOutputStream());
        saida.flush();
        entrada     = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        /**
         * Isto é possível pois a primeira ação de uma nova conexão é enviar
         * uma mensagem idenfiticando o tipoConexao de host
         */
        processarIdentificacao();

        for(;;) {
            // Se o socke esta fechado então terminar Thread.
            if(socket.isClosed()) return;

            try {
                mensagemRecebida = (Mensagem) entrada.readObject();
                switch(tipoConexao) {
                    case CLIENTE:
                        processarMensagensCliente();
                    break;

                    case ESCRAVO:
                        processarMensagensEscravos();
                    break;
                }
            }
            catch(Exception ex) {
                System.err.println("Erro ao ler mensagem...");
            }
        }
    }

    /**
     * Este método processa as mensagens enviadas pelos clientes...
     */
    public void processarMensagensCliente() {
        switch(mensagemRecebida.getTipoMensagem()) {
            case LISTA_ARQUIVOS:
                enviarListaDeArquivos();
            break;

            case UPLOAD:

            break;

            case DOWNLOAD:

            break;
        }
    }

    /**
     * Este método processa as mensagens enviadas pelos escravos...
     */
    public void processarMensagensEscravos() {
        switch(mensagemRecebida.getTipoMensagem()) {
            case LISTA_ARQUIVOS:
                processarListaArquivoDeEscravo();
            break;
        }
    }

    /**
     * Este método processa a mensagem de identificação
     */
    public void processarIdentificacao() {
        try {
            mensagemRecebida    = (Mensagem) entrada.readObject();                      // lê a mensagem
            tipoConexao         = (TipoConexao) mensagemRecebida.getInfoMensagem();     // pega a informação

            if(tipoConexao == TipoConexao.ESCRAVO) {
                servidor.getListaEscravos().add(this);
            }
            else if(tipoConexao == TipoConexao.CLIENTE) {
                servidor.getListaClientes().add(this);
            }
        }
        catch(Exception ex) {
            System.err.println("Erro ao processar mensagem de identificação");
            return;
        }

        System.out.println("Mensagem de Identificação processada com sucesso: " + tipoConexao.toString());
    }

    /**
     * Este método processa a mensagemRecebida contendo uma lista de arquivos de um escravo
     */
    private void processarListaArquivoDeEscravo() {
        listaArquivos = (ArrayList<Arquivo>) mensagemRecebida.getInfoMensagem();
        servidor.atualizarListaArquivos();

        System.out.println("Lista de arquivos recebida e processada com sucesso.");

        // Depois que processar a lista de arquivos recebidos do servidor, envia a mesma para todos os clientes
        enviarBroadCast();
    }

    /**
     * Este método envia uma mensagem contendo a lista de arquivos que estão no servidor para um cliente
     */
    private void enviarListaDeArquivos() {
        try {
            // Monta a mensagem
            mensagemEnviada = new Mensagem(Mensagem.TipoMensagem.LISTA_ARQUIVOS, servidor.getListaArquivos());

            // envia a mensagem com a listagem dos arquivos
            saida.writeObject(mensagemEnviada);
            saida.flush();
        }
        catch(Exception ex) {
            System.err.println("Erro ao enviar lista de arquivos para o cliente");
            return;
        }

        System.out.println("Lista de arquivos enviada para o cliente com sucesso.");
    }

    /**
     * Este método envia uma mensagem em Broadcast, ou seja, para todos os
     * Servidores Escravos conectados
     */
    private void enviarBroadCast() {
        if(servidor.getListaClientes().size() <= 0) return;

        try{
            for(ConexaoServidor conCliente : servidor.getListaClientes()) {
                conCliente.enviarListaDeArquivos();
            }
        }
        catch(Exception ex) {
            System.err.println("Erro ao enviar lista de arquivos em broadcast para clientes.");
        }
    }

    /**
     * Retorna lista de arquivos deste escravo.
     */
    public ArrayList<Arquivo> getListaArquivo() {
        return listaArquivos;
    }
}
