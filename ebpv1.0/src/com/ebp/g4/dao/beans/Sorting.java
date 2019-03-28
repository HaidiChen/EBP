package com.ebp.g4.dao.beans;

public class Sorting
{
    private String id;//分类编号
    private String name;//类别名
   
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
        return "String [id=" + id + ", name=" + name + "]";
    }
}
