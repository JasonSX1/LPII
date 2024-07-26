import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

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

                    punhado.add(new PunhadoMoedas(valorMoedas, quantidadeMoedas));

                break;

                case 2:

                    if(punhado.isEmpty()){
                        System.out.println("Você não possui nenhum punhado de moedas cadastrado.");
                    }

                    double totalMoedas = 0.0;
                    Iterator<PunhadoMoedas> iterator = punhado.iterator();
                    while(iterator.hasNext()){
                        totalMoedas += (iterator.next().getTotalPunhado())
                    }

                    System.out.println("O total de moedas dos seus punhados cadastrados foi de " + totalMoedas);

                break;

                case 3:

                if(punhado.isEmpty()){
                    System.out.println("Você não possui nenhum punhado de moedas cadastrado.");
                }

                double maiorMoeda;
                Iterator<PunhadoMoedas> iterator2 = punhado.iterator();
                

                break;

            }

        }while (op != 4);
        input.close();
    }
}