package com.ebp.g4.dao.beans;

public class Cart
{
    private String accountid;// 用户账号

    private String goodsid;// 商品编号

    private String num;// 商品数量

    public String getAccountid()
    {
        return accountid;
    }

    public void setAccountid(String accountid)
    {
        this.accountid = accountid;
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

    @Override
    public String toString()
    {
            return "Cart [accountid=" + accountid + ", goodsid=" + goodsid
                    + ", num=" + num + "]";
    }

}
