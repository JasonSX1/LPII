import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Cinema implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Filme> filmes;
    private List<Sessao> sessoes;
    private List<Sala> salas;
    private List<Ingresso> ingressos;

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

    public void adicionarIngresso(Ingresso ingresso) {
        ingresso.getSessao().venderIngresso(ingresso.getFilme(), ingresso.isMeiaEntrada());
        // Adicionar lógica de persistência se necessário (ex: banco de dados)
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
        ingresso.getSessao().cancelarIngresso(ingresso.getFilme(),
                ingresso.getSessao().getHorario());
        // Adicionar lógica de persistência se necessário (ex: banco de dados)
    }

    public int calcularIngressosVendidos(Sessao sessao) {
        int count = 0;
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getSessao().equals(sessao)) {
                count++;
            }
        }
        return count;
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
            for (Filme filme : sessao.getFilmes()) {
                if (filme.getTitulo().equalsIgnoreCase(titulo)) {
                    return sessao;
                }
            }
        }
        return null;
    }

    public double calcularFaturamento() {
        double total = 0;
        for (Ingresso ingresso : ingressos) {
            total += ingresso.calcularPreco();
        }
        return total;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }
}
