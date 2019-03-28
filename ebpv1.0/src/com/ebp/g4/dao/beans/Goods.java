package com.ebp.g4.dao.beans;

public class Goods
{
    private String goodsid;//商品编号
    private String name;//商品名称
    private String price;//商品价格
    private String typeid;//商品类别编号
    private String photo;//商品图片
    private String stock;//商品库存
    private String info;//商品简介
    private String shopid;//店铺编号
    private String time;//上架时间
    private String tranprice; //运费
    public String getGoodsid()
    {
        return goodsid;
    }
    public void setGoodsid(String goodsid)
    {
        this.goodsid = goodsid;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getPrice()
    {
        return price;
    }
    public void setPrice(String price)
    {
        this.price = price;
    }
    public String getTypeid()
    {
        return typeid;
    }
    public void setTypeid(String typeid)
    {
        this.typeid = typeid;
    }
    public String getPhoto()
    {
        return photo;
    }
    public void setPhoto(String photo)
    {
        this.photo = photo;
    }
    public String getStock()
    {
        return stock;
    }
    public void setStock(String stock)
    {
        this.stock = stock;
    }
    public String getInfo()
    {
        return info;
    }
    public void setInfo(String info)
    {
        this.info = info;
    }
    public String getShopid()
    {
        return shopid;
    }
    public void setShopid(String shopid)
    {
        this.shopid = shopid;
    }
    
    public String getTime()
    {
        return time;
    }
    public void setTime(String time)
    {
        this.time = time;
    }

    public String getTranprice()
    {
        return tranprice;
    }
    public void setTranprice(String tranprice)
    {
        this.tranprice = tranprice;
    }
    @Override
    public String toString()
    {
        return "Goods [goodsid=" + goodsid + ", name=" + name + ", price="
                + price + ", typeid=" + typeid + ", photo=" + photo + ", stock="
                + stock + ", info=" + info + ", shopid=" + shopid + ", time="
                + time + ", tranprice=" + tranprice + "]";
    }
    
}
