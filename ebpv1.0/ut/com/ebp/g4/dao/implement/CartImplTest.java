package com.ebp.g4.dao.implement;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.junit.Assert;
import org.junit.Test;

import com.ebp.g4.dao.beans.Cart;

public class CartImplTest
{
    fileOperate op =new fileOperate();
    CartImpl cartImpl = new CartImpl();
    Cart cart = new Cart();
    
    @Test
    public void testDeleteCart()
    {
       Assert.assertEquals(true, cartImpl.deleteCart("u4", "g2"));
    }

    @Test
    public void testAddCart()
    {
       cart.setAccountid("u3");
       cart.setGoodsid("g4");
       cart.setNum("4");
       Assert.assertEquals(true, cartImpl.addCart(cart));
        //fail("Not yet implemented");
    }

    @Test
    public void testUpdateCart()
    {
        cart.setAccountid("u1");
        cart.setGoodsid("g1");
        cart.setNum("33");
        Assert.assertEquals(true, cartImpl.updateCart(cart));
        //fail("Not yet implemented");
    }

    @Test
    public void testSelectAllCart()
    {
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
                if (element.getChildText("accountid").equals("u1"))
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
        System.out.println(empList);
        System.out.println(cartImpl.selectAllCart("u1"));
        Assert.assertEquals(empList.toString(), cartImpl.selectAllCart("u1").toString());
        //fail("Not yet implemented");
    }

    @Test
    public void testSelectCartById()
    {
       cart.setAccountid("u10");
       cart.setGoodsid("g8");
       cart.setNum("1");
       System.out.println(cart);
       System.out.println(cartImpl.selectCartById("u10", "g8"));
       Assert.assertEquals(cart.toString(), cartImpl.selectCartById("u10", "g8").toString());
    }

}
