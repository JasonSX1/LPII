import java.util.Arrays;
import java.util.Date;

public class Voo {

    public final static int TAM = 70;

    protected int numero;
    protected Date dataHorario;
    protected boolean[] assentos;



    public Voo(int numero, Date dataHorario){
        this.assentos = new boolean[TAM];
        this.numero = numero;
        this.dataHorario = dataHorario;   
    }

    public int getNumero(){
        return numero;
    }

    public Date getDataHorario(){
        return dataHorario;
    }

    public int getProximoAssentoLivre(){
        for(int i=0; i<assentos.length; i++){
            if (!assentos[i]){
                return i+1;
            } 
        }
        return -1;
    }

    public int getTotalAssentosLivres(){
        int qtd=0;

        for(int i=0; i<assentos.length; i++){
            if(!assentos[i]){
                qtd++;
            } 
        }
        return qtd;
    }

    public double getTaxaOcupacao(){
        int assentosOcupados = assentos.length - getTotalAssentosLivres();

        return assentosOcupados * 100/assentos.length;
    }

    public boolean isAssentoLivre(int n){
        return !assentos[n-1];
    }

    public boolean ocuparAssento(int n){
        if(!isAssentoLivre(n)) {
            return false;
        } else {
            assentos [n-1] = true;
            return true;
        }
    }
    //QUESTÃO 7 - Dado que a classe implementada na questão anterior herda todos os métodos definidos na classe java.lang.Object,
    //sobrescreva um destes métodos: clone. Tal método é projetado para retornar um novo objeto da mesma classe, de modo a
    //haver cópia de todos os valores dos atributos da instância a partir da qual ele é invocado.

    //Forma que o professor fez:
    public Object Clone(){
        // instanciação de novo objeto de Voo com o mesmo número e data e horário
        Voo voo = new Voo(numero, (Date)dataHorario.clone());

        // cópia de valores booleanos para array de assentos de novo objeto
        for (int i = 0; i < assentos.length; i++){
        voo.assentos[i] = assentos[i];
        }

        return voo;
    }

    //forma alternativa
    public Object CloneII(){
        Voo clone = new Voo(this.numero, this.dataHorario);
        clone.assentos = Arrays.copyOf(this.assentos, this.assentos.length);
        return clone;
    }

    //CLONE realiza uma cópia manual dos objetos do array, enquanto cloneII utiliza o método Arrays.copyOf
}
