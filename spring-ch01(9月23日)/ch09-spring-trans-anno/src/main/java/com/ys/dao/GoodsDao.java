package com.ys.dao;

import com.ys.domain.Goods;
import com.ys.domain.Sale;
import org.apache.ibatis.annotations.Param;

public interface GoodsDao {
    //更新商品库存量
    void updateGoods(Goods goods);

    //查询商品信息
    Goods selectGoods(int id);
}
