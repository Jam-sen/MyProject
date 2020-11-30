package com.ys.service.impl;

import com.ys.dao.GoodsDao;
import com.ys.dao.SaleDao;
import com.ys.domain.Goods;
import com.ys.domain.Sale;
import com.ys.exception.NotEnoughException;
import com.ys.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ServiceImpl implements Service {
    @Autowired
    SaleDao saleDao;

    @Autowired
    GoodsDao goodsDao;

    /**
     * rollbackFor:表示发生指定的异常一定回滚
     *      处理逻辑是：
     *          1)spring框架会首先检查方法抛出的异常是不是在rollbackFor中的，如果异常在rollbackFor列表中，不管是什么类型的异常，一定会回滚
     *          2)如果你抛出的异常不在rollbackFor列表中，spring会判断异常是不是RuntimeException ，如果是一定回滚。
     */
    /*@Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            readOnly = false,
            rollbackFor = {
                    NullPointerException.class,
                    NotEnoughException.class
            }
    )*/
    @Transactional
    @Override
    public String buy(int goodsId, int count) throws NotEnoughException {
        System.out.println ("==buy方法开始==");
        //记录销售信息
        Sale sale = new Sale ();
        sale.setGid (goodsId);
        sale.setNums (count);
        saleDao.insertSale (sale);
        //更新商品库存数量
        Goods goods = goodsDao.selectGoods (goodsId);
        if (goods == null) {
            throw new NullPointerException ("商品不存在");
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
