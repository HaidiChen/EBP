package com.ebp.g4.dao.implement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

import com.ebp.g4.dao.beans.Comment;
import com.ebp.g4.dao.beans.Order;
import com.ebp.g4.dao.interfaces.OrderIntf;

public class OrderImpl implements OrderIntf
{

    private fileOperate op;
    
    /*通过orderid查询订单是否存在*/
    @Override
    public boolean selectOrder(String orderid)
    {
        // TODO Auto-generated method stub
        Document doc;
        int flag = 0;
        try
        {
            doc = op.openXml("data", "orderinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("order");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("orderid").equals(orderid))
                {
                    flag = flag +1;
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

        if(flag != 0) return true;
        else return false;
    }

   /*通过orderid删除订单信息*/
    @Override
    public boolean deleteOrder(String orderid)
    {
        // TODO Auto-generated method stub
        try
        {
            Document doc = op.openXml("data", "orderinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("order");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("orderid").equals(orderid))
                {
                    root.removeContent(element);
                }
            }
            op.saveXML(doc, "data", "orderinfo.xml");
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

        return true;
    }

    /*通过order对象增添订单信息*/
    @Override
    public boolean addOrder(Order order)
    {
        // TODO Auto-generated method stub
        try
        {
            Document doc = op.openXml("data", "orderinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("order");
            Element child = new Element("order");
            child.addContent(new Element("orderid").setText(order.getOrderid()));
            child.addContent(new Element("goodsid").setText(order.getGoodsid()));
            child.addContent(new Element("num").setText(order.getNum()));
            child.addContent(new Element("shopid").setText(order.getShopid()));
            child.addContent(new Element("tranprice").setText(order.getTransprice()));
            child.addContent(new Element("totalprice").setText(order.getTotalprice()));
            child.addContent(new Element("time").setText(order.getTime()));
            child.addContent(new Element("stateid").setText(order.getStateid()));
            child.addContent(new Element("userid").setText(order.getUserId()));
            root.addContent(child);
            op.saveXML(doc, "data", "orderinfo.xml");
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

        return true;
    }

    /*通过order对象修改信息*/
    @Override
    public boolean updateOrder(Order order)
    {
        // TODO Auto-generated method stub
        try
        {
          Document  doc = op.openXml("data","orderinfo.xml");        
          Element root=doc.getRootElement(); //获取根元素GOODINFO
          List<Element> list = root.getChildren("order");
          
          for (int i = 0; i < list.size(); i++)
          {
              Element element = (Element) list.get(i);
              if (element.getChildText("orderid").equals(order.getOrderid()))
              {
                 Element num = element.getChild("num");
                 num.setText(order.getNum());
                 Element tranprice = element.getChild("tranprice");
                 tranprice.setText(order.getTransprice());
                 Element totalprice= element.getChild("totalprice");
                 totalprice.setText(order.getTotalprice());
                 Element time = element.getChild("time");
                 time.setText(order.getTime());
                 Element stateid = element.getChild("stateid");
                 stateid.setText(order.getStateid());
              }
          }
          op.saveXML(doc, "data", "orderinfo.xml");
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

        return true;
    }

    /*通过orderid对象查询order对象*/
    @Override
    public Order selectOrderById(String orderid)
    {
        // TODO Auto-generated method stub
        Document doc;
       Order order = new Order();
        try
        {
            doc = op.openXml("data", "orderinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("order");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("orderid").equals(orderid))
                {
                    order.setOrderid(element.getChildText("orderid"));
                    order.setGoodsid(element.getChildText("goodsid"));
                    order.setShopid(element.getChildText("shopid"));
                    order.setNum(element.getChildText("num"));
                    order.setTransprice(element.getChildText("tranprice"));
                    order.setTotalprice(element.getChildText("totalprice"));
                    order.setTime(element.getChildText("time"));
                    order.setStateid(element.getChildText("stateid"));
                    order.setUserId(element.getChildText("userid"));
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

        return order;
    }

   /*查询所有的order对象*/
    @Override
    public List<Order> selectAllOrder()
    {
        // TODO Auto-generated method stub
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

        return empList;
    }

   /*通过是将来查询order对象list*/
    @Override
    public List<Order> selectAllOrderByDate(String date)
    {
        // TODO Auto-generated method stub
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
                if (element.getChildText("time").equals(date))
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

        return empList;
    }

    /*通过商品id来查询order对象list*/
    @Override
    public List<Order> selectAllOrderByGoodsId(String goodsId)
    {
        // TODO Auto-generated method stub
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
                if (element.getChildText("goodsid").equals(goodsId))
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

        return empList;
    }

   /*通过用户id来查询order对象list*/
    @Override
    public List<Order> selectAllOrderByUserId(String userId)
    {
        // TODO Auto-generated method stub
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
                if (element.getChildText("userid").equals(userId))
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

        return empList;
    }

}
