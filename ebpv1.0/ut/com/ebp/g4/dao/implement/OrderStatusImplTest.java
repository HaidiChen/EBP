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

import com.ebp.g4.dao.beans.OrderStatus;

import junit.framework.Assert;



public class OrderStatusImplTest
{
    private fileOperate op;
    OrderStatusImpl orderstatusImpl = new OrderStatusImpl();
    OrderStatus orderstatsus = new OrderStatus();
    @Test
    public void testSelectAllOrderStatus()
    {
        Document doc;
        List<OrderStatus> empList = new ArrayList<>();
        try{
            doc = op.openXml("data", "config.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element orderstatement=root.getChild("orderstatement");
            List<Element> list = orderstatement.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                    OrderStatus orderstatus = new OrderStatus();
                    orderstatus.setId(element.getChildText("id"));
                    orderstatus.setName(element.getChildText("name"));
                    empList.add(orderstatus);
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
        Assert.assertEquals(empList.toString(), orderstatusImpl.selectAllOrderStatus().toString());
        //fail("Not yet implemented");
    }

    @Test
    public void testSelectStatusById()
    {
        Document doc;
        OrderStatus orderstatus = new OrderStatus();
    try{
        doc = op.openXml("data", "config.xml");
        Element root = doc.getRootElement(); // 获取根元素
        Element orderstatement=root.getChild("orderstatement");
        List<Element> list = orderstatement.getChildren("item");
        for (int i = 0; i < list.size(); i++)
        {
            Element element = (Element) list.get(i);
            if (element.getChildText("id").equals("os1"))
            {

                orderstatus.setId(element.getChildText("id"));
                orderstatus.setName(element.getChildText("name"));
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
    Assert.assertEquals(orderstatus.toString(), orderstatusImpl.selectStatusById("os1").toString());
        //fail("Not yet implemented");
    }

    @Test
    public void testSelectStatusByName()
    {
        Document doc;
        OrderStatus orderstatus = new OrderStatus();
        try{
            doc = op.openXml("data", "config.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element orderstatement=root.getChild("orderstatement");
            List<Element> list = orderstatement.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("name").equals("已发货"))
                {
                    orderstatus.setId(element.getChildText("id"));
                    orderstatus.setName(element.getChildText("name"));
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
        Assert.assertEquals(orderstatus.toString(), orderstatusImpl.selectStatusByName("已发货").toString());
        //fail("Not yet implemented");
    }

}
