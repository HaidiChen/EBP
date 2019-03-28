package com.ebp.g4.dao.interfaces;

import java.util.List;

import com.ebp.g4.dao.beans.Goods;

public interface GoodsIntf
{
    boolean selectGoods(String goodsid);//查询商品是否存在
    boolean deleteGoods(String goodsid);//删除商品信息
    boolean addGoods(Goods goods);//添加商品
    boolean updateGoods(Goods goods);//更新商品信息
    Goods selectGoodsById(String goodsid);//通过id查找商品，以对象的形式返回信息
    List<Goods> selectAllGoods();//查找商品，以列表的形式返回所有信息
    Goods selectGoodsByName(String newGoodsName); //通过名字来返回物品

}
