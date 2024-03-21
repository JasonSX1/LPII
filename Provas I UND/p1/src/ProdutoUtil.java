import java.util.Scanner;

public class ProdutoUtil {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        final int QTD = 5;
        Produto[] produto = new Produto[QTD];

        for(int i=0; i<QTD; i++){
            String nome, undMedida;
            int estoqueInicial;
            double preco;

            System.out.println("\nProduto " + (i+1));
            System.out.println("Nome................: ");
            nome = scanner.nextLine();
            System.out.println("Unidade de medida...: ");
            undMedida = scanner.nextLine();
            System.out.println("Estoque inicial.....: ");
            estoqueInicial = scanner.nextInt();
            System.out.println("Preço...............: ");
            preco = scanner.nextDouble();

            scanner.nextLine(); //consumir linhas do buffer

            int op;

            do{
                System.out.println();
                System.out.println("************ OPERACÕES ************");
                System.out.println("* 1 - Verificar produtos na prateleira (Com Estoque) *");
                System.out.println("* 2 - Comprar produtos *");
                System.out.println("* 3 - Verificar faturamento de vendas *");                
                System.out.println("* 4 - Encerrar *");
                System.out.println("***********************************");
                
                op=scanner.nextInt();

                switch(op){
                    case 1:
                    System.out.println("ss");
                }
            } while(op!=4);

        }





        scanner.close();
    }
}
