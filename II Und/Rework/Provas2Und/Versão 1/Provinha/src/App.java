import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<PunhadoMoedas> punhado = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        int op;

        do{
            System.out.println("--------------------------------------------------");
            System.out.println("----------Aplicação de punhado de moedas----------");
            System.out.println("------1 - Cadastrar um novo punhado de moedas-----");
            System.out.println("2 - Ver todos os punhados de moedas cadastrados");
            System.out.println("3 - Verificar a maior moeda cadastrada");
            System.out.println("4 - Encerrar a aplicação.");
            System.out.println("--------------------------------------------------");

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
                        System.out.println("Você ainda não cadastrou nenhum punhado de moedas!");
                    }

                    double totalPunhados = 0;
                    Iterator<PunhadoMoedas> iterator = punhado.iterator();
                    while(iterator.hasNext()){
                        totalPunhados += iterator.next().getTotalPunhado();
                    }
                    System.out.println("O valor total de seus punhados de moedas é: " + totalPunhados);

                break;

                case 3:
                    if(punhado.isEmpty()){
                        System.out.println("Você ainda não cadastrou nenhum punhado de moedas!");
                        return;
                    }

                    PunhadoMoedas maiorPunhado = punhado.get(0);
                    Iterator<PunhadoMoedas> iteratorMaiorMoeda = punhado.iterator();
                    
                    while(iteratorMaiorMoeda.hasNext()){
                        PunhadoMoedas atual = iteratorMaiorMoeda.next();
                        if(atual.getQuantidade() >  maiorPunhado.getQuantidade()){
                            maiorPunhado = atual;
                        }

                        //Testar fazer isso ao contrário, vendo se o maior que tenho atualmente é menor que o 
                        //próximo que esotu comparando, que é uma abordagem mais interessante
                    }

                    System.out.println("Moeda de maior quantidade: R$ " + maiorPunhado.getValor() + " , E sua quantidade foi de " + maiorPunhado.getQuantidade());
                break;
            }

        }while (op != 4);
        input.close();
    }
}