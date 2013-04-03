/**
 * Classe que modela o Escravo
 * @Descrição:
 * Esta classe modela o Escravo Principal do Sistema.
 */

package escravo;

import base.Host;
import base.TipoConexao;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Escravo {
    private ServerSocket                socket;
    private ArrayList<ConexaoEscravo>   listaClientes;
    private String                      nome;
    private Host                        hostEscravo;

    public Escravo(Host hostEscravo) throws Exception {
        // Isto pode sofrer alguma mudaça.
        this.hostEscravo = hostEscravo;
        this.nome = hostEscravo.toString();

        socket = new ServerSocket(hostEscravo.porta);
    }


    /**
     * Retorna informação do host
     */
    public Host getHost() {
        return hostEscravo;
    }

    /**
     * Retorna o nome do escravo como uma string
     */
    public String getNome() {
       return nome;
    }

    public void iniciarServico() {
        System.out.println("Iniciando conexão de controle com o servidor...");

        /**
         * Cria conexão de controle com o servidor...
         */
        try {
            new Thread(new ConexaoEscravo(new Socket(Host.SERVIDOR.ip, Host.SERVIDOR.porta), this, TipoConexao.ESCRAVO)).start();
        }
        catch(Exception ex) {
            System.err.println("Erro ao criar nova Thread de conexão do " + nome + ", inicialização abortada");
            return;
        }

        System.out.println(nome + " inciado com sucesso, aguardando conexões de clientes...");

        /**
         * Loop infinito que recebe as conexão dos clientes e cria as novas Thread's.
         */
        for(;;) {
            try {
                new Thread(new ConexaoEscravo(socket.accept(), this, TipoConexao.CLIENTE)).start();
                System.out.println("Conexão com cliente estabelecida com sucesso.");
            }
            catch(Exception ex) {
                System.err.println("Erro ao criar nova Thread de conexão do " + nome);
            }
        }
    }
}
