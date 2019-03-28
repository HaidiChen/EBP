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

import com.ebp.g4.dao.beans.ShopType;

import junit.framework.Assert;

public class ShopTypeImplTest
{
    private fileOperate op;
    ShopTypeImpl shopTypeImpl = new ShopTypeImpl();

    @Test
    public void testSelectAllShopType()
    {
        //fail("Not yet implemented");
        Document doc;
        List<ShopType> empList = new ArrayList<>();
        try{
            doc = op.openXml("data", "config.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element shoptype =root.getChild("shoptype");
            List<Element> list = shoptype.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
 
                    ShopType shopType = new ShopType();
                    shopType.setId(element.getChildText("id"));
                    shopType.setName(element.getChildText("name"));
                    empList.add(shopType);
 
            }
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
        catch (JDOMException e)
        {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
           // e.printStackTrace();
        }
        
        Assert.assertEquals(empList.toString(), shopTypeImpl.selectAllShopType().toString());
        
    }

    @Test
    public void testSelectShopTypeById()
    {
       // fail("Not yet implemented");
        ShopType shopType = new ShopType();
        Document doc;
        try{
            doc = op.openXml("data", "config.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element shoptype =root.getChild("shoptype");
            List<Element> list = shoptype.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
 
                  
                    if(element.getChildText("id").equals("st1")){
                    shopType.setId(element.getChildText("id"));
                    shopType.setName(element.getChildText("name"));}

 
            }
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
        catch (JDOMException e)
        {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
           // e.printStackTrace();
        }
        
        
        Assert.assertEquals(shopType.toString(), shopTypeImpl.selectShopTypeById("st1").toString());
    }

}
