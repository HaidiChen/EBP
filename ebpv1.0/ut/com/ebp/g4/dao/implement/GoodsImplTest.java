package com.ebp.g4.dao.implement;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.junit.Test;

import com.ebp.g4.dao.beans.Goods;

import junit.framework.Assert;

public class GoodsImplTest
{

    private fileOperate op;
    GoodsImpl goodsImpl = new GoodsImpl();
    Goods goods = new Goods();
    
    @Test
    public void testSelectGoods()
    {
        Assert.assertEquals(true, goodsImpl.selectGoods("g1"));
       // fail("Not yet implemented");
    }

    @Test
    public void testDeleteGoods()
    {
        Assert.assertEquals(true, goodsImpl.deleteGoods("g5"));
        //fail("Not yet implemented");
    }

    @Test
    public void testAddGoods()
    {
        //goods.setGoodsid("g6");
        goods.setName("小猪佩奇");
        goods.setPrice("1000");
        goods.setPhoto("www");
        goods.setShopid("g1");
        goods.setStock("200");
        goods.setTypeid("gt1");
        goods.setInfo("社会人!!!!");
        goods.setTime("2005-9-9");
        goods.setTranprice("5");
        Assert.assertEquals(true, goodsImpl.addGoods(goods));
        //fail("Not yet implemented");
    }

    @Test
    public void testUpdateGoods()
    {
        goods.setGoodsid("g4");
        goods.setName("小公举");
        goods.setPrice("1000");
        goods.setPhoto("www");
        goods.setShopid("g1");
        goods.setStock("200");
        goods.setTypeid("gt1");
        goods.setInfo("社会人!!!!");
        goods.setTime("2005-9-9");
        goods.setTranprice("5");
        Assert.assertEquals(true, goodsImpl.updateGoods(goods));
       // fail("Not yet implemented");
    }

    @Test
    public void testSelectAllGoods()
    {
        Document doc;
        List<Goods> empList = new ArrayList<>();
        try
        {
            doc = op.openXml("data", "goodsinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element Goods = root.getChild("goods");
            List<Element> list = Goods.getChildren("item");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                    Goods goods = new Goods();
                    goods.setGoodsid(element.getChildText("goodsid"));
                    goods.setName(element.getChildText("name"));
                    goods.setPrice(element.getChildText("price"));
                    goods.setTypeid(element.getChildText("typeid"));
                    goods.setPhoto(element.getChildText("photo"));
                    goods.setStock(element.getChildText("stock"));
                    goods.setInfo(element.getChildText("info"));
                    goods.setShopid(element.getChildText("shopid"));
                    goods.setTime(element.getChildText("time"));
                    goods.setTranprice(element.getChildText("tranprice"));
                    empList.add(goods);
            }
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (JDOMException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Assert.assertEquals(empList.toString(), goodsImpl.selectAllGoods().toString());
       // fail("Not yet implemented");
    }

    @Test
    public void testSelectGoodsByName()
    {
        Document doc;
        Goods goods = new Goods();
         try
         {
             doc = op.openXml("data","goodsinfo.xml");        
             Element root=doc.getRootElement(); //获取根元素
             Element Goods = root.getChild("goods");
             List<Element> list = Goods.getChildren("item");      

             for (int i = 0; i < list.size(); i++)
             {
                 Element element = (Element) list.get(i);
                 if (element.getChildText("name").equals("榴莲糖"))
                 {
                     goods.setGoodsid(element.getChildText("goodsid"));
                     goods.setName(element.getChildText("name"));
                     goods.setPrice(element.getChildText("price"));
                     goods.setTypeid(element.getChildText("typeid"));
                     goods.setPhoto(element.getChildText("photo"));
                     goods.setStock(element.getChildText("stock"));
                     goods.setInfo(element.getChildText("info"));
                     goods.setShopid(element.getChildText("shopid"));
                     goods.setTime(element.getChildText("time"));
                     goods.setTranprice(element.getChildText("tranprice"));
                 }
             }
         }
         catch (FileNotFoundException e)
         {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         catch (JDOMException e)
         {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         catch (IOException e)
         {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         System.out.println(goods.toString());
         System.out.println(goodsImpl.selectGoodsByName("榴莲糖").toString());
         
         Assert.assertEquals(goods.toString(),goodsImpl.selectGoodsByName("榴莲糖").toString());
        //fail("Not yet implemented");
    }

    @Test
    public void testSelectGoodsById()
    {
        Document doc;
        Goods goods = new Goods();
         try
         {
             doc = op.openXml("data","goodsinfo.xml");        
             Element root=doc.getRootElement(); //获取根元素
             Element Goods = root.getChild("goods");
             List<Element> list = Goods.getChildren("item");      

             for (int i = 0; i < list.size(); i++)
             {
                 Element element = (Element) list.get(i);
                 if (element.getChildText("goodsid").equals("g1"))
                 {
                     goods.setGoodsid(element.getChildText("goodsid"));
                     goods.setName(element.getChildText("name"));
                     goods.setPrice(element.getChildText("price"));
                     goods.setTypeid(element.getChildText("typeid"));
                     goods.setPhoto(element.getChildText("photo"));
                     goods.setStock(element.getChildText("stock"));
                     goods.setInfo(element.getChildText("info"));
                     goods.setShopid(element.getChildText("shopid"));
                     goods.setTime(element.getChildText("time"));
                     goods.setTranprice(element.getChildText("tranprice"));
                 }
             }
         }
         catch (FileNotFoundException e)
         {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         catch (JDOMException e)
         {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         catch (IOException e)
         {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         Assert.assertEquals(goods.toString(), goodsImpl.selectGoodsById("g1").toString());
        //fail("Not yet implemented");
    }

}
