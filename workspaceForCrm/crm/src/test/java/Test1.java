import com.ys.crm.util.MD5Util;
import org.junit.Test;

public class Test1 {
    @Test
    public void test1() {
        String ip = "127.0.0.1";
        String allowIps = "192.168.1.1,192.168.1.2";
        System.out.println(ip.contains(allowIps));
    }

    @Test
    public void test2() {
        String str = MD5Util.getMD5("11111111111111111111111111111111111111");
        System.out.println(str);

    }
}
