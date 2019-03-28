/**
 * 类名：GoodsSales
 * 对应界面内容：宝贝销售情况所需的信息
 */

package com.ebp.g4.service.beans;

public class GoodsSales
{
    private String goodsName;   // 商品名称

    private String goodsType;   // 商品类型

    private float goodsPrice;   // 商品价格

    private float goodsShipping;  // 运费

    private String orderNumber; // 订单号

    private String orderDate;     // 交易时间

    private float TotalRevenue; // 总销售量

    @Override
    public String toString()
    {
        return "GoodsSales [goodsName=" + goodsName + ", goodsType=" + goodsType
                + ", goodsPrice=" + goodsPrice + ", goodsShipping="
                + goodsShipping + ", orderNumber=" + orderNumber
                + ", orderDate=" + orderDate + ", TotalRevenue=" + TotalRevenue
                + "]";
    }

    @Override
    public boolean equals(Object obj)
    {
        GoodsSales gs=(GoodsSales)obj;
        if(     this.goodsName.equalsIgnoreCase(gs.goodsName)&&
                this.goodsPrice==gs.goodsPrice&&
                this.goodsShipping==gs.goodsShipping&&
                this.goodsType.equalsIgnoreCase(gs.goodsType)&&
                this.orderDate==gs.orderDate&&
                this.orderNumber.equalsIgnoreCase(gs.orderNumber)&&
                this.TotalRevenue==gs.TotalRevenue){
            return true;
        }
        else{
            return false;
        }
        // TODO Auto-generated method stub
    }

    public String getGoodsName()
    {
        return goodsName;
    }

    public void setGoodsName(String goodsName)
    {
        this.goodsName = goodsName;
    }

    public String getGoodsType()
    {
        return goodsType;
    }

    public void setGoodsType(String goodsType)
    {
        this.goodsType = goodsType;
    }

    public float getGoodsPrice()
    {
        return goodsPrice;
    }

    public void setGoodsPrice(float goodsPrice)
    {
        this.goodsPrice = goodsPrice;
    }

    public float getGoodsShipping()
    {
        return goodsShipping;
    }

    public void setGoodsShipping(float goodsShipping)
    {
        this.goodsShipping = goodsShipping;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public String getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(String orderDate)
    {
        this.orderDate = orderDate;
    }

    public float getTotalRevenue()
    {
        return TotalRevenue;
    }

    public void setTotalRevenue(float totalRevenue)
    {
        TotalRevenue = totalRevenue;
    }
}
