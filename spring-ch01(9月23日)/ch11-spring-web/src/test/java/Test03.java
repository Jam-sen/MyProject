import org.junit.Test;

import java.math.BigDecimal;

public class Test03 {
    @Test
    public void test03(){
        Integer i = 1;
        Runnable r1 = new A(i);
        Thread t1 = new Thread (r1,"t1");
        Thread t2 = new Thread (r1,"t1");
        t1.start ();
        t2.start ();
    }

}
class A implements Runnable{
    Integer i;
    public A(Integer i){
        this.i = i;
    }
    @Override
    public void run() {
        synchronized (this){
            System.out.println (++i);
        }
    }
}
