package com.ebp.g4.dao.beans;

public class MenuList
{
    private String id;   //菜单栏编号
    private String name;  //菜单栏名
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
        return "MenuList [id=" + id + ", name=" + name + "]";
    }
    
    

}
