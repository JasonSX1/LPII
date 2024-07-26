import java.util.LinkedList;
import java.util.Scanner;
import java.util.Deque;

public class Exemplo3 {
    public static void main(String[] args) {
    
    Deque<Integer> deque = new LinkedList<>();
    Scanner scanner = new Scanner(System.in);
    String resp;
    do {
        System.out.println("Inserir, Remover, ou Fechar o programa(I/R/F): ");
        resp = scanner.nextLine().toLowerCase();
        
        switch(resp){
            case "i":
            System.out.println("Digite o número: ");
            Integer n = scanner.nextInt();
            scanner.nextLine();
            
            System.out.println("Deseja inserir no inicio ou final? (i/f): ");
            String extremidade = scanner.nextLine().toLowerCase();
            if(extremidade.equals("i")) {
            deque.addFirst(n);
            } else {
            deque.addLast(n);
            }
            System.out.print("Deque após a inserção: " + deque);
            System.out.println();
            break;
            case "r":

            System.out.printf("Remoção inicio ou final (I/F)? : ");
            extremidade = scanner.nextLine().toLowerCase();
            if (extremidade.equals("i")) {
            n = deque.pollFirst();
            } else {
            n = deque.pollLast();
            }
            System.out.printf("Número removido: %d ", n);
            System.out.println();
            System.out.println("Deque após a remoção: " + deque);
            break;
        }
    } while(!resp.equals("f"));
    scanner.close();
   }
}