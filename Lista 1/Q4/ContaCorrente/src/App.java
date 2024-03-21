import java.util.Scanner;

public class App {
        public static void main(String[] args) throws Exception {
        
        Scanner input = new Scanner(System.in);
        char resp;

        ContaCorrente c1 = new ContaCorrente(0);

        System.out.println("Saldo inicial: " + c1.getSaldo());

        do{
            System.out.println("Qual o tipo de operação? Depósito (D) ou Saque (S)?");
            char op = input.next().charAt(0);
            
            System.out.println("Valor de Depósito/Saque: ");
            double v = input.nextDouble();

            if (op == 'D' || op == 'd')
            c1.registrarDeposito(v);
            
            else if (op == 'S' || op == 's')
            c1.registrarSaque(v);

            System.out.println("Seu saldo atualizado é: " + c1.getSaldo());

            System.out.println("Você deseja encerrar as operações? (S/N)? ");
            resp = input.next().charAt(0);
        } while (resp == 'N' || resp == 'n');

        input.close();
    }
}
