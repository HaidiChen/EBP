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

import com.ebp.g4.dao.beans.GoodsType;

import junit.framework.Assert;

public class GoodsTypeImplTest
{
    private fileOperate op;
    GoodsTypeImpl goodsTypeImpl= new GoodsTypeImpl();
    GoodsType goodsType = new GoodsType();
    @Test
    public void testSelectAllGoodsType()
    {
        Document doc;
        List<GoodsType> empList = new ArrayList<>();
        try
        {
            doc = op.openXml("data", "config.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element goodstype =root.getChild("goodstype");
            List<Element> list = goodstype.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                GoodsType goodsType = new GoodsType();
                goodsType.setId(element.getChildText("id"));
                goodsType.setName(element.getChildText("name"));
                goodsType.setCid(element.getChildText("cid"));
                empList.add(goodsType);
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
        Assert.assertEquals(empList.toString(), goodsTypeImpl.selectAllGoodsType().toString());
        //fail("Not yet implemented");
    }

    @Test
    public void testSelectGoodsTypeById()
    {
        Document doc;
        GoodsType goodsType = new GoodsType();
        try
        {
            doc = op.openXml("data", "config.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element goodstype =root.getChild("goodstype");
            List<Element> list = goodstype.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("id").equals("gt1"))
                {
                    goodsType.setId(element.getChildText("id"));
                    goodsType.setName(element.getChildText("name"));
                    goodsType.setCid(element.getChildText("cid"));
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
        Assert.assertEquals(goodsType.toString(),goodsTypeImpl.selectGoodsTypeById("gt1").toString());
        //fail("Not yet implemented");
    }

    @Test
    public void testSelectGoodsTypeByName()
    {
        Document doc;
        GoodsType goodsType = new GoodsType();
        try
        {
            doc = op.openXml("data", "config.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element goodstype =root.getChild("goodstype");
            List<Element> list = goodstype.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("name").equals("食品"))
                {
                    goodsType.setId(element.getChildText("id"));
                    goodsType.setName(element.getChildText("name"));
                    goodsType.setCid(element.getChildText("cid"));
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
        Assert.assertEquals(goodsType.toString(),goodsTypeImpl.selectGoodsTypeByName("食品").toString());
        //fail("Not yet implemented");
    }

}
