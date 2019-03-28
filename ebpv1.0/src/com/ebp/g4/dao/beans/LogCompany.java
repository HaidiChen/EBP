package com.ebp.g4.dao.beans;

public class LogCompany
{
    private String id;//物流公司编号
    private String name;//物流公司名称
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
        return "LogCompany [id=" + id + ", name=" + name + "]";
    }
    
    
}
