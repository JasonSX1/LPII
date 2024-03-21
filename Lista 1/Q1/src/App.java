import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);

        int d, m, a;
        Data data;

       System.out.println("Informe um Dia (1-31): ");
        d = input.nextInt();

       System.out.println("Informe um Mês (1-12): ");
        m = input.nextInt();

       System.out.println("Informe um Ano: ");
        a = input.nextInt();


        data = new Data(d,m,a);

        System.out.println(data.mostrarData()); 

        System.out.println("Faltam " + data.getDiasTerminoAno() + " dias para o fim do ano.");

        input.close();
    }
}