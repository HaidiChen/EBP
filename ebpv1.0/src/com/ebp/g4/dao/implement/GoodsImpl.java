package com.ebp.g4.dao.implement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

import com.ebp.g4.dao.beans.Goods;
import com.ebp.g4.dao.interfaces.GoodsIntf;

public class GoodsImpl implements GoodsIntf
{
    private fileOperate op;

    /*通过id查询goods是否存在*/
    @Override
    public boolean selectGoods(String goodsid)
    {
        // TODO Auto-generated method stub
                Document doc;
        int flag = 0;
        try
        {
            doc = op.openXml("data", "goodsinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element goods = root.getChild("goods");
            List<Element> list = goods.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("goodsid").equals(goodsid))
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

    /*通过goodsid删除goods对象*/
    @Override
    public boolean deleteGoods(String goodsid)
    {
        // TODO Auto-generated method stub
        try
        {
            Document doc = op.openXml("data", "goodsinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element Goods = root.getChild("goods");
            List<Element> list = Goods.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("goodsid").equals(goodsid))
                {
                    Goods.removeContent(element);
                }
            }
            op.saveXML(doc, "data", "goodsinfo.xml");
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

    /*通过goods对象增添商品信息*/
    @Override
    public boolean addGoods(Goods goods)
    {
        // TODO Auto-generated method stub
        try
        {
            Document doc = op.openXml("data", "goodsinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element Goods = root.getChild("goods");
            List<Element> list = Goods.getChildren("item");
            String num1 = list.get(list.size()-1).getChildText("goodsid");
            String regex1 = "[a-zA-Z]";
            String num = num1.replaceAll(regex1, "");
            int i = Integer.valueOf(num);
            i = i+ 1;
            String str1 = "g"+i;
            Element child = new Element("item");
            child.addContent(new Element("goodsid").setText(str1));
            child.addContent(new Element("name").setText(goods.getName()));
            child.addContent(new Element("price").setText(goods.getPrice()));
            child.addContent(new Element("typeid").setText(goods.getTypeid()));
            child.addContent(new Element("photo").setText(goods.getPhoto()));
            child.addContent(new Element("stock").setText(goods.getStock()));
            child.addContent(new Element("info").setText(goods.getInfo()));
            child.addContent(new Element("shopid").setText(goods.getShopid()));
            child.addContent(new Element("time").setText(goods.getTime()));
            child.addContent(new Element("tranprice").setText(goods.getTranprice()));
            Goods.addContent(child);
            op.saveXML(doc, "data", "goodsinfo.xml");
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

    /*通过goods对象修改商品信息*/
    @Override
    public boolean updateGoods(Goods goods)
    {
        // TODO Auto-generated method stub
        try
        {
          Document  doc = op.openXml("data","goodsinfo.xml");        
          Element root=doc.getRootElement(); //获取根元素
          Element Goods = root.getChild("goods");
          List<Element> list = Goods.getChildren("item");      
          for (int i = 0; i < list.size(); i++)
          {
              Element element = (Element) list.get(i);
              if (element.getChildText("goodsid").equals(goods.getGoodsid()))
              {
                 Element name = element.getChild("name");
                 name.setText(goods.getName());
                 Element price = element.getChild("price");
                 price.setText(goods.getPrice());
                 Element typeid = element.getChild("typeid");
                 typeid.setText(goods.getTypeid());
                 Element photo = element.getChild("photo");
                 photo.setText(goods.getPhoto());
                 Element stock = element.getChild("stock");
                 stock.setText(goods.getStock());
                 Element info = element.getChild("info");
                 info.setText(goods.getInfo());
                 Element shopid = element.getChild("shopid");
                 shopid.setText(goods.getShopid());
                 Element time = element.getChild("time");
                 time.setText(goods.getTime());
                 
              }
          }
          op.saveXML(doc, "data", "goodsinfo.xml");
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

    /*查询所有goods对象*/
    @Override
    public List<Goods> selectAllGoods()
    {
        // TODO Auto-generated method stub
        Document doc;
        List<Goods> empList = new ArrayList<>();
        try
        {
            doc = op.openXml("data", "goodsinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element Goods = root.getChild("goods");
            List<Element> list = Goods.getChildren("item");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                    Goods goods = new Goods();
                    goods.setGoodsid(element.getChildText("goodsid"));
                    goods.setName(element.getChildText("name"));
                    goods.setPrice(element.getChildText("price"));
                    goods.setTypeid(element.getChildText("typeid"));
                    goods.setPhoto(element.getChildText("photo"));
                    goods.setStock(element.getChildText("stock"));
                    goods.setInfo(element.getChildText("info"));
                    goods.setShopid(element.getChildText("shopid"));
                    goods.setTime(element.getChildText("time"));
                    goods.setTranprice(element.getChildText("tranprice"));
                    empList.add(goods);
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

    /*通过goosName查询goods对象*/
    @Override
    public Goods selectGoodsByName(String newGoodsName)
    {
        // TODO Auto-generated method stub
        Document doc;
        Goods goods = new Goods();
         try
         {
             doc = op.openXml("data","goodsinfo.xml");        
             Element root=doc.getRootElement(); //获取根元素
             Element Goods = root.getChild("goods");
             List<Element> list = Goods.getChildren("item");      

             for (int i = 0; i < list.size(); i++)
             {
                 Element element = (Element) list.get(i);
                 if (element.getChildText("name").equals(newGoodsName))
                 {
                     goods.setGoodsid(element.getChildText("goodsid"));
                     goods.setName(element.getChildText("name"));
                     goods.setPrice(element.getChildText("price"));
                     goods.setTypeid(element.getChildText("typeid"));
                     goods.setPhoto(element.getChildText("photo"));
                     goods.setStock(element.getChildText("stock"));
                     goods.setInfo(element.getChildText("info"));
                     goods.setShopid(element.getChildText("shopid"));
                     goods.setTime(element.getChildText("time"));
                     goods.setTranprice(element.getChildText("tranprice"));
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

         return goods;
    }

   /*通过goodsID查询goods对象*/
    @Override
    public Goods selectGoodsById(String goodsid)
    {
        // TODO Auto-generated method stub
        Document doc;
        Goods goods = new Goods();
         try
         {
             doc = op.openXml("data","goodsinfo.xml");        
             Element root=doc.getRootElement(); //获取根元素
             Element Goods = root.getChild("goods");
             List<Element> list = Goods.getChildren("item");      

             for (int i = 0; i < list.size(); i++)
             {
                 Element element = (Element) list.get(i);
                 if (element.getChildText("goodsid").equals(goodsid))
                 {
                     goods.setGoodsid(element.getChildText("goodsid"));
                     goods.setName(element.getChildText("name"));
                     goods.setPrice(element.getChildText("price"));
                     goods.setTypeid(element.getChildText("typeid"));
                     goods.setPhoto(element.getChildText("photo"));
                     goods.setStock(element.getChildText("stock"));
                     goods.setInfo(element.getChildText("info"));
                     goods.setShopid(element.getChildText("shopid"));
                     goods.setTime(element.getChildText("time"));
                     goods.setTranprice(element.getChildText("tranprice"));
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

         return goods;
    }

}
