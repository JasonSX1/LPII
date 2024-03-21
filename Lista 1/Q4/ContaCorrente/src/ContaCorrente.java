public class ContaCorrente {
    protected double saldo;

    public ContaCorrente(double saldo){
        this.saldo = saldo;
    }

    public double getSaldo(){
        return saldo;
    }

    public boolean registrarDeposito(double valor){
        if (valor<=0){
            return false;
        } else {
        saldo += valor;
        return true;
    }
}

    public boolean registrarSaque(double valor){
        if (valor<=0){
            return false;
        } else {
            //calculo da tarifa de 5% do valor do saque
            double tarifa = valor * 5/100;

            // verificação de saldo negativo após dedução de saque e tarifa
            if (saldo < valor + tarifa){
            return false;

            }else {
            // atualização de saldo deduzindo-se saque e tarifa
            saldo -= (valor + tarifa);
            return true;
        }
    }
}
}
