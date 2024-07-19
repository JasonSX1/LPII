import java.util.Scanner;
import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        Stack<Integer> pilha = new Stack<Integer>();
        Scanner input = new Scanner(System.in);
        int numero;
        int primeiroNumero = 0;
        boolean isFirstNumber = true;
        int cont = 0;

        System.out.println("Insira um inteiro para adicionar à pilha (A inserção do 0 encerra a execução do programa)");

        while((numero = input.nextInt()) != 0){
            if(isFirstNumber) {
                primeiroNumero = numero;
                isFirstNumber = false;
            }
            pilha.push(numero);
        }

        for(int num : pilha) {
            if(num == primeiroNumero) {
                cont++;
            }
        }

        System.out.println("O primeiro número inserido foi: " + primeiroNumero);
        System.out.println("E ele ocorreu " + cont + " vezes na sua pilha.");
        System.out.println("Encerrando o programa.");

        input.close();
    }
}
