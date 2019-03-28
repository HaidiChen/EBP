package com.ebp.g4.dao.implement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

import com.ebp.g4.dao.beans.Category;
import com.ebp.g4.dao.beans.MenuList;
import com.ebp.g4.dao.interfaces.MenuListIntf;

public class MenuListImpl implements MenuListIntf
{
    private fileOperate op;
    @Override
    public List<MenuList> selectAllMenu()
    {
        // TODO Auto-generated method stub
        List<MenuList> empList = new ArrayList<>();
        try{
            Document doc = op.openXml("data", "menuinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element menulist =root.getChild("menulist");
            
            List<Element> list = menulist.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                    MenuList menuList = new MenuList();
                    menuList.setId(element.getChildText("id"));
                    menuList.setName(element.getChildText("name"));
                    empList.add(menuList);
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
            //e.printStackTrace();
        }

        return empList;
    }


}
