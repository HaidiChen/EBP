package com.ebp.g4.dao.implement;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.junit.Test;

import com.ebp.g4.dao.beans.Category;

import junit.framework.Assert;

public class CategoryImplTest
{
    private fileOperate op;
    CategoryImpl categoryImpl= new CategoryImpl();
    Category category = new Category();
    @Test
    public void testSelectAllCategory()
    {
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
        Assert.assertEquals(empList.toString(), categoryImpl.selectAllCategory().toString());     
        //fail("Not yet implemented");
    }

}
