package com.ebp.g4.dao.implement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
//import org.junit.Test;

import com.ebp.g4.dao.beans.Cart;
import com.ebp.g4.dao.beans.PersonalCenter;
import com.ebp.g4.dao.interfaces.PersonalCenterIntf;

public class PersonalCenterImpl implements PersonalCenterIntf
{
    private static fileOperate op;
    @Override
    public List<PersonalCenter> selectAllPersonalCenter( )
    {
        // TODO Auto-generated method stub
        Document doc;
        List<PersonalCenter> empList = new ArrayList<>();
        try{
            doc = op.openXml("data", "menuinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element personalcenter =root.getChild("personalcenter");
            List<Element> list = personalcenter.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);

                    PersonalCenter personalcenter1 = new PersonalCenter();
                    personalcenter1.setId(element.getChildText("id"));
                    personalcenter1.setName(element.getChildText("name"));
                    empList.add(personalcenter1);

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
