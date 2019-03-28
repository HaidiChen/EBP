package com.ebp.g4.dao.implement;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
//import org.junit.Test;

import com.ebp.g4.dao.beans.Seller;
import com.ebp.g4.dao.beans.User;
import com.ebp.g4.dao.interfaces.SellerIntf;

public class SellerImpl implements SellerIntf
{
    private fileOperate op;

    /*通过id查询卖家是否存在*/
   @Override
    public boolean selectSeller(String id)
    {
        // TODO Auto-generated method stub
       Document doc;
       int flag = 0;
       try
       {
           doc = op.openXml("data", "accountinfo.xml");
           Element root = doc.getRootElement(); // 获取根元素
           Element Seller = root.getChild("seller");
           List<Element> list = Seller.getChildren("item");
           for (int i = 0; i < list.size(); i++)
           {
               Element element = (Element) list.get(i);
               if (element.getChildText("id").equals(id))
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

   /*通过id删除卖家信息*/
    @Override
    public boolean deleteSeller(String id)
    {
        try
        {
            Document doc = op.openXml("data", "accountinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element Seller = root.getChild("seller");
            List<Element> list = Seller.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("id").equals(id))
                {
                    Seller.removeContent(element);
                }
            }
            op.saveXML(doc, "data", "accountinfo.xml");
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

  /*通过seller对象增加卖家信息*/
    @Override
    public boolean addSeller(Seller seller)
    {
        // TODO Auto-generated method stub
        try
        {
            Document doc = op.openXml("data", "accountinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element Seller = root.getChild("seller");
            List<Element> list = Seller.getChildren("item");
            Element child = new Element("item");
            child.addContent(new Element("id").setText(seller.getId()));
            child.addContent(new Element("realname").setText(seller.getRealname()));
            child.addContent(new Element("idcard").setText(seller.getIdcard()));
            child.addContent(new Element("bankaccount").setText(seller.getBankaccount()));
            child.addContent(new Element("shopid").setText(seller.getShopid()));
            Seller.addContent(child);
            op.saveXML(doc, "data", "accountinfo.xml");
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

   /*通过seller对象更新卖家信息*/
    @Override
    public boolean updateSeller(Seller seller)
    {
        // TODO Auto-generated method stub
        try
        {
          Document  doc = op.openXml("data","accountinfo.xml");        
          Element root=doc.getRootElement(); //获取根元素
          Element Seller = root.getChild("seller");
          List<Element> list = Seller.getChildren("item");      
          for (int i = 0; i < list.size(); i++)
          {
              Element element = (Element) list.get(i);
              if (element.getChildText("id").equals(seller.getId()))
              {
                 Element password = element.getChild("realname");
                 password.setText(seller.getRealname());
                 Element idcard = element.getChild("idcard");
                 idcard.setText(seller.getIdcard());
                 Element bankaccount = element.getChild("bankaccount");
                 bankaccount.setText(seller.getBankaccount());
                 Element shopid = element.getChild("shopid");
                 shopid.setText(seller.getShopid());

              }
          }
          op.saveXML(doc, "data", "accountinfo.xml");
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

    /*通过sellerId来查询卖家信息*/
    @Override
    public Seller selectSellerById(String id)
    {
        // TODO Auto-generated method stub
        Document doc;
        Seller seller = new Seller();
         try
         {
             doc = op.openXml("data","accountinfo.xml");        
             Element root=doc.getRootElement(); //获取根元素
             Element Seller = root.getChild("seller");
             List<Element> list = Seller.getChildren("item");      

             for (int i = 0; i < list.size(); i++)
             {
                 Element element = (Element) list.get(i);
                 if (element.getChildText("id").equals(id))
                 {
                     seller.setId(element.getChildText("id"));
                     seller.setRealname(element.getChildText("realname"));
                     seller.setIdcard(element.getChildText("idcard"));
                     seller.setBankaccount(element.getChildText("bankaccount"));
                     seller.setShopid(element.getChildText("shopid"));
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

         return seller;
    }

    /*查询所有的卖家信息*/
    @Override
    public List<Seller> selectAllSeller( )
    {
        // TODO Auto-generated method stub
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
        
        return empList;
    }

}
