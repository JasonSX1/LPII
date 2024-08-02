import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.imageio.IIOException;

public class App {
    public static void main(String[] args) throws Exception {
        final static String caminhoArquivo = "lista-func-obj.txt";
        static Collection<Funcionario> lista = new ArrayList<>()

        //Para salvar um arquivo devo criar uma string correspondente ao caminho que sera feito o salvamento do arquivo
        //Uso também uma classe FileWriter
        //Cobrar de xandao as postagens da aula 13 e da aula 14
        //Utilizamos o mesmo scanner default para ler um arquivo, onde ao inves de utilizar um objeto system,in, utilizamos um objeto da classe filereader
        //Na aula anterior ele fez o metodo de escrita em aruivo printwriter
        //Professor recomendou modularizar mais o código

        public static void lerArquivo(){
            FileInputStreeam fluxoLeitura = null;
            ObjectOutputStream fluxoLeituraObjetos = null;

            try{
                fluxoLeitura = new FileInputStream(caminhoArquivo);
                fluxoLeituraObjetos = new objectInputStream(fluxoLeitura);

                lista = (Collection<Funcionario>)fluxoLeituraObjetos.readObject();

                if(fluxoLeituraObjetos != null)
                    fluxoLeituraObjetos.close();
            
            } catch(Exception e){
                System.out.println("Linha %d de arquivo não processada\n", i);
            }

        }

        public static void escreverArquivo(){
            FileOutputStream fluxoEscrita;
            ObjectOutputStream fluxoEscritaObjetos;
            try{
                fluxoEscrita = new FileOutputStream(caminhoArquivo);
                fluxoEscritaObjetos = new ObjectOutputStream(fluxoEscrita);

                fluxoEscritaObjetos.writeObject(lista);

                fluxoEscritaObjetos.close();
                fluxoEscrita.close();
            }catch(IIOException e){
                e.printStackTrace();
            }
        }

        public static void menu(){

        }
    }
}
