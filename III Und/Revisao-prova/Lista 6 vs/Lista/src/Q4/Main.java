package Q4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        List<String> nomes = new ArrayList<>();
        List<String> sobrenomes = new ArrayList<>();
        List<String> nomeCompleto = new ArrayList<>();
        List<String> nomesCompletosSalvamento = new ArrayList<>();

        System.out.println("Quantas pessoas você quer gerar?");
        int qtd = input.nextInt();

        try {
            FileReader fileReaderNomes = new FileReader("lista 6 vs/lista/src/Q4/nomes.txt");
            FileReader fileReaderSobrenomes = new FileReader("lista 6 vs/lista/src/Q4/sobrenomes.txt");

            input = new Scanner(fileReaderNomes);
            while (input.hasNext()) {
                nomes.add(input.nextLine());
            }

            input = new Scanner(fileReaderSobrenomes);
            while (input.hasNext()) {
                sobrenomes.add(input.nextLine());
            }

            Random random = new Random();
            for (int i = 0; i < qtd; i++) {
                String nome = nomes.get(random.nextInt(nomes.size()));
                String sobrenome = sobrenomes.get(random.nextInt(sobrenomes.size()));
                int idade = random.nextInt(100);
                nomeCompleto.add("Nome: " + nome + " " + sobrenome + ", Idade: " + idade);
                nomesCompletosSalvamento.add(nome + " " + sobrenome + ", " + idade);
            }

            input.close();
            fileReaderNomes.close();
            fileReaderSobrenomes.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Implementação do algoritmo de ordenação por inserção
        for (int i = 1; i < nomeCompleto.size(); i++) {
            String nomeTemp = nomeCompleto.get(i); // enésimo nome
            int j = i - 1; // índice de nome anterior ao enésimo nome em comparação

            // Realocação de nomes anteriores para índices imediatamente subsequentes
            while (j >= 0 && nomeCompleto.get(j).compareTo(nomeTemp) > 0) {
                nomeCompleto.set(j + 1, nomeCompleto.get(j));
                j--;
            }
            // Atribuição de enésimo nome em índice de acordo com sua posição em relação aos
            // nomes anteriores comparados
            nomeCompleto.set(j + 1, nomeTemp);
        }

        // Imprime cada nome completo na lista
        // nomeCompleto.forEach(System.out::println);
        // ou
        for (String nomesCompleto : nomeCompleto) {
            // listagem de nomes
            System.out.println(nomesCompleto);
        }

        try {
            FileWriter fileWriter = new FileWriter("lista 6 vs/lista/src/Q4/nomesCompletos.txt");
            for (String nome : nomesCompletosSalvamento) {
                fileWriter.write(nome + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
