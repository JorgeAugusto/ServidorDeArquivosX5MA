/**
 * Classe que modela a Conexão de um Escravo
 * @Descrição:
 * Esta classe modela a Conexão do Escravo com um Escravo.
 */

package escravo;

import base.Arquivo;
import base.Mensagem;
import base.TipoConexao;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
        /**
         * Se está conexão foi criada com o servidor então está conexão se "comportará"
         * como escravo, desta forma envia essa identificação para o servidor
         * e depois envia a lista arquivos disponíveis para download
         */
        if(tipoConexao == TipoConexao.ESCRAVO) {
            enviarIdentificacao();
            enviarListaDeArquivos();
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
                enviarArquivo();
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

            // envia mensagem
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
        // Monta a lista de arquivos disponíveis neste escravo
        montarListaArquivos();

        try {
            // Monta a mensagem
            mensagemEnviada = new Mensagem(Mensagem.TipoMensagem.LISTA_ARQUIVOS, listaArquivos);

            // envia a mensagem com a listagem dos arquivos
            saida.writeObject(mensagemEnviada);
            saida.flush();
        }
        catch(Exception ex) {
            System.err.println("Erro ao enviar lista de arquivos para o servidor");
            return;
        }

        System.out.println("Lista de arquivos enviada com sucesso.");
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
            System.err.println("Erro ao montar listagem dos arquivos");
        }
    }

    /**
     * Este método envia uma arquivo.
     */
    private void enviarArquivo() {
        System.out.println("Iniciando transmissão de arquivo para cliente...");

        try {
            Arquivo             arquivoTrans        = (Arquivo) mensagemRecebida.getInfoMensagem();
            // Neste ponto o nome da basta é o mesmo nome do escravo
            File                arquivoDados        = new File(escravo.getNome() + "\\" + arquivoTrans.getNome());
            FileInputStream     entradaArquivoDados = new FileInputStream(arquivoDados);
            FileOutputStream    saidaDadosCliente   = (FileOutputStream) socket.getOutputStream();

            byte[] b = new byte[1];

            while (entradaArquivoDados.read(b) != -1) {
                saidaDadosCliente.write(b);
            }
            //saidaDadosCliente.flush();       // força despejo de algum dado restante
            //saidaDadosCliente.close();       // fecha stream de saída
            //entradaArquivoDados.close();     // fecha arquivo de entrada
            // socket.close();                  // fecha o socket
        }
        catch (Exception ex) {
            System.err.println("Erro ao enviar dados para o cliente.");
        }

        System.out.println("Fim transmissão de arquivo para cliente...");
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
