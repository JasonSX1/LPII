import java.util.Scanner;

public class FuncionarioUtil {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        final int QTD = 5;
        Funcionario[] func = new Funcionario[QTD];

        for (int i = 0; i<QTD; i++){
            String nome, sobrenome;
            double ValorHora;

            System.out.println("\nFuncionário " + (i + 1));
            System.out.print("Nome.....................: ");
            nome = scanner.nextLine();
            System.out.print("Sobrenome................: ");
            sobrenome = scanner.nextLine();
            System.out.print("Valor por Hora Trabalhada: ");
            ValorHora = scanner.nextDouble();

            scanner.nextLine(); //consumir linhas do buffer

            func[i] = new Funcionario(nome, sobrenome, ValorHora);
        }

        int op; //seleção de qual operação o usuário deseja realizar:

        do{
            System.out.println();
            System.out.println("************ OPERACÕES ************");
            System.out.println("* 1 - Adicionar Horas Trabalhadas *");
            System.out.println("* 2 - Listas Salários Líquidos *");
            System.out.println("* 3 - Encerrar *");
            System.out.println("***********************************");

            System.out.print("Informe Operação (1/2/3): ");
            op = scanner.nextInt(); // entrada de operação desejada
            
            switch(op){
                case 1:
                System.out.print("\nInforme No de Funcionário (1-" + QTD + "): ");
                int nrFunc = scanner.nextInt(); // entrada de no de funcionário
                System.out.print("Horas Trabalhadas a Adicionar..: ");
                int horas = scanner.nextInt(); // entrada de horas trabalhadas
                
                // atualização de horas trabalhadas de funcionário selecionado
                func[nrFunc - 1].adicionarCargaHoraria(horas);
                break;

                case 2:
                System.out.println();
                for (int i = 0; i < func.length; i++) // listagem de salários líquidos
                System.out.println("Funcionário " + (i + 1) + ": " +
                func[i].getSalarioLiquido());
                            
                
            }
        
        } while(op!=3);

        scanner.close();
    }
}
