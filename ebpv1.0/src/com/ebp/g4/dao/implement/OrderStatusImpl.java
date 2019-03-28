package com.ebp.g4.dao.implement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
//import org.junit.Test;

import com.ebp.g4.dao.beans.OrderStatus;
import com.ebp.g4.dao.interfaces.OrderStatusIntf;

public class OrderStatusImpl implements OrderStatusIntf
{
    private static fileOperate op;
    
    /*查询所有的订单状态*/
    @Override
    public List<OrderStatus> selectAllOrderStatus()
    {
        // TODO Auto-generated method stub
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

        return empList;
    }
    
    /*通过id查orderstatus*/
    @Override
    public OrderStatus selectStatusById(String stateid)
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
            if (element.getChildText("id").equals(stateid))
            {

                orderstatus.setId(element.getChildText("id"));
                orderstatus.setName(element.getChildText("name"));
            }
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
       // e.printStackTrace();
    }
    catch (IOException e)
    {
        // TODO Auto-generated catch block
       // e.printStackTrace();
    }

    return orderstatus;
    }
  
    /*通过状态查询orderStatus对象*/
    @Override
    public OrderStatus selectStatusByName(String name)
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
                if (element.getChildText("name").equals(name))
                {
                    orderstatus.setId(element.getChildText("id"));
                    orderstatus.setName(element.getChildText("name"));
                }
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
          //  e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
           // e.printStackTrace();
        }

        return orderstatus;
    }

}
