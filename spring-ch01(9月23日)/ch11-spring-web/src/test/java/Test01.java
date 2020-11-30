import org.junit.Test;

import java.io.*;
import java.util.*;

public class Test01 implements Serializable{

    @Test
    public void test01(){
        Map map = new HashMap ();
        map.put (1,10);
        map.put (2,20);
        map.put (3,30);
        Set<Integer> keys = map.keySet ();
        for(Iterator iterator = keys.iterator ();iterator.hasNext ();){
            Integer key = (Integer) iterator.next ();
            System.out.println (key+"-->"+map.get (key));
        }

        String path = Thread.currentThread ().
                getContextClassLoader ().
                getResource ("ApplicationContext.xml").
                getPath ();
        System.out.println (path);
    }

    @Test
    public void test02() throws IOException {
        File file = new File ("D:\\Program Files\\123.txt");
        ObjectOutputStream objectOutputStream = null;
        objectOutputStream = new ObjectOutputStream (new FileOutputStream (file));
        Test01 test01 = new Test01 ();
        Objects.requireNonNull (objectOutputStream).writeObject (test01);
    }

    @Override
    public String toString() {
        return "我是Test01";
    }
}
