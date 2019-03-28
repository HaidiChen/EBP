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

import com.ebp.g4.dao.beans.Seller;
import com.ebp.g4.dao.beans.User;

public class SellerImplTest
{
    fileOperate op =new fileOperate();
    SellerImpl sellerImpl = new SellerImpl();
    Seller seller = new Seller();

    @Test
    public void testSelectSeller()
    {
        Assert.assertEquals(true, sellerImpl.selectSeller("u2"));
        Assert.assertEquals(false, sellerImpl.selectSeller("u100"));
    }

    @Test
    public void testDeleteSeller()
    {
        Assert.assertEquals(true, sellerImpl.deleteSeller("u1"));
    }

    @Test
    public void testAddSeller()
    {
        seller.setId("u3");
        seller.setRealname("拉克丝");
        seller.setIdcard("123");
        seller.setBankaccount("321");
        seller.setShopid("s3");
        Assert.assertEquals(true, sellerImpl.addSeller(seller));
    }

    @Test
    public void testUpdateSeller()
    {
        seller.setId("u3");
        seller.setRealname("波比");
        seller.setIdcard("1234");
        seller.setBankaccount("4321");
        seller.setShopid("s4");
        Assert.assertEquals(true, sellerImpl.updateSeller(seller));
    }

    @Test
    public void testSelectSellerById()
    {
        seller.setId("u7");
        seller.setRealname("盲僧");
        seller.setIdcard("32358319980303652");
        seller.setBankaccount("6257001370024606535");
        seller.setShopid("s4");
        System.out.println(seller);
        System.out.println(sellerImpl.selectSellerById("u3"));
        Assert.assertEquals(seller.toString(), sellerImpl.selectSellerById("u7").toString());
    }

    @Test
    public void testSelectAllSeller()
    {
        Document doc;
        List<Seller> empList = new ArrayList<>();
        try
        {
            doc = op.openXml("data", "accountinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element Seller = root.getChild("seller");
            List<Element> list = Seller.getChildren("item");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);

                    Seller seller = new Seller();
                    seller.setId(element.getChildText("id"));
                    seller.setRealname(element.getChildText("realname"));
                    seller.setIdcard(element.getChildText("idcard"));
                    seller.setBankaccount(element.getChildText("bankaccount"));
                    seller.setShopid(element.getChildText("shopid"));
                    empList.add(seller);
                
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
        Assert.assertEquals(empList.toString(), sellerImpl.selectAllSeller().toString());
    }

}
