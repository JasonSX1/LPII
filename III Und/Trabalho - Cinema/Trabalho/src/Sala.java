import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Sala implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final Map<Integer, Sala> salas = new HashMap<>();

    private final int numero;
    private final String nome;
    private final int capacidade;
    private final Set<LocalTime> horarios; // Lista de horários para verificar intervalos

    private Sala(int numero, String nome, int capacidade) {
        this.numero = numero;
        this.nome = nome;
        this.capacidade = capacidade;
        this.horarios = new HashSet<>();
    }

    public static Sala criarSala(int numero, String nome, int capacidade) {
        if (salas.containsKey(numero)) {
            return salas.get(numero);
        } else {
            Sala sala = new Sala(numero, nome, capacidade);
            salas.put(numero, sala);
            return sala;
        }
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public int getCapacidade() {
        return capacidade;
    }
    
    public Set<LocalTime> getHorarios() {
        return horarios;
    }

    public static Sala getSala(int numero) {
        return salas.get(numero);
    }

    public boolean adicionarHorario(LocalTime horario) {
        for (LocalTime h : horarios) {
            if (Math.abs(h.toSecondOfDay() - horario.toSecondOfDay()) < 1200) { // 1200 segundos = 20 minutos
                return false; // Intervalo menor que 20 minutos
            }
        }
        horarios.add(horario);
        return true;
    }

    public void removerHorario(LocalTime horario) {
        horarios.remove(horario);
    }

    @Override
    public String toString() {
        return String.format("Sala %d: %s (Capacidade: %d)", numero, nome, capacidade);
    }
}
