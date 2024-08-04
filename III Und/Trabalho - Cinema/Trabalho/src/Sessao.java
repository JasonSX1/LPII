import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Sessao implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Filme> filmes;
    private List<LocalTime> horarios;
    private Sala sala;
    private String tipoAudio;
    private boolean em3D;
    private double valorEntradaBase;
    private int ingressosVendidos;

    public Sessao(Sala sala, String tipoAudio, boolean em3D, double valorEntradaBase) {
        this.filmes = new ArrayList<>();
        this.horarios = new ArrayList<>();
        this.sala = sala;
        this.tipoAudio = tipoAudio;
        this.em3D = em3D;
        this.valorEntradaBase = valorEntradaBase;
        this.ingressosVendidos = 0;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public List<LocalTime> getHorarios() {
        return horarios;
    }

    public Sala getSala() {
        return sala;
    }

    public String getTipoAudio() {
        return tipoAudio;
    }

    public boolean isEm3D() {
        return em3D;
    }

    public double getValorEntradaBase() {
        return valorEntradaBase;
    }

    public int getIngressosVendidos() {
        return ingressosVendidos;
    }

    public boolean adicionarFilme(Filme filme, LocalTime horario) {
        if (sala.adicionarHorario(horario)) {
            this.filmes.add(filme);
            this.horarios.add(horario);
            return true;
        }
        return false;
    }

    public void venderIngresso(Filme filme, boolean meiaEntrada) {
        double valorEntrada = valorEntradaBase;
        if (em3D) {
            valorEntrada *= 1.25;
        }
        if (meiaEntrada) {
            valorEntrada *= 0.5;
        }
        this.ingressosVendidos++;
        // Lógica para registrar venda (por exemplo, salvar em banco de dados)
    }

    public void cancelarIngresso(Filme filme, LocalTime horario) {
        if (ingressosVendidos > 0) {
            this.ingressosVendidos--;
            sala.removerHorario(horario);
            // Lógica para cancelar venda (por exemplo, atualizar banco de dados)
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < filmes.size(); i++) {
            sb.append(String.format("Filme: %s, Horário: %s\n", filmes.get(i).getTitulo(), horarios.get(i).format(formatter)));
        }
        return sb.toString() + String.format("Sala: %s, Tipo de Áudio: %s, 3D: %s, Valor Entrada Base: R$ %.2f, Ingressos Vendidos: %d", 
                sala.getNome(), tipoAudio, em3D ? "Sim" : "Não", valorEntradaBase, ingressosVendidos);
    }
}
