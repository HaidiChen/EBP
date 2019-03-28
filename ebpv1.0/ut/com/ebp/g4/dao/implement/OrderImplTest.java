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

import com.ebp.g4.dao.beans.Order;

import junit.framework.Assert;

public class OrderImplTest
{

    OrderImpl orderImpl = new OrderImpl();
    Order order = new Order();
    private fileOperate op;
    
    @Test
    public void testSelectOrder()
    {
        //fail("Not yet implemented");
        Assert.assertEquals(true, orderImpl.selectOrder("o1"));
        Assert.assertEquals(false, orderImpl.selectOrder("o999"));
    }

    @Test
    public void testDeleteOrder()
    {
        //fail("Not yet implemented");
        Assert.assertEquals(true, orderImpl.deleteOrder("o3"));
    }

    @Test
    public void testAddOrder()
    {
        //fail("Not yet implemented");
        order.setOrderid("o4");
        order.setGoodsid("g2");
        order.setShopid("s1");
        order.setNum("3");
        order.setStateid("os1");
        order.setTime("2018-9-6");
        order.setTotalprice("555");
        order.setTransprice("5");
        order.setUserId("u1");
        Assert.assertEquals(true, orderImpl.addOrder(order));
    }

    @Test
    public void testUpdateOrder()
    {
        //fail("Not yet implemented");
        order.setOrderid("o4");
        order.setGoodsid("g2");
        order.setShopid("s1");
        order.setNum("444");
        order.setStateid("os1");
        order.setTime("2018-9-6");
        order.setTotalprice("555");
        order.setTransprice("5");
        order.setUserId("u1");
        Assert.assertEquals(true, orderImpl.updateOrder(order));
    }

    @Test
    public void testSelectOrderById()
    {
       // fail("Not yet implemented");
        order.setOrderid("o1");
        order.setGoodsid("g1");
        order.setShopid("s1");
        order.setNum("1");
        order.setStateid("os2");
        order.setTime("2018-6-22");
        order.setTotalprice("21");
        order.setTransprice("10");
        order.setUserId("u1");
        System.out.println(order);
        System.out.println(orderImpl.selectOrderById("o1"));
        Assert.assertEquals(order.toString(), orderImpl.selectOrderById("o1").toString());
    }

    @Test
    public void testSelectAllOrder()
    {
       // fail("Not yet implemented");
        Document doc;
        List<Order> empList = new ArrayList<>();
        try
        {
            doc = op.openXml("data", "orderinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("order");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);

                    Order order = new Order();
                    order.setOrderid(element.getChildText("orderid"));
                    order.setGoodsid(element.getChildText("goodsid"));
                    order.setShopid(element.getChildText("shopid"));
                    order.setNum(element.getChildText("num"));
                    order.setTransprice(element.getChildText("tranprice"));
                    order.setTotalprice(element.getChildText("totalprice"));
                    order.setTime(element.getChildText("time"));
                    order.setStateid(element.getChildText("stateid"));
                    order.setUserId(element.getChildText("userid"));
                    empList.add(order);

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
        
        Assert.assertEquals(empList.toString(), orderImpl.selectAllOrder().toString());
    }

    @Test
    public void testSelectAllOrderByDate()
    {
        //fail("Not yet implemented");
        Document doc;
        List<Order> empList = new ArrayList<>();
        try
        {
            doc = op.openXml("data", "orderinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("order");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("time").equals("2018-5-28"))
                {
                    Order order = new Order();
                    order.setOrderid(element.getChildText("orderid"));
                    order.setGoodsid(element.getChildText("goodsid"));
                    order.setShopid(element.getChildText("shopid"));
                    order.setNum(element.getChildText("num"));
                    order.setTransprice(element.getChildText("tranprice"));
                    order.setTotalprice(element.getChildText("totalprice"));
                    order.setTime(element.getChildText("time"));
                    order.setStateid(element.getChildText("stateid"));
                    order.setUserId(element.getChildText("userid"));
                    empList.add(order);
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
        
        Assert.assertEquals(empList.toString(), orderImpl.selectAllOrderByDate("2018-5-28").toString());
    }

    @Test
    public void testSelectAllOrderByGoodsId()
    {
        //fail("Not yet implemented");
        Document doc;
        List<Order> empList = new ArrayList<>();
        try
        {
            doc = op.openXml("data", "orderinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("order");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("goodsid").equals("g1"))
                {
                    Order order = new Order();
                    order.setOrderid(element.getChildText("orderid"));
                    order.setGoodsid(element.getChildText("goodsid"));
                    order.setShopid(element.getChildText("shopid"));
                    order.setNum(element.getChildText("num"));
                    order.setTransprice(element.getChildText("tranprice"));
                    order.setTotalprice(element.getChildText("totalprice"));
                    order.setTime(element.getChildText("time"));
                    order.setStateid(element.getChildText("stateid"));
                    order.setUserId(element.getChildText("userid"));
                    empList.add(order);
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
        
        Assert.assertEquals(empList.toString(), orderImpl.selectAllOrderByGoodsId("g1").toString());
    }

    @Test
    public void testSelectAllOrderByUserId()
    {
        //fail("Not yet implemented");
        Document doc;
        List<Order> empList = new ArrayList<>();
        try
        {
            doc = op.openXml("data", "orderinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("order");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("userid").equals("u1"))
                {
                    Order order = new Order();
                    order.setOrderid(element.getChildText("orderid"));
                    order.setGoodsid(element.getChildText("goodsid"));
                    order.setShopid(element.getChildText("shopid"));
                    order.setNum(element.getChildText("num"));
                    order.setTransprice(element.getChildText("tranprice"));
                    order.setTotalprice(element.getChildText("totalprice"));
                    order.setTime(element.getChildText("time"));
                    order.setStateid(element.getChildText("stateid"));
                    order.setUserId(element.getChildText("userid"));
                    empList.add(order);
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

        Assert.assertEquals(empList.toString(), orderImpl.selectAllOrderByUserId("u1").toString());
    }

}
