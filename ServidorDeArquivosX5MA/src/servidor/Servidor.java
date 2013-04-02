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
    private ServerSocket                socket;
    private ArrayList<ConexaoServidor>  listaClientes;
    private ArrayList<ConexaoServidor>  listaEscravos;

    /**
     * Construtor
     */
    public Servidor() throws Exception {
        socket = new ServerSocket(Host.SERVIDOR.porta);
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
