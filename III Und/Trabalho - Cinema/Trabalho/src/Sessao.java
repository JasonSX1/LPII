import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Sessao implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Filme> filmes;
    private LocalTime horario;
    private Sala sala;
    private boolean em3D;
    private double valorEntradaBase;
    private int ingressosVendidos;

    public Sessao(Sala sala, LocalTime horario, boolean em3D, double valorEntradaBase) {
        this.filmes = new ArrayList<>();
        this.horario = horario;
        this.sala = sala;
        this.em3D = em3D;
        this.valorEntradaBase = valorEntradaBase;
        this.ingressosVendidos = 0;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public Sala getSala() {
        return sala;
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

    public boolean adicionarFilme(Filme filme) {
        if (sala.adicionarHorario(horario)) {
            this.filmes.add(filme);
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
        for (Filme filme : filmes) {
            sb.append(String.format("Filme: %s, Horário: %s\n", filme.getTitulo(), horario.format(formatter)));
        }
        return sb.toString() + String.format("Sala: %s, Tipo de Áudio: %s, 3D: %s, Valor Entrada Base: R$ %.2f, Ingressos Vendidos: %d", 
                sala.getNumero(), em3D ? "Sim" : "Não", valorEntradaBase, ingressosVendidos);
    }
}
