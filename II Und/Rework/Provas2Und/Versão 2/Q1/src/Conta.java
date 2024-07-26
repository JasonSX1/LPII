public class Conta {

    //Quando crio uma conta nova, este valor é referente ao último número de conta utilizado
    private static int ultimoNumero = 0;

    private int numero; //Numero da conta
    private String titular; //Nome do titular da conta
    private double saldo; //Saldo atual da conta

    public Conta(String titular){
        this.numero = ultimoNumero+1;
        this.titular = titular;
        this.saldo = 0;
        ultimoNumero++;
    }

    public int getNumero(){
        return numero;
    }

    public String getTitular(){
        return titular;
    }
    public double getSaldo(){
        return saldo;
    }

    /* Operações de sque, com atualização de saldo atual e returno de valor booleano true em caso de exito */

    public boolean sacar(double saque){
        if(saldo>=saque){
            saldo -= saque;
            return true;
        }
        else {
            return false;
        }
    }

    //Operação de deposito com atualização de saldo atual
    public void depositar(double deposito){
        saldo += deposito;
    }

    //Descrição de dados do objeto instanciado
    public String toString(){
        return "Conta [Número: " + numero + ", Titular: " + titular + ", Saldo Atual: " + saldo + "]";
    }
    
}
