/**
 * 接口名：OrderServiceIntf
 * 功能：提供订单相关的方法
 */

package com.ebp.g4.service.interfaces;

import java.util.List;

import com.ebp.g4.service.beans.CartGoods;
import com.ebp.g4.service.beans.OrderDetail;
import com.ebp.g4.service.beans.OrderInfo;
import com.ebp.g4.service.beans.SalesOrder;

public interface OrderServiceIntf
{
    /**
     * 方法名：getOrderDetailsByOrderNumber
     * 参数1：String number(订单编号)
     * 返回值：OrderDetail
     * 功能：获取订单详细信息
     */
    OrderDetail getOrderDetailsByOrderNumber(String number);
    
    /**
     * 方法名：updateOrderInfo
     * 参数1：String orderNumber(订单编号)
     * 参数2：OrderInfo newOrderInfo
     * 返回值：boolean
     * 功能：对订单信息进行修改，修改成功，返回true，否则返回false
     */
    boolean updateOrderInfo(String orderNumber, OrderInfo newOrderInfo);
    
    /**
     * 方法名：getAllOrderInfoByAccount
     * 参数：String account
     * 返回值：List<OrderInfo>
     * 功能：获取用户账号下所有的订单信息
     */
    List<OrderInfo> getAllOrderInfoByAccount(String account);
    
    /**
     * 方法名：getSalesOrderInfoByOrderNumber
     * 参数1：String number
     * 返回值：SalesOrder
     * 功能：根据订单编号获取销售订单的信息
     */
    SalesOrder getSalesOrderInfoByOrderNumber(String number);
    
    /**
     * 方法名：getSalesOrderInfoListByDate
     * 参数1：Date date
     * 返回值：List<SalesOrder>
     * 功能：根据订单的日期获取该日期下所有的销售订单的信息
     */
    List<SalesOrder> getSalesOrderInfoListByDate(String date);
    
    /**
     * 方法名：getAllSalesOrderInfoByStoreName
     * 参数：String storeName
     * 返回值：List<SalesOrder>
     * 功能：获取所有的销售订单信息
     */
    List<SalesOrder> getAllSalesOrderInfoByStoreName(String storeName);
    
    /**
     * 方法名：getOrderInfoPropertyNames
     * 参数：无
     * 返回值：List<String>
     * 功能：获取所有的订单信息表格的每一列的列名
     */
    List<String> getOrderInfoPropertyNames();
    
    /**
     * 方法名：getSalesOrderPropertyNames
     * 参数：无
     * 返回值：List<String>
     * 功能：获取所有的销售订单信息表格的每一列的列名
     */
    List<String> getSalesOrderPropertyNames();
    
    /**
     * 方法名：createOrder
     * 参数：String account, CartGoods goods, String time
     * 返回值：boolean
     * 功能：生成新订单
     */
    boolean createOrder(String account, CartGoods goods, String time);
    
    /**
     * 方法名：updateSalesOrderStatus
     * 参数：String orderNumber, String newStatus
     * 返回值：boolean
     * 功能：更新销售订单的交易状态
     */
    boolean updateSalesOrderStatus(String orderNumber, String newStatus);
}
