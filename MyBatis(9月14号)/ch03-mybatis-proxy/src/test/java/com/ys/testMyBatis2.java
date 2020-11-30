package com.ys;

import org.junit.Test;

import java.util.ArrayList;

public class testMyBatis2 {
    @Test
    public void testSql(){
        String sql = "select *from student where";
        StringBuilder stringBuilder = new StringBuilder ();
        stringBuilder.append (sql+"(");
        ArrayList<Integer> list = new ArrayList<> ();
        list.add (1001);
        list.add (1002);
        list.add (1003);
        list.add (1004);
        for (Integer integer:list) {
            stringBuilder.append (integer+",");
        }
        stringBuilder.deleteCharAt (stringBuilder.length ()-1).append (")");
        System.out.println (stringBuilder);
    }
}
