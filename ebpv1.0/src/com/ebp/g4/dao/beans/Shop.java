package com.ebp.g4.dao.beans;

public class Shop
{
    private String shopid;//店铺编号
    private String name;//店铺名称
    private String type;//店铺类别
    private String logcompany;//物流公司
    private String info;//店铺简介
    public String getShopid()
    {
        return shopid;
    }
    public void setShopid(String shopid)
    {
        this.shopid = shopid;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getType()
    {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public String getLogcompany()
    {
        return logcompany;
    }
    public void setLogcompany(String logcompany)
    {
        this.logcompany = logcompany;
    }
    public String getInfo()
    {
        return info;
    }
    public void setInfo(String info)
    {
        this.info = info;
    }
    @Override
    public String toString()
    {
        return "Shop [shopid=" + shopid + ", name=" + name + ", type=" + type
                + ", logcompany=" + logcompany + ", info=" + info + "]";
    }
    
}
