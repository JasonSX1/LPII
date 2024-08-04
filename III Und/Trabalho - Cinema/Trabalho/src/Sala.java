import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Sala implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final Map<Integer, Sala> salas = new HashMap<>();

    private final int numero;
    private final String nome;
    private final int capacidade;

    private Sala(int numero, String nome, int capacidade) {
        this.numero = numero;
        this.nome = nome;
        this.capacidade = capacidade;
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

    public static Sala getSala(int numero) {
        return salas.get(numero);
    }

    @Override
    public String toString() {
        return String.format("Sala %d: %s (Capacidade: %d)", numero, nome, capacidade);
    }
}
