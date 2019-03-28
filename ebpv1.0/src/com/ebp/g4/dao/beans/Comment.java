package com.ebp.g4.dao.beans;

public class Comment
{
    private String id;//评论编号
    private String userid;//用户账号
    private String goodsid;//商品编号
    private String content;//评论内容
    private String time;//评论时间
    private String type;//评论类型
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getUserid()
    {
        return userid;
    }
    public void setUserid(String userid)
    {
        this.userid = userid;
    }
    public String getGoodsid()
    {
        return goodsid;
    }
    public void setGoodsid(String goodsid)
    {
        this.goodsid = goodsid;
    }
    public String getContent()
    {
        return content;
    }
    public void setContent(String content)
    {
        this.content = content;
    }
    public String getTime()
    {
        return time;
    }
    public void setTime(String time)
    {
        this.time = time;
    }
    public String getType()
    {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    @Override
    public String toString()
    {
        return "Comment [id=" + id + ", userid=" + userid + ", goodsid="
                + goodsid + ", content=" + content + ", time=" + time
                + ", type=" + type + "]";
    }
    
}
