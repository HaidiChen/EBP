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

import com.ebp.g4.dao.beans.Sorting;

import junit.framework.Assert;


public class SortingImplTest
{
    private fileOperate op;
    SortingImpl sortingImpl = new SortingImpl();
    Sorting sorting = new Sorting();
    @Test
    public void testSelectAllSortings()
    {
        Document doc;
        List<Sorting> empList = new ArrayList<>();
        
        try
        {
        doc = op.openXml("data","sorting.xml");
        Element root=doc.getRootElement(); //获取根元素GOODINFO
        List<Element> list = root.getChildren("item");

        for (int i = 0; i < list.size(); i++)
        {
            Element element = (Element) list.get(i);

                Sorting sorting = new Sorting();
                sorting.setId(element.getChildText("id"));
                sorting.setName(element.getChildText("name"));
                empList.add(sorting);
            
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
        Assert.assertEquals(empList.toString(), sortingImpl.selectAllSortings().toString());
        //fail("Not yet implemented");
    }

}
