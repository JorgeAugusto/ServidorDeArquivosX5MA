/**
 * Classe que modela o Servidor Principal do Sistema
 * @Descrição:
 * Esta classe modela o Servidor Principal do Sistema.
 */

package servidor;

import base.OLD_Host;
import base.Host;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Servidor {
    private int                  idConexao;     // isso talvez saia...
    private ServerSocket         socket;
    private ArrayList<Conexao>   listaClientes;
    private ArrayList<Conexao>   listaEscravos;

    /**
     * Construtor
     */
    public Servidor() throws Exception {
        idConexao         = 1;
        socket            = new ServerSocket(Host.SERVIDOR.porta);
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
        Servidor servidor       = new Servidor();

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
