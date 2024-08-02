package Q5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<String> nomes = new ArrayList<>();
        List<String> sobrenomes = new ArrayList<>();
        List<Pessoa> pessoas = new ArrayList<>();

        System.out.println("Quantas pessoas você quer gerar?");
        int qtd = input.nextInt();

        try {
            FileReader fileReaderNomes = new FileReader("lista 6 vs/lista/src/Q5/nomes.txt");
            FileReader fileReaderSobrenomes = new FileReader("lista 6 vs/lista/src/Q5/sobrenomes.txt");

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
                pessoas.add(new Pessoa(nome, sobrenome, idade));
            }

            input.close();
            fileReaderNomes.close();
            fileReaderSobrenomes.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Implementação do algoritmo de ordenação por inserção
        for (int i = 1; i < pessoas.size(); i++) {
            Pessoa pessoaTemp = pessoas.get(i); // enésimo nome
            int j = i - 1; // índice de nome anterior ao enésimo nome em comparação

            // Realocação de nomes anteriores para índices imediatamente subsequentes
            while (j >= 0 && pessoas.get(j).compareTo(pessoaTemp) > 0) {
                pessoas.set(j + 1, pessoas.get(j));
                j--;
            }
            // Atribuição de enésimo nome em índice de acordo com sua posição em relação aos
            // nomes anteriores comparados
            pessoas.set(j + 1, pessoaTemp);
        }

        // Imprime cada nome completo na lista
        for (Pessoa pessoa : pessoas) {
            System.out.println("Nome: " + pessoa.getNomeCompleto() + ", Idade: " + pessoa.getIdade());
        }

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("lista 6 vs/lista/src/Q5/pessoas.txt"));
            objectOutputStream.writeObject(pessoas);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
