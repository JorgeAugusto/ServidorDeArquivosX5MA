package escravo;

import base.Host;

public class MainEscravo2 {
    public static void main(String[] args) throws Exception {
        Escravo escravo2 = new Escravo(Host.ESCRAVO_2);
        escravo2.iniciarServico();
    }
}
