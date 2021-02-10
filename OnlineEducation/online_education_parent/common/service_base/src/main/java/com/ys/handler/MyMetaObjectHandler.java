package com.ys.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        try {
            this.setFieldValByName("gmtCreate", simpleDateFormat.parse(date), metaObject);
            this.setFieldValByName("gmtModified",simpleDateFormat.parse(date), metaObject);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        try {
            this.setFieldValByName("gmtModified", simpleDateFormat.parse(date), metaObject);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
