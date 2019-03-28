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

import com.ebp.g4.dao.beans.Shop;

import junit.framework.Assert;

public class ShopImplTest
{

    Shop shop = new Shop();
    ShopImpl shopImpl = new ShopImpl();
    private fileOperate op;
    
    @Test
    public void testSelectShop()
    {
        //fail("Not yet implemented");
        Assert.assertEquals(true, shopImpl.selectShop("s1"));
        Assert.assertEquals(false, shopImpl.selectShop("uuu1"));
    }

    @Test
    public void testDeleteShop()
    {
        //fail("Not yet implemented");
        Assert.assertEquals(true,shopImpl.deleteShop("s3"));
    }

    @Test
    public void testAddShop()
    {
        //fail("Not yet implemented");
        //shop.setShopid("s4");
        shop.setType("食品店");
        shop.setName("榴莲专卖店");
        shop.setLogcompany("韵达");
        shop.setInfo("榴莲糖很好吃");
        Assert.assertEquals(true, shopImpl.addShop(shop));
    }

    @Test
    public void testUpdateShop()
    {
        //fail("Not yet implemented");
        shop.setShopid("s4");
        shop.setType("食品店");
        shop.setName("榴莲专卖店");
        shop.setLogcompany("韵达");
        shop.setInfo("榴莲糖很好吃,榴莲蛋糕很好吃");
        Assert.assertEquals(true, shopImpl.updateShop(shop));
    }

    @Test
    public void testSelectShopById()
    {
        //fail("Not yet implemented");
        Document doc;
        Shop shop = new Shop();
        try
        {
            doc = op.openXml("data", "shopinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("shop");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("shopid").equals("s1"))
                {
                    shop.setShopid(element.getChildText("shopid"));
                    shop.setName(element.getChildText("name"));
                    shop.setType(element.getChildText("type"));
                    shop.setLogcompany(element.getChildText("logcompany"));
                    shop.setInfo(element.getChildText("info"));
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
        
        Assert.assertEquals(shop.toString(), shopImpl.selectShopById("s1").toString());
    }

    @Test
    public void testSelectAllShop()
    {
        //fail("Not yet implemented");
        Document doc;
        List<Shop> empList = new ArrayList<>();
        try
        {
            doc = op.openXml("data", "shopinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("shop");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                 Shop shop = new Shop();
                    shop.setShopid(element.getChildText("shopid"));
                    shop.setName(element.getChildText("name"));
                    shop.setType(element.getChildText("type"));
                    shop.setLogcompany(element.getChildText("logcompany"));
                    shop.setInfo(element.getChildText("info"));
                    empList.add(shop);                
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
        
        Assert.assertEquals(empList.toString(), shopImpl.selectAllShop().toString());
    }

    @Test
    public void testSelectShopByName()
    {
       // fail("Not yet implemented");
        Document doc;
        Shop shop = new Shop();
        try
        {
            doc = op.openXml("data", "shopinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("shop");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("name").equals("妞妞零食店"))
                {
                    shop.setShopid(element.getChildText("shopid"));
                    shop.setName(element.getChildText("name"));
                    shop.setType(element.getChildText("type"));
                    shop.setLogcompany(element.getChildText("logcompany"));
                    shop.setInfo(element.getChildText("info"));
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
        
        Assert.assertEquals(shop.toString(), shopImpl.selectShopByName("妞妞零食店").toString());
    }

}
