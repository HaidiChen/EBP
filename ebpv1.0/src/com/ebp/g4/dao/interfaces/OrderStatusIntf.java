package com.ebp.g4.dao.interfaces;

import java.util.List;

import com.ebp.g4.dao.beans.OrderStatus;

public interface OrderStatusIntf
{
    List<OrderStatus> selectAllOrderStatus();//查找订单状态信息，以列表的形式返回所有信息

    OrderStatus selectStatusById(String stateid);

    OrderStatus selectStatusByName(String name);
}
