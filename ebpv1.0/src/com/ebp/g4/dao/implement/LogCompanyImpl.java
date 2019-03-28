package com.ebp.g4.dao.implement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import java.util.List;

import com.ebp.g4.dao.beans.LogCompany;
import com.ebp.g4.dao.beans.OrderStatus;
import com.ebp.g4.dao.interfaces.LogCompanyIntf;

public class LogCompanyImpl implements LogCompanyIntf
{
    private static fileOperate op;
   
    /*查询所有的快递公司*/
    @Override
    public List<LogCompany> selectAllLogCompany( )
    {
        // TODO Auto-generated method stub
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

        return empList;
    }

  /*通过物流公司id，返回物流公司信息*/
    @Override
    public LogCompany selectLogCompany(String id)
    {
        // TODO Auto-generated method stub
        LogCompany log = new LogCompany();
        Document doc;
        try{
            doc = op.openXml("data", "logcompanyinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
 
                 if(element.getChildText("id").equals(id)){
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
        return log;
    }

}
