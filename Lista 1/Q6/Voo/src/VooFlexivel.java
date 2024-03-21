import java.util.Date;
import java.util.Scanner;

public class VooFlexivel extends Voo {
    
    Scanner scanner = new Scanner(System.in);

    protected int qtdMax = scanner.nextInt();

    public VooFlexivel(int numero, Date dataHorario,  int qtdMax){
        super(numero, dataHorario);
        this.qtdMax = qtdMax;
        this.assentos = new boolean[qtdMax];
    }

    public int getTotalAssentos(){
        return assentos.length;
    }


    
}
