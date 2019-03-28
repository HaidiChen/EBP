package com.ebp.g4.dao.interfaces;

import java.util.List;

import com.ebp.g4.dao.beans.Cart;

public interface CartIntf
{
    //boolean selectCart(String accountid);//查询购物车是否存在
    boolean deleteCart(String accountid,String goodsid);//删除购物车
    boolean addCart(Cart cart);//添加购物车
    boolean updateCart(Cart cart);//更新购物车信息
    Cart selectCartById(String Accountid,String goodsid);//通过id查找购物车，以对象的形式返回信息
    List<Cart> selectAllCart(String accountid);//查找购物车，以列表的形式返回所有信息

}
