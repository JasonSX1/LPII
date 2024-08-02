package Q2;
//Tem que ler os dados do numeros.txt e calcular a média deles

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        String caminhoArquivo = "lista 6 vs/lista/src/Q2/numeros.txt";
        int soma = 0, cont = 0;
        int index = 0;

        try{
            FileReader fileReader = new FileReader(caminhoArquivo);
            Scanner scanner = new Scanner(fileReader);
            Scanner input = new Scanner(System.in);

            try{
                System.out.println("Quantos números do arquivo você deseja ler? ");
                index = input.nextInt();
    
                while(cont<index){
                    soma += Integer.parseInt(scanner.nextLine());
                    cont++;
                }

            } catch(NoSuchElementException e){
                System.out.println("Linhas insuficientes!");
            }


            scanner.close();
            input.close();
            fileReader.close();
        } catch(IOException e){
            throw new RuntimeException(e);
        }

        System.out.println("Média: " + (double) soma/cont);
    }
}


