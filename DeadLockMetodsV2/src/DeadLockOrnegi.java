
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class DeadLockOrnegi {
    private Hesap hesap1 = new Hesap();
    private Hesap hesap2 = new Hesap();
    private Random random = new Random();
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();
    //bir çok işlem yapıcak olduğum için bir metod ile bunu yapmayi tercih ediyorum
    public void lockKontrol(Lock a,Lock b){
        //dönen değerlerimizi boolean bir ifadeye eşitlememiz gerekiyor
        boolean a_elde_edildi = false;
        boolean b_elde_edildi = false;
        //burada a elde edildi fakat b elde edilmediği durumda a yı bırakmamız gerekiyor diğer threade
        //hangi threadimizin bunu bırakacağını işletim sistemi belirleyecek
        while(true){
            //bu yapıdan sadece iki lockuda elde edip çıkabileceğim.
            a_elde_edildi = a.tryLock();
            b_elde_edildi = b.tryLock();//elde edilebilecek durumdalarsa
            
            if(a_elde_edildi ==true && b_elde_edildi == true){
              return ;//dediğimizde bu koşul sağlandığında metod sonlandırılmış oluyor 
            }if(a_elde_edildi ==true){
                a.unlock();
            }if(b_elde_edildi == true){
                b.unlock();
            }
        }
    }
  public void thread1Fonksiyonu(){//threadlerden ikisini aldıysa devam eder birini aldıysa bırakmasını söymeyebileceğimiz bir yapı kuruyorum
        //bu sorunu çözmek için trylock ile çözerim
        lockKontrol(lock1, lock2);
        for(int i = 0;i<5000;i++){
            Hesap.paratransteri(hesap1, hesap2, random.nextInt(250));
        }
        lock1.unlock();
        lock2.unlock();
    }

    public void thread2Fonksiyonu(){
        lockKontrol(lock2, lock1);
        for(int i =0;i<5000;i++){
            
        Hesap.paratransteri(hesap2, hesap1, random.nextInt(250));
        }
        lock1.unlock();
        lock2.unlock();
    }
    public void threadOver(){
        System.out.println("Hesap1 bakiye: "+hesap1.getBakiye()+"\nhesap2 bakiye : "+hesap2.getBakiye());
        System.out.println("Toplam Bakiye : "+(hesap1.getBakiye()+hesap2.getBakiye()));
    }
}
