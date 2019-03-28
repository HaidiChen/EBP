package com.ebp.g4.dao.implement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

import com.ebp.g4.dao.beans.Shop;
import com.ebp.g4.dao.interfaces.ShopIntf;

public class ShopImpl implements ShopIntf
{

    private fileOperate op;
    
    /*通过shopId查询店铺是否存在*/
    @Override
    public boolean selectShop(String shopid)
    {
        // TODO Auto-generated method stub
        Document doc;
        int flag = 0;
        try
        {
            doc = op.openXml("data", "shopinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("shop");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("shopid").equals(shopid))
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

    /*通过shopId删除店铺信息*/
    @Override
    public boolean deleteShop(String shopid)
    {
        // TODO Auto-generated method stub
        try
        {
            Document doc = op.openXml("data", "shopinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("shop");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("shopid").equals(shopid))
                {
                    root.removeContent(element);
                }
            }
            op.saveXML(doc, "data", "shopinfo.xml");
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

   /*通过shop对象增加店铺信息*/
    @Override
    public boolean addShop(Shop shop)
    {
        // TODO Auto-generated method stub
        try
        {
            Document doc = op.openXml("data", "shopinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("shop");
            String num1 = list.get(list.size()-1).getChildText("shopid");
            String regex1 = "[a-zA-Z]";
            String num = num1.replaceAll(regex1, "");
            int i = Integer.valueOf(num);
            i = i+ 1;
            String str1 = "s"+i;
            //System.out.println(str1);
            Element child = new Element("shop");
            child.addContent(new Element("shopid").setText(str1));
            child.addContent(new Element("name").setText(shop.getName()));
            child.addContent(new Element("type").setText(shop.getType()));
            child.addContent(new Element("logcompany").setText(shop.getLogcompany()));
            child.addContent(new Element("info").setText(shop.getInfo()));
            root.addContent(child);
            op.saveXML(doc, "data", "shopinfo.xml");
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

   /*通过shop对象修改店铺信息*/
    @Override
    public boolean updateShop(Shop shop)
    {
        // TODO Auto-generated method stub
        try
        {
          Document  doc = op.openXml("data","shopinfo.xml");        
          Element root=doc.getRootElement(); //获取根元素GOODINFO
          List<Element> list = root.getChildren("shop");
          
          for (int i = 0; i < list.size(); i++)
          {
              Element element = (Element) list.get(i);
              if (element.getChildText("shopid").equals(shop.getShopid()))
              {
                 Element logcompany = element.getChild("logcompany");
                 logcompany.setText(shop.getLogcompany());
                 Element name = element.getChild("name");
                 name.setText(shop.getName());
                 Element info = element.getChild("info");
                 info.setText(shop.getInfo());
              }
          }
          op.saveXML(doc, "data", "shopinfo.xml");
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

   /*通过shopId查询店铺，返回店铺信息*/
    @Override
    public Shop selectShopById(String shopid)
    {
        // TODO Auto-generated method stub
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
                if (element.getChildText("shopid").equals(shopid))
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

        return shop;
    }

    /*返回所有店铺信息*/
    @Override
    public List<Shop> selectAllShop( )
    {
        // TODO Auto-generated method stub
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

        return empList;
    }

    /*通过shopName来查询店铺*/
    @Override
    public Shop selectShopByName(String newStore)
    {
        // TODO Auto-generated method stub
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
                if (element.getChildText("name").equals(newStore))
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

        return shop;
    }

}
