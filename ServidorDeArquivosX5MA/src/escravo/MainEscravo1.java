package escravo;

import base.Host;

public class MainEscravo1 {
    public static void main(String[] args) throws Exception {
        Escravo escravo1 = new Escravo(Host.ESCRAVO_1);
        escravo1.iniciarServico();
    }
}
