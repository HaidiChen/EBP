package com.ebp.g4.dao.beans;

public class Seller
{
    private String id;//用户编号
    private String realname;//卖家真实姓名
    private String idcard;//卖家身份证
    private String bankaccount;//卖家银行账号
    private String shopid;//卖家店铺编号
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getRealname()
    {
        return realname;
    }
    public void setRealname(String realname)
    {
        this.realname = realname;
    }
    public String getIdcard()
    {
        return idcard;
    }
    public void setIdcard(String idcard)
    {
        this.idcard = idcard;
    }
    public String getBankaccount()
    {
        return bankaccount;
    }
    public void setBankaccount(String bankaccount)
    {
        this.bankaccount = bankaccount;
    }
    public String getShopid()
    {
        return shopid;
    }
    public void setShopid(String shopid)
    {
        this.shopid = shopid;
    }
    @Override
    public String toString()
    {
        return "Seller [id=" + id + ", realname=" + realname + ", idcard="
                + idcard + ", bankaccount=" + bankaccount + ", shopid=" + shopid
                + "]";
    }
    
}
