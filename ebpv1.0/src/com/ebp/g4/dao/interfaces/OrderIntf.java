package com.ebp.g4.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.ebp.g4.dao.beans.Order;

public interface OrderIntf
{
    boolean selectOrder(String orderid);//查询订单是否存在
    boolean deleteOrder(String orderid);//删除订单
    boolean addOrder(Order order);//添加订单
    boolean updateOrder(Order order);//更新订单信息
    Order selectOrderById(String orderid);//通过id查找订单，以对象的形式返回信息
    List<Order> selectAllOrder();//查找订单，以列表的形式返回所有信息
    List<Order> selectAllOrderByDate(String date);   //通过订单时间返回订单列表
    List<Order> selectAllOrderByGoodsId(String goodsId);   //通过商品号返回订单列表
    List<Order> selectAllOrderByUserId(String userId);  //通过用户id返回订单类别 
}
