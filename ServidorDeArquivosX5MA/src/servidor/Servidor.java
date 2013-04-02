/**
 * Classe que modela o Servidor Principal do Sistema
 * @Descrição:
 * Esta classe modela o Servidor Principal do Sistema.
 */

package servidor;

import base.Host;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Servidor {
    private int               idConexao;    // isso talvez saia...
    private ServerSocket      socket;
    private ArrayList<Host>   listaClientes;
    private ArrayList<Host>   listaEscravos;
    private Host              host;

    /**
     * Construtor, recebe um objeto host que contém a porta
     */
    public Servidor(Host host) throws Exception {
        idConexao = 1;
        this.host = host;
        socket    = new ServerSocket(host.getPorta());
    }


    /**
     * Gera um novo ID de conexão
     */
    public int getIdConexao() {
        return idConexao++;
    }

    /**
     * Método principal do Servidor
     */
    public static void main(String[] args) throws Exception {
        Host     host     = new Host(2000);
        Servidor servidor = new Servidor(host);

        /**
         * Loop infinito que recebe as conexão e cria as novas Thread's
         */
        for(;;) {
            try {
                new Thread(new Conexao(servidor.socket.accept(), servidor)).start();
            }
            catch(Exception ex) {
                System.err.println("Erro ao criar nova Thread de conexão");
            }
        }
    }
}
