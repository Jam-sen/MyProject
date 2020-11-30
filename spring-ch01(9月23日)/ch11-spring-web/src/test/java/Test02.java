import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Test02 {
    @Test
    public void test02() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream (new FileInputStream ("D:\\Program Files\\123.txt"));
        Test01 test01 = (Test01) objectInputStream.readObject ();
        test01.test01 ();
    }
}
