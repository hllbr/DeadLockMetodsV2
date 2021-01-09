
import java.util.logging.Level;
import java.util.logging.Logger;


public class Hesap {
    private int bakiye = 10000;
    
    public void paracek(int miktar){
        bakiye-= miktar;
    }
    public void parayatır(int miktar){
        bakiye += miktar;
    }
    public static void paratransteri(Hesap hesap1,Hesap hesap2,int miktar){
        hesap1.paracek(miktar);
        hesap2.parayatır(miktar);
      
    }

    public int getBakiye() {
        return bakiye;
    }

    public void setBakiye(int bakiye) {
        this.bakiye = bakiye;
    }
    
    
}
