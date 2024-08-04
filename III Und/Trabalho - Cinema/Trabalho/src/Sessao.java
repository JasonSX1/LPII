import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Sessao implements Serializable {
    private Filme filme;
    private Sala sala;
    private LocalDateTime horario;
    private boolean em3D;
    private double valorEntrada;
    private int ingressosVendidos;
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public Sessao(Filme filme, Sala sala, String horario, boolean em3D, double valorEntrada) {
        this.filme = filme;
        this.sala = sala;
        this.horario = LocalDateTime.parse(horario, FORMATTER);
        this.em3D = em3D;
        this.valorEntrada = valorEntrada;
        this.ingressosVendidos = 0;
    }

    public Filme getFilme() {
        return filme;
    }

    public Sala getSala() {
        return sala;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public boolean isEm3D() {
        return em3D;
    }

    public double getValorEntrada() {
        return valorEntrada;
    }

    public int getIngressosVendidos() {
        return ingressosVendidos;
    }

    public void setIngressosVendidos(int ingressosVendidos) {
        this.ingressosVendidos = ingressosVendidos;
    }

    public int getDuracao() {
        return filme.getDuracao();
    }

    public String getTipoProducao() {
        return filme.getTipoProducao();
    }

    public String getTipoAudio() {
        return filme.getTipoAudio();
    }

    @Override
    public String toString() {
        return String.format("Filme: %s, Sala: %d, Horário: %s, 3D: %s, Valor: R$ %.2f, Ingressos Vendidos: %d",
                filme.getTitulo(), sala.getNumero(), horario.format(FORMATTER), em3D ? "Sim" : "Não",
                valorEntrada, ingressosVendidos);
    }
}
