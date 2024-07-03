import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Exemplo2{

    public static void main(String[] args){

    Queue<Integer> fila = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        String resp;

        do{
            System.out.println("Enfileirar, Desenfileirar ou fechar o programa: (E,D,F)");
            resp = scanner.nextLine().toLowerCase();

            switch(resp){
                case "e":
                    System.out.println("Digite o núnero: ");
                    Integer n = scanner.nextInt();
                    scanner.nextLine();
                    fila.add(n);
                    System.out.println("Fila após a inserção: " + fila);
                    break;
                case "d":
                    n = fila.poll();
                    System.out.printf("Numero desenfileirado: %d", n);
                    System.out.println("\nFila após a remoção: " + fila);
                    break;
            }
        } while (!resp.equals("f"));
        scanner.close();
    }
}

//fazer o exemplo 3, de deque