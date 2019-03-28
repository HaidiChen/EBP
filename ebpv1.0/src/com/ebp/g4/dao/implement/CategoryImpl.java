package com.ebp.g4.dao.implement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
//import org.junit.Test;

import com.ebp.g4.dao.beans.Category;
import com.ebp.g4.dao.interfaces.CategoryIntf;

public class CategoryImpl implements CategoryIntf
{
    private fileOperate op;
    
    /*返回所有的商品分类*/
    @Override
    public List<Category> selectAllCategory( )
    {
     // TODO Auto-generated method stub
        Document doc;
        List<Category> empList = new ArrayList<>();
        try
        {
        doc = op.openXml("data","categoryinfo.xml");
        Element root=doc.getRootElement(); //获取根元素GOODINFO
        List<Element> list = root.getChildren("item");

        for (int i = 0; i < list.size(); i++)
        {
            Element element = (Element) list.get(i);

                Category category = new Category();
                category.setId(element.getChildText("id"));
                category.setName(element.getChildText("name"));
                empList.add(category);
            
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
        //e.printStackTrace();
    }

    return empList;
    }

}
