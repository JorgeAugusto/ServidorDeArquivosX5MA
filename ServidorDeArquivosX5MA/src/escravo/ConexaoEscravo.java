/**
 * Classe que modela a Conexão de um Escravo
 * @Descrição:
 * Esta classe modela a Conexão do Escravo com um Escravo.
 */

package escravo;

import base.Arquivo;
import base.Mensagem;
import base.TipoConexao;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ConexaoEscravo implements Runnable {
    private Socket                  socket;
    private ObjectInputStream       entrada;
    private ObjectOutputStream      saida;
    private Escravo                 escravo;
    private Mensagem                mensagemRecebida;
    private Mensagem                mensagemEnviada;
    private ArrayList<Arquivo>      listaArquivos;
    private TipoConexao             tipoConexao;

    /**
     * Construtor.
     */
    public ConexaoEscravo(Socket socket, Escravo escravo, TipoConexao tipo) throws Exception {
        this.socket         = socket;
        this.escravo        = escravo;
        this.tipoConexao    = tipo;
        listaArquivos       = new ArrayList<Arquivo>();

        // limpa cabeçalho...
        saida       = new ObjectOutputStream(socket.getOutputStream());
        saida.flush();
        entrada     = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        // Se for uma conexão com o servidor então envia identificação
        if(tipoConexao == TipoConexao.ESCRAVO) {
            enviarIdentificacao();
        }

        for(;;) {
            // Se o socke esta fechado então terminar Thread.
            if(socket.isClosed()) return;

            try {
                mensagemRecebida = (Mensagem) entrada.readObject();
                switch(tipoConexao) {
                    case CLIENTE:
                        processarMensagensClientes();
                    break;

                    case SERVIDOR:
                        processarMensagensServidor();
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
    public void processarMensagensClientes() {
        switch(mensagemRecebida.getTipoMensagem()) {
            case LISTA_ARQUIVOS:
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
    public void processarMensagensServidor() {
        switch(mensagemRecebida.getTipoMensagem()) {
            case LISTA_ARQUIVOS:
            break;

            case UPLOAD:

            break;

            case DOWNLOAD:

            break;
        }
    }

    /**
     * Este método envia uma mensagem informando o tipoConexao de conexão desta Thread
     */
    public void enviarIdentificacao() {
        try {
            mensagemEnviada = new Mensagem(Mensagem.TipoMensagem.IDENTIFICACAO, tipoConexao);

            // envia listagem de arquivos...
            saida.writeObject(mensagemEnviada);
            saida.flush();
        }
        catch(Exception ex) {
            System.err.println("Erro ao enviar mensagem de identificação");
            return;
        }

        System.out.println("Identificação enviada com sucesso.");
    }


    /**
     * Este método envia uma mensagem contendo a lista de arquivos que estão no escravo
     */
    private void enviarListaDeArquivos() {
        try {
            mensagemEnviada = new Mensagem(Mensagem.TipoMensagem.LISTA_ARQUIVOS, null);

            // envia listagem de arquivos...
            saida.writeObject(mensagemEnviada);
            saida.flush();
        }
        catch(Exception ex) {
            System.err.println("Erro ao enviar lista de arquivos para o servidor");
        }
    }


    /**
     * Este método cria a lista de arquivos a ser enviada para o servidor e
     * a ser mostrada na tabela de arquivos na JanelaEscravo
     */
    public void montarListaArquivos() {
        try {
            // Neste ponto a pasta onde ficam os arquivos compartilhados tem o mesmo nome do escravo
            File    pasta       = new File(escravo.getNome());

            // Monta a lista com os nomes de todos os arquivos...
            listaArquivos.clear();
            for(File arquivo : pasta.listFiles()) {
                if(arquivo.isFile()) {
                    Arquivo arq = new Arquivo(arquivo.getName(), escravo.getHost(), arquivo.length());
                    listaArquivos.add(arq);
                }
            }
        }
        catch(Exception ex) {
            System.err.println("Erro ao montar listagem dos arquivos do " + escravo.getNome());
        }
    }

    /**
     * Este método processa a mensagem de identificação
     */
    public void processarIdentificacao() {

    }

    /**
     * Este método processa a mensagemRecebida de recebimento de uma lista de arquivos
     */
    private synchronized void processarListaArquivo() {
//        ArrayList<InfoArquivo> listaArquivosEscravo = (ArrayList<InfoArquivo>) mensagemRecebida.getInfoMensagem();
//        ArrayList<InfoArquivo> listaArquivosTemp = new ArrayList<InfoArquivo>();
//
//        for(InfoArquivo arquivo : listaArquivosEscravo) {
//            listaArquivosTemp.add(
//            new InfoArquivo(arquivo.getNome(), new InfoServidor(getNome(), getIP(), arquivo.getServEscravo().getPorta()), arquivo.getTamanho() ));
//        }
//
//        listaArquivos = listaArquivosTemp;
//        servidor.getGerenteConexaoEscravos().processaListaArquivos();
//        janelaServidor.adicionarHistorico("Processsando lista de arquivos de " + getNome() , EstadoSistema.OK);
    }

    /**
     * Este método envia uma mensagem em Broadcast, ou seja, para todos os
     * Servidores Escravos conectados
     */
    private synchronized boolean enviarBroadCast() {
//        janelaServidor.adicionarHistorico("Enviando solicitação de lista de arquivos em Broadcast...", EstadoSistema.PROCESSANDO);
//        if(!servidor.getGerenteConexaoEscravos().temEscravoConectado()) {
//            janelaServidor.adicionarHistorico("Broadcast falhou não há Servidores Escravos conectados", EstadoSistema.ERRO);
//            return false;
//        }
//
//        try{
//            ArrayList<Conexao> listaEscravos = servidor.getGerenteConexaoEscravos().getListaEscravos();
//
//            for(ConexaoEscravo conEscravo : listaEscravos) {
//                // Se esta desconectado passa para o próximo
//                if(conEscravo.getEstado() == EstadoEscravo.DESCONECTADO) continue;
//
//                conEscravo.solicitarListaArquivos();
//            }
//        }
//        catch(Exception ex) {
//            janelaServidor.adicionarHistorico("Enviando Broadcast", EstadoSistema.ERRO);
//        }
//
//        janelaServidor.adicionarHistorico("Enviando solicitação de lista de arquivos em Broadcast", EstadoSistema.OK);

        return true;
    }

    /**
     * Retorna lista de arquivos deste escravo...
     */
    public ArrayList<Arquivo> getListaArquivo() {
        return listaArquivos;
    }
}
