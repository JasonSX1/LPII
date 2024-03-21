public class Produto {

    private String nome;
    private String undMedida;
    private int estoqueInicial;
    private double preco;
    private int qtdVendas;
    
    public Produto(String nome, String undMedida, int estoqueInicial, double preco, int qtdVendas){
        this.nome = nome;
        this.undMedida = undMedida;
        this.estoqueInicial = estoqueInicial;
        this.preco = preco;
        this.qtdVendas = qtdVendas;   
    }

    public String getNome(){
        return nome;
    }

    public String getUndMedida(){
        return undMedida;
    }

    public int getEstoqueInicial(){
        return estoqueInicial;
    }

    public double getPreco(){
        return preco;
    }

    public int qtdVendas(){
        return qtdVendas;
    }

    public double getEstoqueDisponivel(){
        double estoqueDisponivel = estoqueInicial - qtdVendas;
        return estoqueDisponivel;
    }

    public boolean adicionarVenda(double qtd){ //metodo vender
        if((estoqueInicial-qtdVendas)>= qtd){
            qtdVendas += qtd;
            return true;
        } else {
            return false;
        }
    }

    public double getFaturamentoVenda(){
        return qtdVendas * preco;
    }

    public boolean isDisponivel() {
        return estoqueInicial > 0;
    }

    public double getPercentEstoqueDisponivel(){
        return (estoqueInicial - qtdVendas) / estoqueInicial * 100;
    }


}
