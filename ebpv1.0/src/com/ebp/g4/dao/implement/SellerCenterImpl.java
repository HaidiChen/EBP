package com.ebp.g4.dao.implement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
//import org.junit.Test;

import com.ebp.g4.dao.beans.SellerCenter;
import com.ebp.g4.dao.interfaces.SellerCenterIntf;

public class SellerCenterImpl implements SellerCenterIntf
{
    private static fileOperate op;
    @Override
    public List<SellerCenter> selectAllSellerCenter( )
    {
        // TODO Auto-generated method stub
        Document doc;
        List<SellerCenter> empList = new ArrayList<>();
        try{
            doc = op.openXml("data", "menuinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element sellercenter =root.getChild("sellercenter");
            List<Element> list = sellercenter.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);

                    SellerCenter sellercenter1 = new SellerCenter();
                    sellercenter1.setId(element.getChildText("id"));
                    sellercenter1.setName(element.getChildText("name"));
                    empList.add(sellercenter1);

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

}
