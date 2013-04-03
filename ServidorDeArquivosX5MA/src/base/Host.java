/**
 * Enum com as configurações de conexão.
 */

package base;

import java.io.Serializable;

public enum Host implements Serializable {
    SERVIDOR ("localhost",   2000),
    ESCRAVO_1("localhost",   2001),
    ESCRAVO_2("localhost",   2002);

    Host(String ip, int porta) {
        this.ip     = ip;
        this.porta  = porta;
    }

    public String   ip;
    public int      porta;
}

