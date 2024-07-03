import java.util.InputMismatchException;
import java.util.Scanner;

public class ProdutoUtil {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int QTD = 5;
        Produto[] produto = new Produto[QTD];

        for (int i = 0; i < QTD; i++) {
            do {
                try {
                    String nome, undMedida;
                    int estoqueInicial;
                    double preco;

                    System.out.println("\nProduto " + (i + 1));
                    System.out.print("Nome................: ");
                    nome = scanner.nextLine();
                    System.out.print("Unidade de medida...: ");
                    undMedida = scanner.nextLine();
                    System.out.print("Estoque inicial.....: ");
                    estoqueInicial = scanner.nextInt();
                    System.out.print("Preço...............: ");
                    preco = scanner.nextDouble(); 
                    scanner.nextLine(); //consumir linhas do buffer

                    produto[i] = new Produto(nome, undMedida, estoqueInicial, preco);
                    break;
                } catch (InputMismatchException ie) {
                    System.out.println("Erro de entrada: Tipo de dado não esperado. Insira novamente");
                    scanner.nextLine(); // consumir a quebra de linha após o próximoInt ou nextDouble
                    i--; // reiniciar a iteração para inserir os dados corretamente
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            } while (true);
        }
        
        int op;

        do {
            System.out.println();
            System.out.println("************ OPERACÕES ************");
            System.out.println("* 1 - Verificar produtos na prateleira (Com Estoque) *");
            System.out.println("* 2 - Comprar produtos *");
            System.out.println("* 3 - Verificar faturamento de vendas *");                
            System.out.println("* 4 - Encerrar *");
            System.out.println("***********************************");
            
            op = scanner.nextInt();

            switch (op) {
                case 1:
                    System.out.println("************ PRATELEIRA ************");
                    int j = 1;
                    for (int k = 0; k < produto.length; k++) {
                        System.out.println(j + " - " + produto[k].getNome() + " - Quantidade em estoque: " + produto[k].getEstoqueDisponivel() + " - Preço: " + produto[k].getPreco());
                        j++;
                    }
                    break;

                case 2: 
                    int opcaoSelecionada;
                    do {
                        System.out.println("************ PRODUTOS ************");
                        int l = 1;
                        for (int k = 0; k < produto.length; k++) {
                            System.out.println(l + " - " + produto[k].getNome() + " - Quantidade em estoque: " + produto[k].getEstoqueDisponivel() + " - Preço: " + produto[k].getPreco());
                            l++;
                        }
                        System.out.println("************ SELECIONE O PRODUTO DESEJADO ************");
                        opcaoSelecionada = scanner.nextInt();

                        if (opcaoSelecionada < 1 || opcaoSelecionada > produto.length) {
                            System.out.println("Você inseriu um índice inválido, insira novamente!!!");
                        }
                    } while (opcaoSelecionada < 1 || opcaoSelecionada > produto.length);

                    //fazer o for associado à posição no array para adicionar ao carrinho junto a uma verificação
                    int quantidadeSelecionada;

                    System.out.println("Quantidade desejada: ");
                    quantidadeSelecionada = scanner.nextInt();

                    if (produto[opcaoSelecionada - 1].adicionarVenda(quantidadeSelecionada)) {
                        System.out.println("Venda realizada com sucesso de: " + quantidadeSelecionada);
                    } else {
                        System.out.println("Quantidade em estoque é menor que a quantidade selecionada!");
                    }
                    break;

                case 3: 
                    System.out.println("************ REGISTRO DE VENDAS ************");
                    int m = 1;
                    for (int k = 0; k < produto.length; k++) {
                        System.out.println(m + " - " + produto[k].getNome() + " - Quantidade em estoque: " + produto[k].getEstoqueDisponivel() + " - Vendas: " + produto[k].qtdVendas() + " - Faturamento: " + produto[k].getFaturamentoVenda());
                        m++;
                    }
                    break;                
            } 
        } while (op != 4);

        scanner.close();
    }
}
