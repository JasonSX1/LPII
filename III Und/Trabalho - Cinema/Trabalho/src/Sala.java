import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Sala implements Serializable {

    private static final Map<Integer, Sala> salas = new HashMap<>();

    private final int numero;
    private final int capacidade;
    private final Set<LocalTime> horarios; // Lista de horários para verificar intervalos

    private Sala(int numero, int capacidade) {
        this.numero = numero;
        this.capacidade = capacidade;
        this.horarios = new HashSet<>();
    }

    public static Sala criarSala(int numero, int capacidade) {
        if (salas.containsKey(numero)) {
            return salas.get(numero);
        } else {
            Sala sala = new Sala(numero, capacidade);
            salas.put(numero, sala);
            return sala;
        }
    }

    public int getNumero() {
        return numero;
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

    public boolean adicionarHorario(Filme filme, LocalTime horario) {
        for (LocalTime h : this.horarios) {
            // Calcular o horário de término do filme existente
            LocalTime fimExistente = h.plusMinutes(filme.getDuracao() + 20); // duração do filme + 20 minutos
    
            // Verificar se o novo horário inicia antes do término do filme existente + 20 minutos
            if (horario.isBefore(fimExistente)) {
                return false; // Conflito de horário
            }
        }
        this.horarios.add(horario);
        return true;
    }
    

    public void removerHorario(LocalTime horario) {
        horarios.remove(horario);
    }

    @Override
    public String toString() {
        return String.format("Sala %d: %s lugares", numero, capacidade);
    }
}
