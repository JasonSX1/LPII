package Q1;
//Tem que ler os dados do numeros.txt e calcular a média deles

import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        String caminhoArquivo = "src/Q1/numeros.txt";
        int soma = 0, cont = 0;

        try{
            FileReader fileReader = new FileReader(caminhoArquivo);
            Scanner scanner = new Scanner(fileReader);
            while(scanner.hasNext()){
                soma += Integer.parseInt(scanner.nextLine());
                cont++;
            }
            scanner.close();
            fileReader.close();
        } catch(IOException e){
            throw new RuntimeException(e);
        }

        System.out.println("Média: " + (double) soma/cont);
    }
}


