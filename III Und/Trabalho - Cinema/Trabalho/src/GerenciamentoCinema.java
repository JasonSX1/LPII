import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
            System.out.println("1. Cadastrar Filme");
            System.out.println("2. Cadastrar Sala");
            System.out.println("3. Adicionar Sessão");
            System.out.println("4. Remover Sessão");
            System.out.println("5. Vender Ingresso");
            System.out.println("6. Cancelar Ingresso");
            System.out.println("7. Consultar Programação");
            System.out.println("8. Consultar Disponibilidade de Poltronas");
            System.out.println("9. Consultar Taxa de Ocupação");
            System.out.println("10. Consultar Faturamento");
            System.out.println("11. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarFilme();
                    break;
                case 2:
                    cadastrarSala();
                    break;
                case 3:
                    adicionarSessao();
                    break;
                case 4:
                    removerSessao();
                    break;
                case 5:
                    venderIngresso();
                    break;
                case 6:
                    cancelarIngresso();
                    break;
                case 7:
                    consultarProgramacao();
                    break;
                case 8:
                    consultarDisponibilidadePoltronas();
                    break;
                case 9:
                    consultarTaxaOcupacao();
                    break;
                case 10:
                    consultarFaturamento();
                    break;
                case 11:
                    salvarDados();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrarFilme() {
        System.out.println("Título do filme:");
        String titulo = scanner.nextLine();
        System.out.println("Duração (em minutos):");
        int duracao = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Tipo de produção: 1. Nacional 2. Estrangeira");
        int opcaoProducao = scanner.nextInt();
        scanner.nextLine();
        String tipoProducao = opcaoProducao == 1 ? "Nacional" : "Estrangeira";

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
        cinema.adicionarFilme(filme);
    }

    private void cadastrarSala() {
        System.out.println("Número da sala:");
        int numero = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Capacidade da sala:");
        int capacidade = scanner.nextInt();
        scanner.nextLine();

        Sala sala = Sala.criarSala(numero, capacidade);
        cinema.adicionarSala(sala);
    }

private void adicionarSessao() {
    System.out.println("Título do filme:");
    String titulo = scanner.nextLine();
    Filme filme = cinema.buscarFilmePorTitulo(titulo);
    if (filme == null) {
        System.out.println("Filme não encontrado.");
        return;
    }

    System.out.println("Número da sala:");
    int numeroSala = scanner.nextInt();
    scanner.nextLine();
    Sala sala = cinema.buscarSalaPorId(numeroSala);
    if (sala == null) {
        System.out.println("Sala não encontrada.");
        return;
    }

    System.out.println("Horário da sessão (HH:MM):");
    String horarioStr = scanner.nextLine();
    LocalTime horario = LocalTime.parse(horarioStr, DateTimeFormatter.ofPattern("HH:mm"));

    System.out.println("Reprodução em 3D? (sim/não):");
    String em3DStr = scanner.nextLine();
    boolean em3D = em3DStr.equalsIgnoreCase("sim");

    Sessao sessao = new Sessao(sala, horario, em3D, 20.0); // Valor do ingresso base fixo como exemplo
    if (sala.adicionarHorario(horario)) {
        sessao.adicionarFilme(filme);
        cinema.adicionarSessao(sessao);
    } else {
        System.out.println("Não é possível adicionar a sessão. Não há intervalo mínimo de 20 minutos após a sessão anterior.");
    }
}


    private void removerSessao() {
        System.out.println("Título do filme:");
        String titulo = scanner.nextLine();
        Sessao sessaoParaRemover = cinema.buscarSessaoPorFilme(titulo);
        if (sessaoParaRemover != null) {
            cinema.removerSessao(sessaoParaRemover);
        } else {
            System.out.println("Sessão não encontrada.");
        }
    }

    private void venderIngresso() {
        System.out.println("Título do filme:");
        String titulo = scanner.nextLine();
        List<Sessao> sessoesDoFilme = new ArrayList<>();
        for (Sessao sessao : cinema.getSessoes()) {
            for (Filme filme : sessao.getFilmes()) {
                if (filme.getTitulo().equalsIgnoreCase(titulo)) {
                    sessoesDoFilme.add(sessao);
                }
            }
        }

        if (sessoesDoFilme.isEmpty()) {
            System.out.println("Filme não encontrado.");
            return;
        }

        System.out.println("Sessões disponíveis:");
        for (int i = 0; i < sessoesDoFilme.size(); i++) {
            Sessao sessao = sessoesDoFilme.get(i);
            System.out.println(i + 1 + ". " + sessao.getSala().getHorarios());

            //Consultar programação
            //
        }

        System.out.println("Escolha uma sessão (número):");
        int escolhaSessao = scanner.nextInt() - 1;
        Sessao sessaoEscolhida = sessoesDoFilme.get(escolhaSessao);
        Sala sala = sessaoEscolhida.getSala();

        imprimirMapaAssentos(sala);

        System.out.println("Escolha uma poltrona (número):");
        int numeroPoltrona = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha
        Ingresso ingressoExistente = cinema.buscarIngresso(sessaoEscolhida, numeroPoltrona);
        if (ingressoExistente != null) {
            System.out.println("Poltrona já ocupada.");
        } else {
            System.out.println("Meia entrada? (sim/não):");
            boolean meiaEntrada = scanner.nextLine().equalsIgnoreCase("sim");
            Ingresso ingresso = new Ingresso(sessaoEscolhida, sessaoEscolhida.getFilmes().get(0), numeroPoltrona, meiaEntrada);
            cinema.adicionarIngresso(ingresso);
            System.out.println("Ingresso vendido com sucesso.");
        }
    }

    private void imprimirMapaAssentos(Sala sala) {
        int capacidade = sala.getCapacidade();
        int sqrt = (int) Math.ceil(Math.sqrt(capacidade));
        boolean[][] mapaAssentos = new boolean[sqrt][sqrt];

        for (Ingresso ingresso : cinema.getIngressos()) {
            if (ingresso.getSessao().getSala().equals(sala)) {
                int numeroPoltrona = ingresso.getNumeroPoltrona() - 1;
                int row = numeroPoltrona / sqrt;
                int col = numeroPoltrona % sqrt;
                mapaAssentos[row][col] = true;
            }
        }

        for (int i = 0; i < sqrt; i++) {
            for (int j = 0; j < sqrt; j++) {
                int poltronaNumero = i * sqrt + j + 1;
                if (poltronaNumero > capacidade) {
                    break;
                }
                if (mapaAssentos[i][j]) {
                    System.out.print("[X]");
                } else {
                    System.out.print("[" + poltronaNumero + "]");
                }
            }
            System.out.println();
        }
    }

    private void cancelarIngresso() {
        System.out.println("Título do filme:");
        String titulo = scanner.nextLine();
        Sessao sessaoParaCancelar = cinema.buscarSessaoPorFilme(titulo);
        if (sessaoParaCancelar != null) {
            System.out.println("Número da poltrona:");
            int numeroPoltrona = scanner.nextInt();
            scanner.nextLine();
            Ingresso ingressoParaCancelar = cinema.buscarIngresso(sessaoParaCancelar, numeroPoltrona);
            if (ingressoParaCancelar != null) {
                cinema.removerIngresso(ingressoParaCancelar);
            } else {
                System.out.println("Ingresso não encontrado.");
            }
        } else {
            System.out.println("Sessão não encontrada.");
        }
    }

    private void consultarProgramacao() {
        System.out.println("Programação do cinema:");
        for (Sessao sessao : cinema.getSessoes()) {
            System.out.println("Filme: " + sessao.getFilmes().get(0).getTitulo() + ", Sala: " + sessao.getSala().getNumero() + ", Horário: " + sessao.getHorario());
        }
    }

    private void consultarDisponibilidadePoltronas() {
        System.out.println("Título do filme:");
        String titulo = scanner.nextLine();
        Sessao sessaoParaConsultar = cinema.buscarSessaoPorFilme(titulo);
        if (sessaoParaConsultar != null) {
            int capacidade = sessaoParaConsultar.getSala().getCapacidade();
            int vendidos = cinema.calcularIngressosVendidos(sessaoParaConsultar);
            int disponiveis = capacidade - vendidos;
            System.out.println("Ingressos disponíveis: " + disponiveis);
        } else {
            System.out.println("Sessão não encontrada.");
        }
    }

    private void consultarTaxaOcupacao() {
        System.out.println("Título do filme:");
        String titulo = scanner.nextLine();
        Sessao sessaoParaConsultar = cinema.buscarSessaoPorFilme(titulo);
        if (sessaoParaConsultar != null) {
            int capacidade = sessaoParaConsultar.getSala().getCapacidade();
            int vendidos = cinema.calcularIngressosVendidos(sessaoParaConsultar);
            double taxaOcupacao = (double) vendidos / capacidade * 100;
            System.out.println("Taxa de ocupação: " + taxaOcupacao + "%");
        } else {
            System.out.println("Sessão não encontrada.");
        }
    }

    private void consultarFaturamento() {
        System.out.println("Faturamento total:");
        double faturamento = cinema.calcularFaturamento();
        System.out.println("R$ " + faturamento);
    }

    public static void main(String[] args) {
        GerenciamentoCinema gerenciamento = new GerenciamentoCinema();
        gerenciamento.carregarDados();
        gerenciamento.exibirMenu();
    }
}
