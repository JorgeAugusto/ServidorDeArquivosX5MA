/**
 * Classe que modela uma requisição
 * @Descrição:
 */

package base;

import java.io.Serializable;

public class Mensagem implements Serializable {
    // Enum Interna
    public enum TipoMensagem {
        IDENTIFICACAO, LISTA_ARQUIVOS, UPLOAD, DOWNLOAD;
    }

    private TipoMensagem    tipoRequisicao;
    private Object          infoRequisicao;

    public  Mensagem(TipoMensagem  tipoRequisicao, Object infoRequisicao) {
        set(tipoRequisicao, infoRequisicao);
    }

    public final void set(TipoMensagem  tipoRequisicao, Object infoRequisicao) {
        this.tipoRequisicao = tipoRequisicao;
        this.infoRequisicao = infoRequisicao;
    }

    public TipoMensagem getTipoMensagem() {
        return tipoRequisicao;
    }

    public Object getInfoMensagem() {
        return infoRequisicao;
    }
}
