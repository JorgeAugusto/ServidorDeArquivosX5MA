/**
 * Classe que modela o Servidor Principal do Sistema
 * @Descrição:
 * Esta classe modela o Servidor Principal do Sistema.
 */

package servidor;

public class Servidor {
    public int   idConexao = 1;


    // Métodos Estáticos
    public int getIdConexao() {
        return idConexao++;
    }


    public static void main(String[] args) {

    }
}
