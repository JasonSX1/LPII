import java.io.Serializable;

public class Ingresso implements Serializable {
    private Sessao sessao;
    private int numeroPoltrona;
    private boolean meiaEntrada;

    public Ingresso(Sessao sessao, int numeroPoltrona, boolean meiaEntrada) {
        this.sessao = sessao;
        this.numeroPoltrona = numeroPoltrona;
        this.meiaEntrada = meiaEntrada;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public int getNumeroPoltrona() {
        return numeroPoltrona;
    }

    public boolean isMeiaEntrada() {
        return meiaEntrada;
    }
}
