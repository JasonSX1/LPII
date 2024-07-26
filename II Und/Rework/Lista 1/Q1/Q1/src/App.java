import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o Dia: ");
        int dia = scanner.nextInt();

        System.out.println("Digite o Mês: ");
        int mes = scanner.nextInt();

        System.out.println("Digite o Ano: ");
        int ano = scanner.nextInt();

        Data data = new Data(mes, dia, ano);

        System.out.println("Data digitada: " + data.mostrarData());

        System.out.println("Faltam " + data.getDiasTerminoAno() + " dias para o término do ano.");
    }
}
