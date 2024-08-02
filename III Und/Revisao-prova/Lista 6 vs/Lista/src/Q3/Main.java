package Q3;
//Tem que ler os dados do numeros.txt e calcular a média deles

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        List<String> nomes = new ArrayList<>();
        List<String> sobrenomes = new ArrayList<>();
        List<String> nomeCompleto = new ArrayList<>();

        System.out.println("Quantas pessoas você quer gerar?");
        int qtd = input.nextInt();

        try{
            FileReader fileReaderNomes = new FileReader("lista 6 vs/lista/src/Q3/nomes.txt");
            FileReader fileReaderSobrenomes = new FileReader("lista 6 vs/lista/src/Q3/sobrenomes.txt");

            input = new Scanner(fileReaderNomes);
            while(input.hasNext()){
                nomes.add(input.nextLine());
            }

            input = new Scanner(fileReaderSobrenomes);
            while(input.hasNext()){
                sobrenomes.add(input.nextLine());
            }

            Random random = new Random();
            for(int i=0; i<qtd; i++){
                nomeCompleto.add("Nome: " + nomes.get(random.nextInt(nomes.size())) + " " + sobrenomes.get(random.nextInt(sobrenomes.size())) + ", Idade: " + (random.nextInt(100)));
            }

            input.close();
            fileReaderNomes.close();
            fileReaderSobrenomes.close();

            nomeCompleto.forEach(System.out::println);

        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}


