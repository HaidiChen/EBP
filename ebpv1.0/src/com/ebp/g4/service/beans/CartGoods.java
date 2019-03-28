/**
 * 类名：CartGoods
 * 对应界面内容：购物车的一条商品信息
 */

package com.ebp.g4.service.beans;

public class CartGoods
{
    private String shopName;    // 店铺名称
    
    private String goodsName;   // 商品名称

    private String goodsInfo;   // 商品信息

    private float goodsPrice;     // 单价

    private int goodsNumber;    // 数量

    private float money;          // 金额

    @Override
    public boolean equals(Object obj)
    {
        CartGoods cg=(CartGoods)obj;
        if(     (this.goodsInfo==null?"":this.goodsInfo).equalsIgnoreCase(cg.goodsInfo)&&
                (this.goodsName==null?"":this.goodsName).equalsIgnoreCase(cg.goodsName)&&
                this.goodsNumber==cg.goodsNumber&&
                this.goodsPrice==cg.goodsPrice&&
                this.money==cg.money&&
                (this.shopName==null?"":this.shopName).equalsIgnoreCase(cg.shopName)
                )
        {return true;}
        else{
            return false;
        }
    }
    

    @Override
    public String toString()
    {
        return "CartGoods [shopName=" + shopName + ", goodsName=" + goodsName
                + ", goodsInfo=" + goodsInfo + ", goodsPrice=" + goodsPrice
                + ", goodsNumber=" + goodsNumber + ", money=" + money + "]";
    }


    public String getShopName()
    {
        return shopName;
    }

    public void setShopName(String shopName)
    {
        this.shopName = shopName;
    }

    public String getGoodsName()
    {
        return goodsName;
    }

    public void setGoodsName(String goodsName)
    {
        this.goodsName = goodsName;
    }

    public String getGoodsInfo()
    {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo)
    {
        this.goodsInfo = goodsInfo;
    }

    public float getGoodsPrice()
    {
        return goodsPrice;
    }

    public void setGoodsPrice(float goodsPrice)
    {
        this.goodsPrice = goodsPrice;
    }

    public int getGoodsNumber()
    {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber)
    {
        this.goodsNumber = goodsNumber;
    }

    public float getMoney()
    {
        return money;
    }

    public void setMoney(float money)
    {
        this.money = money;
    }

}
