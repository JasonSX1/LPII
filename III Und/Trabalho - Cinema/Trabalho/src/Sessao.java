import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


public class Sessao implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Filme filme;
    private final LocalTime horario;

    private final Sala sala;
    private final boolean em3D;
    private final double valorEntradaBase;
    private int ingressosVendidos;
    private final Map<String, Double> ingressosVendidosPorTipo = new HashMap<>();

    public Sessao(Filme filme, Sala sala, LocalTime horario, boolean em3D, double valorEntradaBase) {
        this.filme = filme;
        this.horario = horario;
        this.sala = sala;
        this.em3D = em3D;
        this.valorEntradaBase = valorEntradaBase;
        this.ingressosVendidos = 0;
    }

    public Filme getFilme() {
        return filme ;
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

    public Map<String, Double> getIngressosVendidosPorTipo() {
        return ingressosVendidosPorTipo;
    }

    public void venderIngresso(Filme filme, boolean meiaEntrada) {
        Double valorEntrada = valorEntradaBase;
        if (em3D) {
            valorEntrada *= 1.25;
            if (meiaEntrada) {
                valorEntrada *= 0.5;
                ingressosVendidosPorTipo.put("3D meia entrada", valorEntrada);
            } else {
                ingressosVendidosPorTipo.put("3D meia entrada", valorEntrada);
            }
        }else{
            if (meiaEntrada) {
                valorEntrada *= 0.5;
                ingressosVendidosPorTipo.put("meia entrada",valorEntrada );
            } else {
                ingressosVendidosPorTipo.put("inteira", valorEntrada );
            }
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
        sb.append(String.format("Filme: %s, Horário: %s\n", filme.getTitulo(), horario.format(formatter)));
        return sb.toString() + String.format("Sala: %s, Tipo de Áudio: %s, 3D: %s, Valor Entrada Base: R$ %.2f, Ingressos Vendidos: %d", 
                sala.getNumero(), em3D ? "Sim" : "Não", valorEntradaBase, ingressosVendidos);
    }
}
