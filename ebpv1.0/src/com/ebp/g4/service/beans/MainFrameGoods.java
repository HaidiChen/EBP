/**
 * 类名：MainFrameGoods
 * 对应界面内容：主界面的表格内的商品信息
 */

package com.ebp.g4.service.beans;

public class MainFrameGoods
{
    private String goodsName;   // 商品名称

    private String goodsType;   // 商品类型

    private float goodsPrice;   // 商品价格

    private int goodsSalesAmount;// 销量

    private int goodsStock;         //库存

    private String goodStoreName;   // 店铺名称

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

    public int getGoodsSalesAmount()
    {
        return goodsSalesAmount;
    }

    public void setGoodsSalesAmount(int goodsSalesAmount)
    {
        this.goodsSalesAmount = goodsSalesAmount;
    }

    public int getGoodsStock()
    {
        return goodsStock;
    }

    public void setGoodsStock(int goodsStock)
    {
        this.goodsStock = goodsStock;
    }

    public String getGoodStoreName()
    {
        return goodStoreName;
    }

    public void setGoodStoreName(String goodStoreName)
    {
        this.goodStoreName = goodStoreName;
    }

    @Override
    public boolean equals(Object obj)
    {
        MainFrameGoods mfg = (MainFrameGoods)obj;
        if (this.goodsStock == mfg.goodsStock && 
            (this.goodsName == null ? "" : this.goodsName).equalsIgnoreCase(mfg.goodsName) &&
            this.goodsPrice == mfg.goodsPrice &&
            this.goodsSalesAmount == mfg.goodsSalesAmount &&
            (this.goodStoreName == null ? "" : this.goodStoreName).equalsIgnoreCase(mfg.goodStoreName) &&
            (this.goodsType == null ? "" : this.goodsType).equalsIgnoreCase(mfg.goodsType)){
                return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString()
    {
        return "MainFrameGoods [goodsName=" + goodsName + ", goodsType="
                + goodsType + ", goodsPrice=" + goodsPrice
                + ", goodsSalesAmount=" + goodsSalesAmount + ", goodsCredit="
                + goodsStock + ", goodStoreName=" + goodStoreName + "]";
    }
}
