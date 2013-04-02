/**
 * Classe que modela o Escravo1 Principal do Sistema
 * @Descrição:
 * Esta classe modela o Escravo1 Principal do Sistema.
 */

package escravo;

import base.Host;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Escravo1 {
    private int                         idConexao;    // isso talvez saia...
    private ServerSocket                socket;
    private ArrayList<ConexaoEscravo>   listaClientes;
    private ArrayList<ConexaoEscravo>   listaEscravos;

    /**
     * Construtor
     */
    public Escravo1() throws Exception {
        idConexao = 1;
        socket    = new ServerSocket(Host.ESCRAVO_1.porta);
    }


    /**
     * Gera um novo ID de conexão
     */
    public int getIdConexao() {
        return idConexao++;
    }

    /**
     * Método principal do Escravo1
     */
    public static void main(String[] args) throws Exception {

        Escravo1 escravo = new Escravo1();

        System.out.println("Iniciando conexão de controle com o servidor...");

        /**
         * Cria conexão de controle com o servidor...
         */
        try {
            new Thread(new ConexaoEscravo(new Socket(Host.SERVIDOR.ip, Host.SERVIDOR.porta), escravo)).start();
        }
        catch(Exception ex) {
            System.err.println("Erro ao criar nova Thread de conexão do Escravo1, inicialização abortada");
            return;
        }

        System.out.println("Escravo 1 Inciado com sucesso, aguardando conexões de clientes...");

        /**
         * Loop infinito que recebe as conexão dos clientes e cria as novas Thread's
         */
        for(;;) {
            try {
                new Thread(new ConexaoEscravo(escravo.socket.accept(), escravo)).start();
                System.out.println("Conexão com cliente estabelecida com sucesso.");
            }
            catch(Exception ex) {
                System.err.println("Erro ao criar nova Thread de conexão do Escravo1");
            }
        }
    }
}
