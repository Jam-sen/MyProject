import org.junit.Test;

public class Test1 {
    @Test
    public void test1() {
        String ip = "127.0.0.1";
        String allowIps = "192.168.1.1,192.168.1.2";
        System.out.println(ip.contains(allowIps));
    }
}
   

