import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cinema implements Serializable {
    private List<Sala> salas;
    private List<Sessao> sessoes;

    public Cinema() {
        this.salas = new ArrayList<>();
        this.sessoes = new ArrayList<>();
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public List<Sessao> getSessoes() {
        return sessoes;
    }

    public Sala adicionarSala(int numero, int capacidade, String nome) {
        // Verificar se já existe uma sala com o mesmo número
        for (Sala sala : salas) {
            if (sala.getNumero() == numero) {
                return sala; // Retornar a sala existente
            }
        }

        // Criar uma nova sala e adicionar à lista
        Sala novaSala = new Sala(numero, capacidade, nome);
        salas.add(novaSala);
        return novaSala;
    }

    public void adicionarSessao(Sessao sessao) {
        // Verificar conflitos de horário com outras sessões na mesma sala
        for (Sessao s : sessoes) {
            if (s.getSala().getNumero() == sessao.getSala().getNumero()) {
                if (s.getHorario().isBefore(sessao.getHorario().plusMinutes(20)) &&
                    s.getHorario().plusMinutes(s.getDuracao()).isAfter(sessao.getHorario())) {
                    System.out.println("Conflito de horários. Não é possível adicionar esta sessão.");
                    return;
                }
            }
        }

        sessoes.add(sessao);
        System.out.println("Sessão adicionada com sucesso.");
    }

    public void removerSessao(Sessao sessao) {
        sessoes.remove(sessao);
        System.out.println("Sessão removida com sucesso.");
    }

    public List<Sessao> getSessoesPorSala(int numeroSala) {
        List<Sessao> sessoesPorSala = new ArrayList<>();
        for (Sessao sessao : sessoes) {
            if (sessao.getSala().getNumero() == numeroSala) {
                sessoesPorSala.add(sessao);
            }
        }
        return sessoesPorSala;
    }

    public List<Sessao> getSessoesPorFilme(String tituloFilme) {
        List<Sessao> sessoesPorFilme = new ArrayList<>();
        for (Sessao sessao : sessoes) {
            if (sessao.getFilme().getTitulo().equalsIgnoreCase(tituloFilme)) {
                sessoesPorFilme.add(sessao);
            }
        }
        return sessoesPorFilme;
    }

    public LocalDateTime getProximoHorarioDisponivel(Sala sala, int duracaoFilme) {
        LocalDateTime ultimoHorario = LocalDateTime.MIN;

        for (Sessao sessao : sessoes) {
            if (sessao.getSala().getNumero() == sala.getNumero()) {
                LocalDateTime fimSessao = sessao.getHorario().plusMinutes(sessao.getDuracao());
                if (fimSessao.isAfter(ultimoHorario)) {
                    ultimoHorario = fimSessao;
                }
            }
        }

        return ultimoHorario.equals(LocalDateTime.MIN) ? LocalDateTime.now() : ultimoHorario.plusMinutes(20);
    }
}
