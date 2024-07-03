public class Filme {
    private String titulo;
    private int duracao;
    private boolean audOriginal;
    private boolean legedado;
    private int precoInteira;
    private int pagantesInteira, pagantesMeia;
    private double bilheteria;
    private int qtdPagantes;
    private int percentualMeiaEntrada;

    public Filme(String titulo, int duracao, boolean audOriginal, boolean legendado, int precoInteira){
        this.titulo = titulo;
        this.duracao = duracao;
        this.audOriginal = audOriginal;
        this.legedado = legendado;
        this.precoInteira = precoInteira;
        this.pagantesInteira = 0;
        this.pagantesMeia = 0;
        this.bilheteria = 0;
        this.qtdPagantes = pagantesInteira + pagantesMeia;
    }

    public String getTitulo(){
        return titulo;
    }

    public int getDuracao(){
        return duracao;
    }

    public boolean getAudOriginal(){
        return audOriginal;
    }

    public boolean getLegenda(){
        return legedado;
    }

    public int getPrecoInteira(){
        return precoInteira;
    }

    public int getPagantesInteira(){
        return pagantesInteira;
    }

    public int getPagantesMeia(){
        return pagantesMeia;
    }

    public void registrarExibicao(int pagantesInteira, int pagantesMeia){
        this.pagantesInteira += pagantesInteira;
        this.pagantesMeia += pagantesMeia;
    }

    public double getBilheteria(){
        return (pagantesInteira * precoInteira + pagantesMeia * precoInteira/2);
        
    }

    public double getPercentualMeiaEntrada(){
        return (pagantesMeia / (pagantesInteira + pagantesMeia) * 100);
    }
}

