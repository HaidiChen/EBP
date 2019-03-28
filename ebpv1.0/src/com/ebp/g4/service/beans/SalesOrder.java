/**
 * 类名：SalesOrder
 * 对应界面内容：查看销售订单界面所需的信息
 */

package com.ebp.g4.service.beans;

public class SalesOrder
{
    private String orderDate;     // 订单时间

    private String orderNumber; // 订单号

    private String goodsName;   // 商品名称

    private float goodsPrice;   // 单价

    private int goodsAmount;    // 数量

    private float goodsShipping;  // 运费

    private float goodsPayFee;  // 实付款

    private String orderStatus; // 交易状态

    public String getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(String orderDate)
    {
        this.orderDate = orderDate;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public String getGoodsName()
    {
        return goodsName;
    }

    public void setGoodsName(String goodsName)
    {
        this.goodsName = goodsName;
    }

    public float getGoodsPrice()
    {
        return goodsPrice;
    }

    public void setGoodsPrice(float goodsPrice)
    {
        this.goodsPrice = goodsPrice;
    }

    public int getGoodsAmount()
    {
        return goodsAmount;
    }

    public void setGoodsAmount(int goodsAmount)
    {
        this.goodsAmount = goodsAmount;
    }

    public float getGoodsShipping()
    {
        return goodsShipping;
    }

    public void setGoodsShipping(float goodsShipping)
    {
        this.goodsShipping = goodsShipping;
    }

    public float getGoodsPayFee()
    {
        return goodsPayFee;
    }

    public void setGoodsPayFee(float goodsPayFee)
    {
        this.goodsPayFee = goodsPayFee;
    }

    public String getOrderStatus()
    {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object obj)
    {
        SalesOrder so = (SalesOrder)obj;
        if ((this.goodsAmount == so.goodsAmount) &&
            ((this.goodsName == null ? "" : this.goodsName).equals(so.goodsName)) &&
            (this.goodsPayFee == so.goodsPayFee) &&
            (this.goodsPrice == so.goodsPrice) &&
            (this.goodsShipping == so.goodsShipping) &&
            ((this.orderDate == null ? "" : this.orderDate).equals(so.orderDate)) &&
            ((this.orderNumber == null ? "" : this.orderNumber).equals(so.orderNumber)) &&
            ((this.orderStatus == null ? "" : this.orderStatus).equals(so.orderStatus))){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString()
    {
        return "SalesOrder [orderDate=" + orderDate + ", orderNumber="
                + orderNumber + ", goodsName=" + goodsName + ", goodsPrice="
                + goodsPrice + ", goodsAmount=" + goodsAmount
                + ", goodsShipping=" + goodsShipping + ", goodsPayFee="
                + goodsPayFee + ", orderStatus=" + orderStatus + "]";
    }
    
    
}
