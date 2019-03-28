package com.ebp.g4.dao.beans;

public class GoodsType
{
    private String id;//商品类别编号
    private String name;//类别名
    private String cid; // category编号
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
    public String getCid()
    {
        return cid;
    }
    public void setCid(String cid)
    {
        this.cid = cid;
    }
    @Override
    public String toString()
    {
        return "GoodsType [id=" + id + ", name=" + name + ", cid=" + cid + "]";
    }
    
    
}
