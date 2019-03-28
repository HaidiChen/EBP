package com.ebp.g4.dao.beans;

public class SellerCenter
{
    private String id;//卖家编号
    private String name;//卖家名字
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    @Override
    public String toString()
    {
        return "SelllerCenter [id=" + id + ", name=" + name + "]";
    }
    
}
