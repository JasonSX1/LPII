public class ContaCorrenteEspecial extends ContaCorrente {

    

    public ContaCorrenteEspecial(double saldo){
        super(saldo);
    }

    //sobrescrita do método de débito
    public boolean registrarSaque(double valor){
        if (valor<=0) {
            return false;
        } else {
            double tarifa = valor * 0.1/100;
        
        if (saldo<valor+tarifa){
            return false;
        } else {
            saldo-= (valor+tarifa);
            return true;
        }



    }
}
    
}
