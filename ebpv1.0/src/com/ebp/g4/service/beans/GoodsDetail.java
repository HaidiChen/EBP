/**
 * 类名：GoodsDetail
 * 对应界面内容：商品详细信息界面所需的商品信息
 */

package com.ebp.g4.service.beans;

public class GoodsDetail
{
    private Object pitcure;     // 商品图片

    private float goodsPrice;   // 商品单价

    private int sellingNumbers; // 交易成功的商品件数

    private int numbers;        // 购买数量

    private int goodsNumbers;   // 库存

    private String goodsName;   // 商品名称

    private String goodsInfo;   // 宝贝详情

    @Override
    public boolean equals(Object obj)
    {
        GoodsDetail gd=(GoodsDetail)obj;
        if(     (this.goodsInfo==null?"":this.goodsInfo).equalsIgnoreCase(gd.goodsInfo)&&
                (this.goodsName==null?"":this.goodsName).equalsIgnoreCase(gd.goodsName)&&
                this.goodsNumbers==gd.goodsNumbers&&
                this.goodsPrice==gd.goodsPrice&&
                this.numbers==gd.numbers&&
                (this.pitcure==null?"":this.pitcure).equals(gd.pitcure)&&
                this.sellingNumbers==gd.sellingNumbers
                ){
            return true;
        }
        else{
            return false;
        }
        // TODO Auto-generated method stub
    }

    public Object getPitcure()
    {
        return pitcure;
    }

    public void setPitcure(Object pitcure)
    {
        this.pitcure = pitcure;
    }

    public float getGoodsPrice()
    {
        return goodsPrice;
    }

    public void setGoodsPrice(float goodsPrice)
    {
        this.goodsPrice = goodsPrice;
    }

    public int getSellingNumbers()
    {
        return sellingNumbers;
    }

    public void setSellingNumbers(int sellingNumbers)
    {
        this.sellingNumbers = sellingNumbers;
    }

    public int getNumbers()
    {
        return numbers;
    }

    public void setNumbers(int numbers)
    {
        this.numbers = numbers;
    }

    public int getGoodsNumbers()
    {
        return goodsNumbers;
    }

    public void setGoodsNumbers(int goodsNumbers)
    {
        this.goodsNumbers = goodsNumbers;
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
}
