public class Data {

    private int mes;
    private int dia;
    private int ano;

    public Data(int mes, int dia, int ano){
        this.mes = mes;
        this.dia = dia;
        this.ano = ano;
    }

    public int getMes(){
        return mes;
    }

    public void setMes(int mes){
        this.mes = mes;
    }

    public int getDia(){
        return dia;
    }

    public void setDia(int dia){
        this.dia = dia;
    }

    public void setAno(int ano){
        this.ano = ano;
    }

    public String mostrarData(){
        return String.format("%02d/%02d/%04d", dia, mes, ano);
   }

   public int getDiasTerminoAno() {
    int diasNoAno;
    
    // Verifica se o ano é bissexto
    if (ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0)) {
        diasNoAno = 366; // Ano bissexto
    } else {
        diasNoAno = 365; // Ano não bissexto
    }
    
    // Calcula os dias restantes no ano
    int diasPassados = calcularDiasPassados();
    return diasNoAno - diasPassados;
    }

    private int calcularDiasPassados() {
        int[] diasNoMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int diasPassados = dia;
    
        for (int i = 0; i < mes - 1; i++) {
            diasPassados += diasNoMes[i];
        }
    
        if (mes > 2 && isAnoBissexto()) {
            diasPassados++;
        }
    
        return diasPassados;
    }

    private boolean isAnoBissexto() {
        return (ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0));
    }

    
}
