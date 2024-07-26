public class Data {
    int mes;
    int dia;
    int ano;

    public Data(int dia, int mes, int ano){
        this.mes = mes;
        this.dia = dia;
        this.ano = ano;
    }

    public String mostrarData(){
        //return ("Sua data é: (DD/MM/AAAA) " + dia + "/" + mes + "/" + ano);
        return (dia < 10 ? "0" : "") + dia + "/" + (mes < 10 ? "0" : "") + mes + "/" + ano;
    }

    public int getDia(){
        return dia;
    }

    public void setDia(int dia){
        this.dia = dia;
    }

    public int getMes(){
        return mes;
    }

    public void setMes(int mes){
        this.mes = mes;
    }

    public int getAno(){
        return ano;
    }

    public void setAno(int ano){
        this.ano = ano;
    } 
    
    public int getDiasTerminoAno(){
        int[] qtdDias = new int [] {31,28,31,30,31,30,31,31,30,31,30,31}; // número de dias por mês
        if (((ano % 400 == 0 ) || ((ano % 4 == 0 ) && (ano % 100 != 0))))
            qtdDias[1]++; // se bissexto, fevereiro tem 29 dias, então é adicionado 1 na posição 1 do array, que representa fevereiro
        
        if (mes == 12) {
            return qtdDias[11] - dia; // dias restantes no mês atual
        } else {
            int diasRestantes = qtdDias[mes-1] - dia; // dias restantes no mês atual
            for (int i = mes; i < 12; i++)
                diasRestantes += qtdDias[i]; // adicionar dias dos meses restantes no ano
            return diasRestantes;
        }
    }
    
    
}
