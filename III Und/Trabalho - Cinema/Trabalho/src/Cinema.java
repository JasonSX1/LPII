import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cinema implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<Filme> filmes;
    private final List<Sessao> sessoes;
    private final List<Sala> salas;
    private final List<Ingresso> ingressos;

    public Cinema() {
        this.filmes = new ArrayList<>();
        this.sessoes = new ArrayList<>();
        this.salas = new ArrayList<>();
        this.ingressos = new ArrayList<>();
    }

    public void adicionarFilme(Filme filme) {
        this.filmes.add(filme);
    }

    public void adicionarSessao(Sessao sessao) {
        this.sessoes.add(sessao);
    }

    public void adicionarSala(Sala sala) {
        if (!salas.contains(sala)) {
            salas.add(sala);
        }
    }

    public void removerSessao(Sessao sessao) {
        this.sessoes.remove(sessao);
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public List<Sessao> getSessoes() {
        return sessoes;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public Sala getSala(int numeroSala) {
        for (Sala sala : salas) {
            if (sala.getNumero() == numeroSala) {
                return sala;
            }
        }
        return null;
    }

    public Sala buscarSalaPorId(int numeroSala) {
        return Sala.getSala(numeroSala);
    }

    public boolean verificarIntervaloMinimo(Sala sala, LocalTime horario) {
        for (LocalTime h : sala.getHorarios()) {
            if (Math.abs(h.toSecondOfDay() - horario.toSecondOfDay()) < 1200) { // 1200 segundos = 20 minutos
                return false;
            }
        }
        return true;
    }

    public void adicionarIngresso(Ingresso ingresso) throws Exception {
        if (!isPoltronaDisponivel(ingresso.getSessao(), ingresso.getNumeroPoltrona())) {
            throw new Exception("A poltrona " + ingresso.getNumeroPoltrona() + " já está vendida.");
        }
        ingresso.getSessao().venderIngresso(ingresso.getFilme(), ingresso.isMeiaEntrada());
        ingressos.add(ingresso);
    }

    public boolean isPoltronaDisponivel(Sessao sessao, int numeroPoltrona) {
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getSessao().equals(sessao) && ingresso.getNumeroPoltrona() == numeroPoltrona) {
                return false;
            }
        }
        return true;
    }

    public Ingresso buscarIngresso(Sessao sessao, int numeroPoltrona) {
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getSessao().equals(sessao) && ingresso.getNumeroPoltrona() == numeroPoltrona) {
                return ingresso;
            }
        }
        return null;
    }

    public void removerIngresso(Ingresso ingresso) {
        ingressos.remove(ingresso);
        ingresso.getSessao().cancelarIngresso(ingresso.getFilme(),
                ingresso.getSessao().getHorario());
        // Adicionar lógica de persistência se necessário (ex: banco de dados)
    }

    public int calcularIngressosVendidos(Sessao sessao) {
        
        return sessao.getIngressosVendidos();
    }

    public double calcularTaxaOcupacao(Sessao sessao) {
        int vendidos = calcularIngressosVendidos(sessao);
        int capacidade = sessao.getSala().getCapacidade();
        return (double) vendidos / capacidade * 100;
    }

    public Filme buscarFilmePorTitulo(String titulo) {
        for (Filme filme : filmes) {
            if (filme.getTitulo().equalsIgnoreCase(titulo)) {
                return filme;
            }
        }
        return null;
    }

    public Sessao buscarSessaoPorFilme(String titulo) {
        for (Sessao sessao : sessoes) {
                if (sessao.getFilme().getTitulo().equalsIgnoreCase(titulo)) {
                    return sessao;
            }
        }
        return null;
    }

    public double calcularFaturamento() {
        double total = 0;
        Map<String, Double> ingressosVendidosPorTipo = new HashMap<>();
        for (Sessao sessao : sessoes) {
            ingressosVendidosPorTipo = sessao.getIngressosVendidosPorTipo();
        }
        for(Map.Entry<String, Double> entry : ingressosVendidosPorTipo.entrySet()) {
            total += entry.getValue();
            
            // Your code here
        }
            
        
        return total;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }
}
