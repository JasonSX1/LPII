import java.util.Scanner;

public class FilmeUtil {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        final int MAX = 4;

        Filme[] filmes = new Filme[4];

        for(int i=0; i<MAX; i++){
            String titulo, entrada;
            int duracao, precoInteira;
            boolean audOriginal, legedado;


            System.out.println("INSIRA O TITULO DO FILME:");
            titulo = scanner.nextLine();
            System.out.println("INSIRA A DURACAO DO FILME");
            duracao = scanner.nextInt();
            System.out.println("O FILME POSSUI AUDIO ORIGINAL? (S/N)");
            entrada = scanner.nextLine();
            if(entrada.equalsIgnoreCase("S")||entrada.equalsIgnoreCase("SIM")){
                audOriginal = true;
            } else {
                audOriginal = false;
            }

            
        }
        
    }
}
