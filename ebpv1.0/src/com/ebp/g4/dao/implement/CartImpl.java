package com.ebp.g4.dao.implement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;


import com.ebp.g4.dao.beans.Cart;
import com.ebp.g4.dao.interfaces.CartIntf;

public class CartImpl implements CartIntf
{
    private fileOperate op;

    /* 通过用户id和商品id来删除购物车内的一条记录 */
    @Override
    public boolean deleteCart(String accountid, String goodsid)
    {
        // TODO Auto-generated method stub
        try
        {
            Document doc = op.openXml("data", "shopcartinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("accountid").equals(accountid)
                        && element.getChildText("goodsid").equals(goodsid))
                {
                    root.removeContent(element);
                }
            }
            op.saveXML(doc, "data", "shopcartinfo.xml");
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

    /*通过cart类来增加购物车信息*/
    @Override
    public boolean addCart(Cart cart)
    {
        // TODO Auto-generated method stub
        // Document doc;
        try
        {
            Document doc = op.openXml("data", "shopcartinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("item");
            Element child = new Element("item");
            child.addContent(
                    new Element("accountid").setText(cart.getAccountid()));
            child.addContent(new Element("goodsid").setText(cart.getGoodsid()));
            child.addContent(new Element("num").setText(cart.getNum()));
            root.addContent(child);
            op.saveXML(doc, "data", "shopcartinfo.xml");
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

    /*通过cart类来修改购物车信息*/
    @Override
    public boolean updateCart(Cart cart)
    {
        // TODO Auto-generated method stub
        try
        {
          Document  doc = op.openXml("data","shopcartinfo.xml");        
          Element root=doc.getRootElement(); //获取根元素GOODINFO
          List<Element> list = root.getChildren("item");
          
          for (int i = 0; i < list.size(); i++)
          {
              Element element = (Element) list.get(i);
              if (element.getChildText("accountid").equals(cart.getAccountid()) && element.getChildText("goodsid").equals(cart.getGoodsid()))
              {
                 Element num = element.getChild("num");
                 num.setText(cart.getNum());
              }
          }
          op.saveXML(doc, "data", "shopcartinfo.xml");
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

    /* 通过用户id返回购物车信息 */
    @Override
    public List<Cart> selectAllCart(String accountid)
    {
        // TODO Auto-generated method stub
        Document doc;
        List<Cart> empList = new ArrayList<>();
        try
        {
            doc = op.openXml("data", "shopcartinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("item");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("accountid").equals(accountid))
                {
                    Cart cart = new Cart();
                    cart.setAccountid(element.getChildText("accountid"));
                    cart.setGoodsid(element.getChildText("goodsid"));
                    cart.setNum(element.getChildText("num"));
                    empList.add(cart);
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

   /*通过账户id和商品id返回cart对象*/
    @Override
    public Cart selectCartById(String Accountid, String goodsid)
    {
        // TODO Auto-generated method stub
        Document doc;
       Cart cart = new Cart();
        try
        {
            doc = op.openXml("data", "shopcartinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("item");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("accountid").equals(Accountid) && element.getChildText("goodsid").equals(goodsid))
                {

                    cart.setAccountid(element.getChildText("accountid"));
                    cart.setGoodsid(element.getChildText("goodsid"));
                    cart.setNum(element.getChildText("num"));

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

        return cart;
    }
     
}
