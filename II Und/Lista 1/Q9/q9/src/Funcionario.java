public class Funcionario {

    private String nome;
    private String sobrenome;
    private double QuantidadeHoras;
    private double ValorHora;

    public Funcionario(String nome, String sobrenome, double ValorHora){
        this.nome = nome;
        this.sobrenome = nome;
        this.ValorHora = ValorHora;
        this.QuantidadeHoras = 0;
    }

    public String getNome(){
        return nome;
    }

    public String getSobrenome(){
        return sobrenome;
    }

    public double getValorHora(){
        return ValorHora;
    }
    
    public double getQuantidadeHoras(){
        return QuantidadeHoras;
    }
    
    public void adicionarCargaHoraria(int horas){
        QuantidadeHoras+=horas;
    }

    public double getSalarioLiquido(){
        double salario = QuantidadeHoras * ValorHora;

        double teto1 = 1212.00;
        double teto2 = 2427.35;
        double teto3 = 3641.03;
        double teto4 = 7087.22;

        double baseCalculo1 = salario < teto1 ? salario : teto1;

        double baseCalculo2 = salario < teto1 ? 0 :

        salario >= teto2 ? teto2 - teto1 : salario - teto1;

        double baseCalculo3 = salario < teto2 ? 0 :

        salario >= teto3 ? teto3 - teto2 : salario - teto2;

        double baseCalculo4 = salario < teto3 ? 0 :
        
        salario >= teto4 ? teto4 - teto3 : salario - teto3;

        // somatório de descontos obtidos por base de cálculo de cada faixa salarial
        double desconto = baseCalculo1 * 7.5/100 + baseCalculo2 * 9.0/100 + baseCalculo3 * 12.0/100 + baseCalculo4 * 14.0/100;

        return salario - desconto; // retorno de salário líquido
        }
}
