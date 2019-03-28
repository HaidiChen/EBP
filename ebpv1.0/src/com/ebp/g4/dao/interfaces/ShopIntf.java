package com.ebp.g4.dao.interfaces;

import java.util.List;

import com.ebp.g4.dao.beans.Shop;

public interface ShopIntf
{
    boolean selectShop(String shopid);//查询店铺是否存在
    boolean deleteShop(String shopid);//删除店铺
    boolean addShop(Shop shop);//添加店铺
    boolean updateShop(Shop shop);//更新店铺信息
    Shop selectShopById(String shopid);//通过id查找店铺，以对象的形式返回信息
    List<Shop> selectAllShop( );//查找店铺，以列表的形式返回所有信息
    Shop selectShopByName(String newStore);

}
