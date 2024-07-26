public class PunhadoMoedas {
    // valores admitidos para as moedas
    public static final double[] valoresPossiveis = { 0.05, 0.10, 0.25, 0.50, 0.75, 1 };

    // valor de cada moeda (algum dos valores definidos em constante estática
    // anterior)
    private double valor;
    // quantidade de moedas
    private int quantidade;

    // construtor (com validação de valor das moedas)
    public PunhadoMoedas(double valor, int quantidade) {
        // flag para identificar se valor indicado para as moedas é válido
        boolean valorValido = false;

        // iteração entre os valores admitidos para as moedas
        for (int i = 0; i < valoresPossiveis.length; i++)
            // se enésimo valor admitido corresponder ao valor indicado...
            if (valor == valoresPossiveis[i]) {
                valorValido = true; // atualização de flag (valor indicado é válido)
                break; // encerramento de bloco de validação de vaor
            }

        // inicialização de campos de instância com valores indicados
        // se valor indicado para a moeda (na forma de parãmetro do construtor) for
        // válido
        if (valorValido) {
            this.valor = valor;
            this.quantidade = quantidade;
        }
        // inicialização de campos de instância com valores padrões se valor indicado
        // para a moeda (na forma de parãmetro do construtor) não for válido
        else {
            this.valor = 0;
            this.quantidade = 0;
        }
    }

    // retorno de valor de cada moeda
    public double getValor() {
        return valor;
    }

    // retorno de quantidade de moedas
    public int getQuantidade() {
        return quantidade;
    }

    // retorno de valor total do punhado de moedas
    public double getTotalPunhado() {
        return valor * quantidade;
    }
}