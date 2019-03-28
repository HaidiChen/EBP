package com.ebp.g4.dao.implement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
//import org.junit.Test;

import com.ebp.g4.dao.beans.Admin;
import com.ebp.g4.dao.beans.Comment;
import com.ebp.g4.dao.interfaces.AdminIntf;

public class AdminImpl implements AdminIntf
{
    private fileOperate op;

   /*通过id查询管理员是否存在*/
    //@Test
    @Override
    public boolean selectAdmin(String id)
    {
        // TODO Auto-generated method stub
        int flag = 0;
        try
        {
            Document doc = op.openXml("data", "accountinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element Admin = root.getChild("admin");
            List<Element> list = Admin.getChildren("item");
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

        if(flag != 0) return true;
        else return false;
    }

    /*通过id删除管理员账户*/
    @Override
    public boolean deleteAdmin(String id)
    {
        // TODO Auto-generated method stub
        try
        {
            Document doc = op.openXml("data", "accountinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element Admin = root.getChild("admin");
            List<Element> list = Admin.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("id").equals(id))
                {
                    Admin.removeContent(element);
                }
            }
            op.saveXML(doc, "data", "accountinfo.xml");
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
           // e.printStackTrace();
        }
        catch (JDOMException e)
        {
            // TODO Auto-generated catch block
           // e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
          //  e.printStackTrace();
        }

        return true;
    }

    /*通过admin对象增加管理员*/
    @Override
    public boolean addAdmin(Admin admin)
    {
        // TODO Auto-generated method stub
        try
        {
            Document doc = op.openXml("data", "accountinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element Admin = root.getChild("admin");
            List<Element> list = Admin.getChildren("item");
            Element child = new Element("item");
            child.addContent(new Element("id").setText(admin.getId()));
            child.addContent(new Element("password").setText(admin.getPassword()));
            Admin.addContent(child);
            op.saveXML(doc, "data", "accountinfo.xml");
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
           // e.printStackTrace();
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

        return true;
    }

    /*通过admin对象更新管理员数据*/
    @Override
    public boolean updateAdmin(Admin admin)
    {
        // TODO Auto-generated method stub
        try
        {
          Document  doc = op.openXml("data","accountinfo.xml");        
          Element root=doc.getRootElement(); //获取根元素
          Element Admin = root.getChild("admin");
          List<Element> list = Admin.getChildren("item");      
          for (int i = 0; i < list.size(); i++)
          {
              Element element = (Element) list.get(i);
              if (element.getChildText("id").equals(admin.getId()))
              {
                 Element password = element.getChild("password");
                 password.setText(admin.getPassword());
              }
          }
          op.saveXML(doc, "data", "accountinfo.xml");
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
          //  e.printStackTrace();
        }
        catch (JDOMException e)
        {
            // TODO Auto-generated catch block
           // e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
          //  e.printStackTrace();
        }

        return true;
    }

    /*通过id来查询admin对象*/
   @Override
   public Admin selectAdminById(String id)
    {
        // TODO Auto-generated method stub
       Document doc;
      Admin admin = new Admin();
       try
       {
           doc = op.openXml("data","accountinfo.xml");        
           Element root=doc.getRootElement(); //获取根元素
           Element Admin = root.getChild("admin");
           List<Element> list = Admin.getChildren("item");      

           for (int i = 0; i < list.size(); i++)
           {
               Element element = (Element) list.get(i);
               if (element.getChildText("id").equals(id))
               {
                   admin.setId(element.getChildText("id"));
                   admin.setPassword(element.getChildText("password"));
               }
           }
       }
       catch (FileNotFoundException e)
       {
           // TODO Auto-generated catch block
          // e.printStackTrace();
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

       return admin;
    }

   /*返回所有admin对象*/
    @Override
    public List<Admin> selectAllAdmin( )
    {
        // TODO Auto-generated method stub
        Document doc;
        List<Admin> empList = new ArrayList<>();
        try
        {
            doc = op.openXml("data", "accountinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element Admin = root.getChild("admin");
            List<Element> list = Admin.getChildren("item");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);

                    Admin admin = new Admin();
                    admin.setId(element.getChildText("id"));
                    admin.setPassword(element.getChildText("password"));
                    empList.add(admin);

            }
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
           // e.printStackTrace();
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

        return empList;
    }

}
