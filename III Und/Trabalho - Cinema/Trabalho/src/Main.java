public class Main {
    public static void main(String[] args) {
        GerenciamentoCinema gerenciamentoCinema = new GerenciamentoCinema();

        // Carregar dados previamente salvos, se existirem
        gerenciamentoCinema.carregarDados();

        // Exibir o menu principal para o usuário
        gerenciamentoCinema.exibirMenu();

        // Salvar dados ao sair do programa
        gerenciamentoCinema.salvarDados();
    }
}
