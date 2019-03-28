package com.ebp.g4.dao.beans;

public class ShopType
{
    private String id; // 店铺类型的id

    private String name; // 店铺类型的名字

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
        return "ShopType [id=" + id + ", name=" + name + "]";
    }

}
