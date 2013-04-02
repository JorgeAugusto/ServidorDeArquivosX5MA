/**
 * Classe que modela a informação de uma conexao (principal ou escravo)
 * @Descrição:
 * Esta classe modela as informações de conexão de um host servidor
 * que pode ser tando o servidor principal como um dos vários servidores
 * escravos
 */

package base;

import java.io.Serializable;

public class OLD_Host implements Serializable {
    private String  ip;
    private int     porta;

    /**
     * Construtor 1
     */
    public OLD_Host(int porta) {
        this("", porta);
    }


    /**
     * Contrutor 2
     */
    public OLD_Host(String ip, int porta) {
        this.ip     = ip;
        this.porta = porta;
    }

    //<editor-fold defaultstate="collapsed" desc="SOMENTE PARA TESTES - DEVE SER RETIRADO MAIS TARDE">
    // Somente para Teste
//    @Override
//    public String toString() {
//        return "[" + nome + ", " + ip + ", " + Integer.toString(porta) + "]";
//    }
    //</editor-fold>

    public String getIp() {
        return ip;
    }

    public int getPorta() {
        return porta;
    }
}
