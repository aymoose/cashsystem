package com.mos.service;

import com.mos.dao.GoodsDao;
import com.mos.entity.Goods;

import java.util.List;

public class GoodsService {

    private GoodsDao goodsDao;

    public GoodsService() {
        this.goodsDao = new GoodsDao();
    }

    public boolean putAwayGoods(Goods goods) {
        return this.goodsDao.insertGoods(goods);
    }

    public boolean soldOutGoods(int goodsId) {
        return this.goodsDao.deleteGoods(goodsId);
    }

    public boolean updateGoods(Goods goods) {
        return this.goodsDao.modifyGoods(goods);
    }

    public Goods getGoods(int goodsId) {
        return this.goodsDao.queryGoodsById(goodsId);
    }

    public List<Goods> queryAllGoods() {
        return this.goodsDao.queryAllGoods();
    }

    public  boolean updateGoodsAfterPay(Goods goods,int goodsNum) {
        return this.goodsDao.updateGoodsAfterPay(goods,goodsNum);
    }
}
