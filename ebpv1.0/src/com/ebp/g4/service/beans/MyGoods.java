/**
 * 类名：MyGoods
 * 对应界面内容：我的宝贝界面需要的商品信息
 */

package com.ebp.g4.service.beans;

public class MyGoods
{
    private String goodsName;   // 商品名称

    private String goodsType;   // 商品类型

    private float goodsPrice;   // 商品价格

    private float goodsShipping;// 商品运费 

    private int goodsSales;     // 销量

    private long goodsStock;    // 库存

    private String goodsShelfTime;// 上架时间
    
    private Object picture;
    
    public Object getPicture()
    {
        return picture;
    }

    public void setPicture(Object picture)
    {
        this.picture = picture;
    }

    private String goodsInfo;

    public String getGoodsInfo()
    {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo)
    {
        this.goodsInfo = goodsInfo;
    }

    @Override
    public boolean equals(Object obj)
    {
        MyGoods mg=(MyGoods)obj;
        if(     (this.goodsName==null?"":this.goodsName).equalsIgnoreCase(mg.goodsName)&&
                this.goodsPrice==mg.goodsPrice&&
                this.goodsSales==mg.goodsSales&&
                (this.goodsShelfTime==null?"":this.goodsShelfTime).equalsIgnoreCase(mg.goodsShelfTime)&&
                this.goodsShipping==mg.goodsShipping&&
                this.goodsStock==mg.goodsStock&&
                (this.goodsType==null?"":this.goodsType).equalsIgnoreCase(mg.goodsType)&&
                (this.goodsInfo==null?"":this.goodsInfo).equalsIgnoreCase(mg.goodsInfo)&&
                (this.picture==null?"":this.picture).equals(mg.picture)
                ){
            return true;
        }
        else{
            return false;
        }
        // TODO Auto-generated method stub
    }

    @Override
    public String toString()
    {
        return "MyGoods [goodsName=" + goodsName + ", goodsType=" + goodsType
                + ", goodsPrice=" + goodsPrice + ", goodsShipping="
                + goodsShipping + ", goodsSales=" + goodsSales + ", goodsStock="
                + goodsStock + ", goodsShelfTime=" + goodsShelfTime
                + ", picture=" + picture + ", goodsInfo=" + goodsInfo + "]";
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

    public float getGoodsCarriage()
    {
        return goodsShipping;
    }

    public void setGoodsCarriage(float goodsCarriage)
    {
        this.goodsShipping = goodsCarriage;
    }

    public int getGoodsSales()
    {
        return goodsSales;
    }

    public void setGoodsSales(int goodsSales)
    {
        this.goodsSales = goodsSales;
    }

    public long getGoodsStock()
    {
        return goodsStock;
    }

    public void setGoodsStock(long goodsStock)
    {
        this.goodsStock = goodsStock;
    }

    public String getGoodsShelfTime()
    {
        return goodsShelfTime;
    }

    public void setGoodsShelfTime(String goodsShelfTime)
    {
        this.goodsShelfTime = goodsShelfTime;
    }

}
