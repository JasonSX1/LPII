public class Circulo {

    private double raio;

    public Circulo(Double raio){
        super();
        this.raio = raio;
    }

    public double getRaio(){
        return raio;
    }

    public void setRaio(double raio){
        this.raio = raio;
    }

    public double area(){
        return Math.PI * raio * raio;
    }
}
