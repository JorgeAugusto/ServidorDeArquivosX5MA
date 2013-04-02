/**
 * Classe que modela o Servidor Principal do Sistema
 * @Descrição:
 * Esta classe modela o Servidor Principal do Sistema.
 */

package servidor;

import base.Arquivo;
import base.Host;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Servidor {
    private ServerSocket                socket;
    private ArrayList<ConexaoServidor>  listaClientes;
    private ArrayList<ConexaoServidor>  listaEscravos;
    private ArrayList<Arquivo>          listaArquivos;

    /**
     * Construtor
     */
    public Servidor() throws Exception {
        socket = new ServerSocket(Host.SERVIDOR.porta);

        listaClientes = new ArrayList<ConexaoServidor>();
        listaEscravos = new ArrayList<ConexaoServidor>();
        listaArquivos = new ArrayList<Arquivo>();
    }


    /**
     * Este método atualiza a listagem dos arquivo no servidor, com base em todos os servidores escravos (2)
     */
    public synchronized void atualizarListaArquivos() {
        listaArquivos.clear();

        // Para cada escravo da lista de escravos, pega todos os arquivos do mesmo...
        for(ConexaoServidor escravo : listaEscravos) {
            listaArquivos.addAll(escravo.getListaArquivo());
        }

        // Depois de atualizar a lista de arquivos, mostra a mesma.
        mostarListaArquivos();
    }

    /**
     * Este método é somente para os testes...
     */

    public synchronized void mostarListaArquivos() {
        System.out.println("--- LISTAGEM DE ARQUIVOS ---");
        for(Arquivo arquivo : listaArquivos) {
            System.out.println(arquivo.getNome());
        }
    }

    /**
     * Retorna a lista de escravos...
     */
    public ArrayList<ConexaoServidor> getListaEscravos() {
         return listaEscravos;
    }

    /**
     * Retorna a lista de clientes...
     */
    public ArrayList<ConexaoServidor> getListaClientes() {
         return listaClientes;
    }

    /**
     * Método principal do Servidor
     */
    public static void main(String[] args) throws Exception {
        Servidor servidor = new Servidor();

        /**
         * Loop infinito que recebe as conexão e cria as novas Thread's
         */
        System.out.println("Servidor Inciado com sucesso, aguardando conexões...");

        for(;;) {
            try {
                new Thread(new ConexaoServidor(servidor.socket.accept(), servidor)).start();
                System.out.println("Conexão estabelecida com sucesso.");
            }
            catch(Exception ex) {
                System.err.println("Erro ao criar nova Thread de conexão do servidor");
            }
        }
    }
}
