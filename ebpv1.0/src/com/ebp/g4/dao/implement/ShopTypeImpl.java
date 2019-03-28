package com.ebp.g4.dao.implement;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

import com.ebp.g4.dao.beans.CommentType;
import com.ebp.g4.dao.beans.ShopType;
import com.ebp.g4.dao.interfaces.ShopTypeIntf;


public class ShopTypeImpl implements ShopTypeIntf
{
private fileOperate op;
    
    @Override
    public List<ShopType> selectAllShopType()
    {
        // TODO Auto-generated method stub
        Document doc;
        List<ShopType> empList = new ArrayList<>();
        try{
            doc = op.openXml("data", "config.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element shoptype =root.getChild("shoptype");
            List<Element> list = shoptype.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
 
                    ShopType shopType = new ShopType();
                    shopType.setId(element.getChildText("id"));
                    shopType.setName(element.getChildText("name"));
                    empList.add(shopType);
 
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

    @Override
    public ShopType selectShopTypeById(String id)
    {
        // TODO Auto-generated method stub
        ShopType shopType = new ShopType();
        Document doc;
        try{
            doc = op.openXml("data", "config.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element shoptype =root.getChild("shoptype");
            List<Element> list = shoptype.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
 
                  
                    if(element.getChildText("id").equals(id)){
                    shopType.setId(element.getChildText("id"));
                    shopType.setName(element.getChildText("name"));}

 
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
        return shopType;

    }

}
