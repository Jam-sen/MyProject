package com.ys.eduservice.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    @Test
    public void testWirte(){
        //实现excel写操作
        //1.设置写入文件夹地址和excel文件名称
        String filename = "/Users/apple/Desktop/write.xlsx";
        //2.调用easyexcel里面的方法实现写操作
        EasyExcel.write(filename, DemoData.class).sheet("学生列表").doWrite(getData());
    }

    private static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            DemoData demoData = new DemoData();
            demoData.setSname("张三"+i);
            demoData.setSno(i);
            list.add(demoData);
        }
        return list;
    }

    @Test
    public void testRead() {
        String filename = "/Users/apple/Desktop/write.xlsx";
        EasyExcel.read(filename, DemoData.class, new ExcelListener()).sheet("学生列表").doRead();
    }
}
