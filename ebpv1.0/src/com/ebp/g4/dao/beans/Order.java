package com.ebp.g4.dao.beans;

public class Order
{
    private String orderid;//订单编号
    private String goodsid;//商品编号
    private String num;//商品数量
    private String shopid;//店铺编号
    private String transprice;//运费
    private String totalprice;//总价
    private String time;//订单时间
    private String stateid;//订单交易状态
    private String userId; //用户id
    public String getOrderid()
    {
        return orderid;
    }
    public void setOrderid(String orderid)
    {
        this.orderid = orderid;
    }
    public String getGoodsid()
    {
        return goodsid;
    }
    public void setGoodsid(String goodsid)
    {
        this.goodsid = goodsid;
    }
    public String getNum()
    {
        return num;
    }
    public void setNum(String num)
    {
        this.num = num;
    }
    public String getShopid()
    {
        return shopid;
    }
    public void setShopid(String shopid)
    {
        this.shopid = shopid;
    }
    public String getTransprice()
    {
        return transprice;
    }
    public void setTransprice(String transprice)
    {
        this.transprice = transprice;
    }
    public String getTotalprice()
    {
        return totalprice;
    }
    public void setTotalprice(String totalprice)
    {
        this.totalprice = totalprice;
    }
    public String getTime()
    {
        return time;
    }
    public void setTime(String time)
    {
        this.time = time;
    }
    public String getStateid()
    {
        return stateid;
    }
    public void setStateid(String stateid)
    {
        this.stateid = stateid;
    }
    public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    @Override
    public String toString()
    {
        return "Order [orderid=" + orderid + ", goodsid=" + goodsid + ", num="
                + num + ", shopid=" + shopid + ", transprice=" + transprice
                + ", totalprice=" + totalprice + ", time=" + time + ", stateid="
                + stateid + ", userId=" + userId + "]";
    }
    
}
