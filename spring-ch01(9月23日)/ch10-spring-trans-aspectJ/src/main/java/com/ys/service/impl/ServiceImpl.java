package com.ys.service.impl;

import com.ys.dao.GoodsDao;
import com.ys.dao.SaleDao;
import com.ys.domain.Goods;
import com.ys.domain.Sale;
import com.ys.exception.NotEnoughException;
import com.ys.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceImpl implements Service {
    @Autowired
    SaleDao saleDao;

    @Autowired
    GoodsDao goodsDao;


    @Override
    public String buy(int goodsId, int count) {
        System.out.println ("==buy方法开始==");
        //记录销售信息
        Sale sale = new Sale ();
        sale.setGid (goodsId);
        sale.setNums (count);
        saleDao.insertSale (sale);
        //更新商品库存数量
        Goods goods = goodsDao.selectGoods (goodsId);
        if (goods == null) {
            throw new NullPointerException (("商品不存在"));
        }else if(goods.getAmount ()<count) {
            throw new NotEnoughException ("商品库存不足");
        }
        Goods buyGoods = new Goods ();
        buyGoods.setId (goodsId);
        buyGoods.setAmount (count);
        goodsDao.updateGoods (buyGoods);
        System.out.println ("==buy方法结束==");
        return "记录添加成功";

    }


}
