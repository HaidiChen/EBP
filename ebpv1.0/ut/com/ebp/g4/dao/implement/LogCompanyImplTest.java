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

import com.ebp.g4.dao.beans.LogCompany;

import junit.framework.Assert;

public class LogCompanyImplTest
{
        private fileOperate op;        
        LogCompanyImpl logcompanyImpl = new LogCompanyImpl();
        LogCompany logcompany = new LogCompany();

        @Test
        public void testSelectAllLogCompany()
        {
            Document doc;
            List<LogCompany> empList = new ArrayList<>();
            try{
                doc = op.openXml("data", "logcompanyinfo.xml");
                Element root = doc.getRootElement(); // 获取根元素
                List<Element> list = root.getChildren("item");
                for (int i = 0; i < list.size(); i++)
                {
                    Element element = (Element) list.get(i);
     
                        LogCompany logcompany = new LogCompany();
                        logcompany.setId(element.getChildText("id"));
                        logcompany.setName(element.getChildText("name"));
                        empList.add(logcompany);
                   
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
            System.out.println(empList.toString());
            Assert.assertEquals(empList.toString(), logcompanyImpl.selectAllLogCompany().toString());
    }

    @Test
    public void testSelectLogCompany()
    {
        //fail("Not yet implemented");
        LogCompany log = new LogCompany();
        Document doc;
        try{
            doc = op.openXml("data", "logcompanyinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
 
                 if(element.getChildText("id").equals("log1")){
                    log.setId(element.getChildText("id"));
                    log.setName(element.getChildText("name"));}

               
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
           // e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
           // e.printStackTrace();
        }
        System.out.println(log.toString());
        System.out.println(logcompanyImpl.selectLogCompany("log1").toString());
        Assert.assertEquals(log.toString(), logcompanyImpl.selectLogCompany("log1").toString());
    }

}
