package com.ys.mybatisplus;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class test {
    @Test
    public void testBubbleSort() {
        int[] data = {23, 43, 23, 12, 23, 64, 86, 26, 2, 36, 68, 3, 95, 9, 13454, 443, 90};
        for (int j = data.length - 1; j > 0; j--) {
            for (int i = 0; i < data.length - 1; i++) {
                int count = 0;
                if (data[i] > data[i + 1]) {
                    count = data[i + 1];
                    data[i + 1] = data[i];
                    data[i] = count;
                }
            }
        }
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + ",");
        }
    }

    @Test
    public void test() {
        int[] arr = {2, 3, 9, 12, 23, 23, 23, 26, 36, 43, 64, 68, 86, 90, 95, 443, 13454};
        int key = 32;
        int start = 0, end = arr.length - 1;
        while (start <= end) {
            int middle = (start + end) / 2;
            if (key < arr[middle]) {
                end = middle - 1;
            } else if (key > arr[middle]) {
                start = middle + 1;
            } else {
                System.out.println(middle);
                return;
            }
        }

    }


    @Test
    public void test1() {
        int[] data = {2, 3, 9, 12, 23, 23, 23, 26, 36, 43, 64, 68, 86, 90, 95, 443, 1345};
        int start = 0, end = data.length - 1;
        int key = 12;
        while (start <= end) {
            int middle = (end + start) / 2;
            if (data[middle] < key) {
                start = ++middle;
            } else if (data[middle] > key) {
                end = --middle;
            } else {
                System.out.println(middle);
                return;
            }
        }

    }

    @Test
    public void test2() {
        Map map = new TreeMap();
        map.put("1003", "王五");
        map.put("1001", "张三");
        map.put("1002", "李四");
        for (Iterator iter = map.entrySet().iterator(); iter.hasNext(); ) {
            Map.Entry entry = (Map.Entry) iter.next();
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
    }

}
