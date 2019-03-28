package com.ebp.g4.dao.beans;

public class OrderStatus
{
    private String id;//订单编号
    private String name;//订单名称
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
        return "OrderStatement [id=" + id + ", name=" + name + "]";
    }
    
}
