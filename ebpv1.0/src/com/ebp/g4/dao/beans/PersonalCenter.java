package com.ebp.g4.dao.beans;

public class PersonalCenter
{
    private String id;//用户编号
    private String name;//用户名称
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
        return "PersonalCenter [id=" + id + ", name=" + name + "]";
    }
    
}
