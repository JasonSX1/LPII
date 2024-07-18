import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<PunhadoMoedas> punhado = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        int op;

        do{
            System.out.println("Aplicação de punhado de moedas");
            System.out.println("1 - Cadastrar um novo punhado de moedas");
            System.out.println("2 - Ver todos os punhados de moedas cadastrados");
            System.out.println("3 - Verificar a maior moeda cadastrada");
            System.out.println("4 - Encerrar a aplicação.");

            op = input.nextInt();
            input.nextLine(); //Consumir o enter do buffer?
            switch(op){
                case 1:
                    System.out.println("Qual o valor do punhado de moedas?");
                    double valorMoedas = input.nextDouble();
                    input.nextLine();

                    System.out.println("Insira a quantidade de moedas com o valor indicado:");
                    int quantidadeMoedas = input.nextInt();
                    input.nextLine();

                    punhado.add(new PunhadoMoedas(valorMoedas, quantidadeMoedas))

                break;

                case 2:

                break;

                case 3:

                break;

            }

        }while (op != 4);
        input.close();
    }
}