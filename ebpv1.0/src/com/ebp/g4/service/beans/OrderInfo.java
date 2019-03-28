/**
 * 类名：OrderInfo
 * 对应界面内容：查看订单界面的所需信息
 */

package com.ebp.g4.service.beans;

import java.util.Date;

public class OrderInfo
{
    private String orderID;     // 订单编号

    private float goodsPrice;   // 商品单价

    private int numbers;        // 数量

    private String goodsName;   // 商品名称

    private String store;       // 店铺名称

    private String orderDate;     // 交易时间

    private float carriage;     // 运费

    private float thePay;       // 实付款

    private String status;      // 交易状态

    public String getOrderID()
    {
        return orderID;
    }

    public void setOrderID(String orderID)
    {
        this.orderID = orderID;
    }

    public float getGoodsPrice()
    {
        return goodsPrice;
    }

    public void setGoodsPrice(float goodsPrice)
    {
        this.goodsPrice = goodsPrice;
    }

    public int getNumbers()
    {
        return numbers;
    }

    public void setNumbers(int numbers)
    {
        this.numbers = numbers;
    }

    public String getGoodsName()
    {
        return goodsName;
    }

    public void setGoodsName(String goodsName)
    {
        this.goodsName = goodsName;
    }

    public String getStore()
    {
        return store;
    }

    public void setStore(String store)
    {
        this.store = store;
    }

    public String getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(String orderDate)
    {
        this.orderDate = orderDate;
    }

    public float getCarriage()
    {
        return carriage;
    }

    public void setCarriage(float carriage)
    {
        this.carriage = carriage;
    }

    public float getThePay()
    {
        return thePay;
    }

    public void setThePay(float thePay)
    {
        this.thePay = thePay;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj)
    {
        OrderInfo oi = (OrderInfo)obj;
        if (this.carriage == oi.carriage &&
            (this.goodsName == null ? "" : this.goodsName).equals(oi.goodsName) &&
            this.goodsPrice == oi.goodsPrice &&
            this.numbers == oi.numbers &&
            (this.orderDate == null ? "" : this.orderDate).equals(oi.orderDate) &&
            (this.orderID == null ? "" : this.orderID).equals(oi.orderID) &&
            (this.status == null ? "" : this.status).equals(oi.status) &&
            (this.store == null ? "" : this.store).equals(oi.store) &&
            this.thePay == oi.thePay){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString()
    {
        return "OrderInfo [orderID=" + orderID + ", goodsPrice=" + goodsPrice
                + ", numbers=" + numbers + ", goodsName=" + goodsName
                + ", store=" + store + ", orderDate=" + orderDate
                + ", carriage=" + carriage + ", thePay=" + thePay + ", status="
                + status + "]";
    }

}
