import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class GerenciamentoCinema {
    private Cinema cinema;
    private Scanner scanner;

    public GerenciamentoCinema() {
        this.cinema = new Cinema();
        this.scanner = new Scanner(System.in);
    }

    public void carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cinema.dat"))) {
            cinema = (Cinema) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cinema.dat"))) {
            oos.writeObject(cinema);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exibirMenu() {
        while (true) {
            System.out.println("1. Adicionar Sessão");
            System.out.println("2. Remover Sessão");
            System.out.println("3. Vender Ingresso");
            System.out.println("4. Cancelar Ingresso");
            System.out.println("5. Consultar Programação");
            System.out.println("6. Consultar Disponibilidade de Poltronas");
            System.out.println("7. Consultar Taxa de Ocupação");
            System.out.println("8. Consultar Faturamento");
            System.out.println("9. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarSessao();
                    break;
                case 2:
                    removerSessao();
                    break;
                case 3:
                    venderIngresso();
                    break;
                case 4:
                    cancelarIngresso();
                    break;
                case 5:
                    consultarProgramacao();
                    break;
                case 6:
                    consultarDisponibilidadePoltronas();
                    break;
                case 7:
                    consultarTaxaOcupacao();
                    break;
                case 8:
                    consultarFaturamento();
                    break;
                case 9:
                    salvarDados();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void adicionarSessao() {
        System.out.println("Título do filme:");
        String titulo = scanner.nextLine();
        System.out.println("Duração (em minutos):");
        int duracao = scanner.nextInt();
        scanner.nextLine();

        // Seleção de tipo de produção
        System.out.println("Tipo de produção: 1. Nacional 2. Estrangeira");
        int opcaoProducao = scanner.nextInt();
        scanner.nextLine();
        String tipoProducao = opcaoProducao == 1 ? "Nacional" : "Estrangeira";

        // Seleção de tipo de áudio
        System.out.println("Tipo de áudio: 1. Original 2. Original com legenda 3. Dublado");
        int opcaoAudio = scanner.nextInt();
        scanner.nextLine();
        String tipoAudio;
        switch(opcaoAudio) {
            case 1:
                tipoAudio = "Original";
                break;
            case 2:
                tipoAudio = "Original com legenda";
                break;
            case 3:
                tipoAudio = "Dublado";
                break;
            default:
                tipoAudio = "Original";
                break;
        }

        Filme filme = new Filme(titulo, duracao, tipoProducao, tipoAudio);

        // Seleção de sala
        System.out.println("Número da sala:");
        int numeroSala = scanner.nextInt();
        scanner.nextLine();
        Sala sala = cinema.buscarSalaPorId(numeroSala);

        if (sala == null) {
            System.out.println("Sala não encontrada.");
            return;
        }

        // Horário da sessão
        System.out.println("Horário da sessão (HH:MM):");
        String horarioStr = scanner.nextLine();
        LocalDateTime horario = LocalDateTime.parse(horarioStr + ":00", DateTimeFormatter.ofPattern("HH:mm:ss"));
        
        // Verificação de 3D
        System.out.println("Reprodução em 3D? (sim/não):");
        String em3DStr = scanner.nextLine();
        boolean em3D = em3DStr.equalsIgnoreCase("sim");

        // Verificação de intervalo mínimo de 20 minutos
        if (cinema.verificarIntervaloMinimo(sala, horario, filme.getDuracao())) {
            Sessao sessao = new Sessao(filme, sala, horario, em3D);
            cinema.adicionarSessao(sessao);
        } else {
            System.out.println("Não é possível adicionar a sessão. Não há intervalo mínimo de 20 minutos após a sessão anterior.");
        }
    }

    private void removerSessao() {
        System.out.println("Título do filme:");
        String titulo = scanner.nextLine();
        Sessao sessaoParaRemover = null;
        for (Sessao sessao : cinema.getSessoes()) {
            if (sessao.getFilme().getTitulo().equalsIgnoreCase(titulo)) {
                sessaoParaRemover = sessao;
                break;
            }
        }
        if (sessaoParaRemover != null) {
            cinema.removerSessao(sessaoParaRemover);
        } else {
            System.out.println("Sessão não encontrada.");
        }
    }

    private void venderIngresso() {
        System.out.println("Título do filme:");
        String titulo = scanner.nextLine();
        System.out.println("Número da poltrona:");
        int numeroPoltrona = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Meia entrada? (sim/não):");
        String meiaEntradaStr = scanner.nextLine();
        boolean meiaEntrada = meiaEntradaStr.equalsIgnoreCase("sim");

        Sessao sessaoParaVenda = null;
        for (Sessao sessao : cinema.getSessoes()) {
            if (sessao.getFilme().getTitulo().equalsIgnoreCase(titulo)) {
                sessaoParaVenda = sessao;
                break;
            }
        }

        if (sessaoParaVenda != null && numeroPoltrona <= sessaoParaVenda.getSala().getCapacidade()) {
            Ingresso ingresso = new Ingresso(sessaoParaVenda, numeroPoltrona, meiaEntrada);
            cinema.adicionarIngresso(ingresso);
            System.out.println("Ingresso vendido com sucesso.");
        } else {
            System.out.println("Sessão não encontrada ou poltrona inválida.");
        }
    }

    private void cancelarIngresso() {
        System.out.println("Título do filme:");
        String titulo = scanner.nextLine();
        System.out.println("Número da poltrona:");
        int numeroPoltrona = scanner.nextInt();
        scanner.nextLine();

        Sessao sessaoParaCancelar = null;
        for (Sessao sessao : cinema.getSessoes()) {
            if (sessao.getFilme().getTitulo().equalsIgnoreCase(titulo)) {
                sessaoParaCancelar = sessao;
                break;
            }
        }

        if (sessaoParaCancelar != null && numeroPoltrona <= sessaoParaCancelar.getSala().getCapacidade()) {
            Ingresso ingresso = cinema.buscarIngresso(sessaoParaCancelar, numeroPoltrona);
            if (ingresso != null) {
                cinema.removerIngresso(ingresso);
                System.out.println("Ingresso cancelado com sucesso.");
            } else {
                System.out.println("Ingresso não encontrado.");
            }
        } else {
            System.out.println("Sessão não encontrada ou poltrona inválida.");
        }
    }

    private void consultarProgramacao() {
        for (Sessao sessao : cinema.getSessoes()) {
            System.out.println("Título: " + sessao.getFilme().getTitulo());
            System.out.println("Duração: " + sessao.getFilme().getDuracao() + " minutos");
            System.out.println("Horário: " + sessao.getHorario().format(DateTimeFormatter.ofPattern("HH:mm")));
            System.out.println("Tipo de Produção: " + sessao.getFilme().getTipoProducao());
            System.out.println("Tipo de Áudio: " + sessao.getFilme().getTipoAudio());
            System.out.println("3D: " + (sessao.isEm3D() ? "Sim" : "Não"));
            System.out.println("Número da Sala: " + sessao.getSala().getNome());
            System.out.println("Capacidade da Sala: " + sessao.getSala().getCapacidade());
            System.out.println("---------------------------");
        }
    }

    private void consultarDisponibilidadePoltronas() {
        System.out.println("Título do filme:");
        String titulo = scanner.nextLine();
        Sessao sessaoParaConsultar = null;
        for (Sessao sessao : cinema.getSessoes()) {
            if (sessao.getFilme().getTitulo().equalsIgnoreCase(titulo)) {
                sessaoParaConsultar = sessao;
                break;
            }
        }

        if (sessaoParaConsultar != null) {
            int ingressosVendidos = cinema.calcularIngressosVendidos(sessaoParaConsultar);
            int capacidade = sessaoParaConsultar.getSala().getCapacidade();
            int disponiveis = capacidade - ingressosVendidos;
            System.out.println("Poltronas disponíveis: " + disponiveis);
        } else {
            System.out.println("Sessão não encontrada.");
        }
    }

    private void consultarTaxaOcupacao() {
        for (Sessao sessao : cinema.getSessoes()) {
            int ingressosVendidos = cinema.calcularIngressosVendidos(sessao);
            int capacidade = sessao.getSala().getCapacidade();
            double taxaOcupacao = (double) ingressosVendidos / capacidade * 100;
            System.out.println("Título: " + sessao.getFilme().getTitulo());
            System.out.println("Taxa de Ocupação: " + taxaOcupacao + "%");
            System.out.println("---------------------------");
        }
    }

    private void consultarFaturamento() {
        double faturamentoTotal = cinema.calcularFaturamentoTotal();
        System.out.println("Faturamento total: R$ " + faturamentoTotal);
    }
}
