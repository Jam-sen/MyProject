package com.ys.service;

import com.ys.domain.Goods;
import com.ys.domain.Sale;
import com.ys.exception.NotEnoughException;

public interface Service {
    String buy(int goodsId,int count) throws NotEnoughException;

}
