import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<Conta> contas = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        int op;
        Conta contaSelecionada = null;

        do{

            System.out.println("");
            System.out.println("---------------------------------------");
            System.out.println("Software de gerenciamento de contas");
            System.out.println("1 - Cadastrar uma nova conta");
            System.out.println("2 - Sacar de determinada conta");
            System.out.println("3 - Depositar em determinada conta");
            System.out.println("4 - Consultar contas cadastradas");
            System.out.println("5 - Encerrar a aplicação.");
            System.out.println("---------------------------------------");
            System.out.println("");

            op = input.nextInt();
            input.nextLine();

            switch(op){
                case 1:
                    //Tenho que criar uma instancia local para o cadastro dos dados da conta
                    System.out.println("Qual o nome do titular da conta?");
                    String titular = input.nextLine();

                    contas.add(new Conta(titular));

                    System.out.println("Sucesso! Sua conta foi criada.");
                    break;

                case 2:
                    
                    if(contas.isEmpty()){
                        System.out.println("Erro! Você ainda não possui contas cadastradas.");
                    } else {                    
                        
                    System.out.println("Insira o número da conta da qual você deseja realizar o saque");
                    int numero = input.nextInt();

                    for(Conta c: contas){
                        if(c.getNumero() == numero){
                            contaSelecionada = c;
                            break;
                        }
                    }

                    /*Iterator<Conta> iterator2 = contas.iterator();
                    while(iterator2.hasNext()){
                        Conta c = iterator2.next();
                        contaSelecionada = c;
                        break;
                    } */

                    if(contaSelecionada == null) {
                        System.out.println("Erro! Conta não encontrada");
                    } else {
                        System.out.println("Insira o valor que você deseja sacar:");
                        double saque = input.nextDouble();

                        boolean sucesso = contaSelecionada.sacar(saque);
                            if(sucesso) {
                                System.out.println("Saque realizado com sucesso. Seu saldo atual é: " + contaSelecionada.getSaldo());
                            } else {
                                System.out.println("Erro! Saldo insuficiente.");
                        }
                        
                    }
                    break;
                }

                case 3:
                    if(contas.isEmpty()){
                        System.out.println("Erro! Você não possui contas cadastradas.");
                    } else {
                        System.out.println("Insira o número da conta em que você deseja realizar o depósito: ");
                        int numeroConta = input.nextInt();
                        input.nextLine();

                        Iterator<Conta> iterator3 = contas.iterator();

                        while(iterator3.hasNext()){
                            Conta c = iterator3.next();
                            if(c.getNumero() == numeroConta){
                                contaSelecionada = c;
                                break;
                            }
                        }

                        if(contaSelecionada == null){
                            System.out.println("Erro! Conta não encontrada.");
                        } else {
                            System.out.println("Insira o valor que você deseja depositar");
                            double deposito = input.nextDouble();
                            
                            contaSelecionada.depositar(deposito);
                            System.out.println("O depósito foi realizado com sucesso! O novo saldo da conta é de: " + contaSelecionada.getSaldo());
                        }
                    }
  
                /*CASE 4 USANDO ITERATOR
                 * 
                 * case 4:
                 *      if(contas.isEmpty()){
                 *      System.out.println("Erro! Você ainda não possui contas cadastradas.")
                 * } else {
                 *      System.out.println("As contas cadastradas atualmente são:")
                 *      Iterator<Conta> iterator = contas.iterator();
                 *      while(iterator.hasNext()){
                 *          Conta c = iterator.next(); //ISSO Q EU TENHO DIFICULDADE DE LEMBRAR
                 *          System.out.println(c)
                 * } 
                 *
                 * }
                 */

                case 4:
                    if(contas.isEmpty()){
                        System.out.println("Erro! Você ainda não possui contas cadastradas.");
                    } else {
                        System.out.println("As contas cadastradas atualmente são:");
                        Iterator<Conta> iterator = contas.iterator();
                        Conta contaTemp;
                        for(Conta c: contas){
                            System.out.println(c);
                        }
                    }

                case 5:
                    System.out.println("Encerrando a aplicação!");
                    break;

                    default:
                    System.out.println("Opção inválida, tente novamente.");
                    break;

            }

        } while(op!=5);
    }
}
