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

    public void exibirMenu() throws Exception {
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
        switch (opcaoAudio) {
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
        List<Sala> salas = cinema.getSalas();
        System.out.println("Salas disponíveis:");
        for (Sala sala : salas) {
            System.out.println(sala.getNumero());
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

        double preco = 20.0; // Definir um preço base, por exemplo

        Sessao sessao = new Sessao(filme, sala, horario, em3D, preco); // Valor do ingresso base fixo como exemplo

        // Verificar se é possível adicionar o horário desejado
        if (sala.adicionarHorario(filme, horario)) {
            cinema.adicionarSessao(sessao);
            System.out.println("Sessão adicionada com sucesso.");
        } else {
            // Sugerir o próximo horário disponível
            LocalTime proximoHorarioDisponivel = calcularProximoHorarioDisponivel(sala, filme);
            System.out.printf("Não é possível adicionar a sessão no horário desejado. O próximo horário disponível é %s.%n", proximoHorarioDisponivel);
        }
    }

    private LocalTime calcularProximoHorarioDisponivel(Sala sala, Filme filme) {
        LocalTime proximoHorarioDisponivel = LocalTime.MIN;

        for (LocalTime h : sala.getHorarios()) {
            LocalTime fimExistente = h.plusMinutes(filme.getDuracao() + 20); // Duração do filme + 20 minutos
            if (fimExistente.isAfter(proximoHorarioDisponivel)) {
                proximoHorarioDisponivel = fimExistente;
            }
        }

        return proximoHorarioDisponivel;
    }

    private void removerSessao() {
        System.out.println("Título do filme:");
        String titulo = scanner.nextLine();
        List<Sessao> sessoesDoFilme = new ArrayList<>();
        for (Sessao sessao : cinema.getSessoes()) {
            if (sessao.getFilme().getTitulo().equalsIgnoreCase(titulo)) {
                sessoesDoFilme.add(sessao);
            }
        }

        if (sessoesDoFilme.isEmpty()) {
            System.out.println("Filme não encontrado.");
            return;
        }

        System.out.println("Sessões disponíveis:");
        for (int i = 0; i < sessoesDoFilme.size(); i++) {
            Sessao sessao = sessoesDoFilme.get(i);
            System.out.println((i + 1) + ". " + sessao.getHorario());
        }

        System.out.println("Escolha uma sessão para remover (número):");
        int escolhaSessao = scanner.nextInt() - 1;
        scanner.nextLine(); // Consome a nova linha

        if (escolhaSessao < 0 || escolhaSessao >= sessoesDoFilme.size()) {
            System.out.println("Escolha inválida.");
            return;
        }

        Sessao sessaoParaRemover = sessoesDoFilme.get(escolhaSessao);
        cinema.removerSessao(sessaoParaRemover);
        System.out.println("Sessão removida com sucesso.");
    }

    private void venderIngresso() throws Exception {
        System.out.println("Título do filme:");
        String titulo = scanner.nextLine();
        List<Sessao> sessoesDoFilme = new ArrayList<>();
        for (Sessao sessao : cinema.getSessoes()) {
                if (sessao.getFilme().getTitulo().equalsIgnoreCase(titulo)) {
                    sessoesDoFilme.add(sessao);
            }
        }

        if (sessoesDoFilme.isEmpty()) {
            System.out.println("Filme não encontrado.");
            return;
        }

        System.out.println("Sessões disponíveis:");
        for (int i = 0; i < sessoesDoFilme.size(); i++) {
            Sessao sessao = sessoesDoFilme.get(i);
            System.out.println((i + 1) + ". " + sessao.getHorario());
        }

        System.out.println("Escolha uma sessão (número):");
        int escolhaSessao = scanner.nextInt() - 1;
        scanner.nextLine(); // Consome a nova linha

        if (escolhaSessao < 0 || escolhaSessao >= sessoesDoFilme.size()) {
            System.out.println("Escolha inválida.");
            return;
        }

        Sessao sessaoEscolhida = sessoesDoFilme.get(escolhaSessao);
        Sala sala = sessaoEscolhida.getSala();

        imprimirMapaAssentos(sala, sessaoEscolhida);

        System.out.println("Escolha uma poltrona (número):");
        int numeroPoltrona = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha
        Ingresso ingressoExistente = cinema.buscarIngresso(sessaoEscolhida, numeroPoltrona);
        if (ingressoExistente != null) {
            System.out.println("Poltrona já ocupada.");
        } else {
            System.out.println("Meia entrada? (sim/não):");
            boolean meiaEntrada = scanner.nextLine().equalsIgnoreCase("sim");
            Ingresso ingresso = new Ingresso(sessaoEscolhida, sessaoEscolhida.getFilme(), numeroPoltrona, meiaEntrada);
            cinema.adicionarIngresso(ingresso);
            System.out.println("Ingresso vendido com sucesso.");
        }
    }

    private void imprimirMapaAssentos(Sala sala, Sessao sessao) {
        int capacidade = sala.getCapacidade();
        int sqrt = (int) Math.ceil(Math.sqrt(capacidade));
        boolean[][] mapaAssentos = new boolean[sqrt][sqrt];

        for (Ingresso ingresso : cinema.getIngressos()) {
            if (ingresso.getSessao().equals(sessao) && ingresso.getSessao().getSala().equals(sala)) {
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
                    System.out.print("    "); // Adicionando espaços extras para melhor alinhamento
                } else if (mapaAssentos[i][j]) {
                    System.out.print("[X]");
                } else {
                    if (poltronaNumero < 10) {
                        System.out.print("[0" + poltronaNumero + "]");
                    } else {
                        System.out.print("[" + poltronaNumero + "]");
                    }
                }
            }
            System.out.println();
        }
    }

    private void cancelarIngresso() {
        System.out.println("Título do filme:");
        String titulo = scanner.nextLine();
        List<Sessao> sessoesDoFilme = new ArrayList<>();
        for (Sessao sessao : cinema.getSessoes()) {
            if (sessao.getFilme().getTitulo().equalsIgnoreCase(titulo)) {
                sessoesDoFilme.add(sessao);
            }
        }
    
        if (sessoesDoFilme.isEmpty()) {
            System.out.println("Filme não encontrado.");
            return;
        }
    
        System.out.println("Sessões disponíveis:");
        for (int i = 0; i < sessoesDoFilme.size(); i++) {
            Sessao sessao = sessoesDoFilme.get(i);
            System.out.println((i + 1) + ". " + sessao.getHorario());
        }
    
        System.out.println("Escolha uma sessão (número):");
        int escolhaSessao = scanner.nextInt() - 1;
        scanner.nextLine(); // Consome a nova linha
    
        if (escolhaSessao < 0 || escolhaSessao >= sessoesDoFilme.size()) {
            System.out.println("Escolha inválida.");
            return;
        }
    
        Sessao sessaoEscolhida = sessoesDoFilme.get(escolhaSessao);
    
        System.out.println("Número da poltrona:");
        int numeroPoltrona = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha
    
        Ingresso ingressoParaCancelar = cinema.buscarIngresso(sessaoEscolhida, numeroPoltrona);
        if (ingressoParaCancelar != null) {
            cinema.removerIngresso(ingressoParaCancelar);
            System.out.println("Ingresso cancelado com sucesso.");
        } else {
            System.out.println("Ingresso não encontrado.");
        }
    }
    

    private void consultarProgramacao() {
        System.out.println("Programação do cinema:");
        for (Sessao sessao : cinema.getSessoes()) {
            if (sessao.isEm3D())
                System.out.println("Filme: " + sessao.getFilme().getTitulo() + ", Sala: " + sessao.getSala().getNumero()
                        + " Horário: " + sessao.getHorario() + " (3D)");
            else
                System.out.println("Filme: " + sessao.getFilme().getTitulo() + ", Sala: " + sessao.getSala().getNumero()
                        + " Horário: " + sessao.getHorario() + " (2D)");
            // tem que imprimir se o filme é 3d pela sessao
        }
    }

    private void consultarDisponibilidadePoltronas() {
        System.out.println("Título do filme:");
        String titulo = scanner.nextLine();
        List<Sessao> sessoesDoFilme = new ArrayList<>();
        for (Sessao sessao : cinema.getSessoes()) {
            if (sessao.getFilme().getTitulo().equalsIgnoreCase(titulo)) {
                sessoesDoFilme.add(sessao);
            }
        }

        if (sessoesDoFilme.isEmpty()) {
            System.out.println("Filme não encontrado.");
            return;
        }

        System.out.println("Sessões disponíveis:");
        for (int i = 0; i < sessoesDoFilme.size(); i++) {
            Sessao sessao = sessoesDoFilme.get(i);
            System.out.println((i + 1) + ". " + sessao.getHorario());
        }

        System.out.println("Escolha uma sessão (número):");
        int escolhaSessao = scanner.nextInt() - 1;
        scanner.nextLine(); // Consome a nova linha

        if (escolhaSessao < 0 || escolhaSessao >= sessoesDoFilme.size()) {
            System.out.println("Escolha inválida.");
            return;
        }

        Sessao sessaoEscolhida = sessoesDoFilme.get(escolhaSessao);
        Sala sala = sessaoEscolhida.getSala();

        int capacidade = sala.getCapacidade();
        int vendidos = cinema.calcularIngressosVendidos(sessaoEscolhida);
        int disponiveis = capacidade - vendidos;
        System.out.println("Ingressos disponíveis: " + disponiveis);

        imprimirMapaAssentos(sala, sessaoEscolhida);
    }

    private void consultarTaxaOcupacao() {
        System.out.println("Título do filme:");
        String titulo = scanner.nextLine();
        List<Sessao> sessoesDoFilme = new ArrayList<>();
        for (Sessao sessao : cinema.getSessoes()) {
            if (sessao.getFilme().getTitulo().equalsIgnoreCase(titulo)) {
                sessoesDoFilme.add(sessao);
            }
        }

        if (sessoesDoFilme.isEmpty()) {
            System.out.println("Filme não encontrado.");
            return;
        }

        System.out.println("Sessões disponíveis:");
        for (int i = 0; i < sessoesDoFilme.size(); i++) {
            Sessao sessao = sessoesDoFilme.get(i);
            System.out.println((i + 1) + ". " + sessao.getHorario());
        }

        System.out.println("Escolha uma sessão (número):");
        int escolhaSessao = scanner.nextInt() - 1;
        scanner.nextLine(); // Consome a nova linha

        if (escolhaSessao < 0 || escolhaSessao >= sessoesDoFilme.size()) {
            System.out.println("Escolha inválida.");
            return;
        }

        Sessao sessaoEscolhida = sessoesDoFilme.get(escolhaSessao);
        double taxaOcupacao = cinema.calcularTaxaOcupacao(sessaoEscolhida);
        System.out.println("Taxa de ocupação: " + taxaOcupacao + "%");
    }

    public static void main(String[] args) throws Exception {
        GerenciamentoCinema gerenciamento = new GerenciamentoCinema();
        gerenciamento.carregarDados();
        gerenciamento.exibirMenu();
    }
}
