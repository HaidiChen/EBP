package com.ebp.g4.dao.implement;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.junit.Assert;
import org.junit.Test;

import com.ebp.g4.dao.beans.Admin;

public class AdminImplTest
{
    fileOperate op =new fileOperate();
    AdminImpl adminImpl = new AdminImpl();
    Admin admin = new Admin();

    @Test
    public void testSelectAdmin()
    {
        Assert.assertEquals(true, adminImpl.selectAdmin("a1"));
        Assert.assertEquals(false, adminImpl.selectAdmin("a55"));
    }

    @Test
    public void testDeleteAdmin()
    {
        Assert.assertEquals(true, adminImpl.deleteAdmin("a2"));
    }

    @Test
    public void testAddAdmin()
    {
        admin.setId("a3");
        admin.setPassword("123");
        Assert.assertEquals(true, adminImpl.addAdmin(admin));
    }

    @Test
    public void testUpdateAdmin()
    {
        admin.setId("a3");
        admin.setPassword("abc");
        Assert.assertEquals(true, adminImpl.updateAdmin(admin));
        //fail("Not yet implemented");
    }

    @Test
    public void testSelectAdminById()
    {
        admin.setId("a1");
        admin.setPassword("admin123");
        System.out.println(admin);
        System.out.println(adminImpl.selectAdminById("a1"));
        Assert.assertEquals(admin.toString(), adminImpl.selectAdminById("a1").toString());
        //fail("Not yet implemented");
    }

    @Test
    public void testSelectAllAdmin()
    {
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
        Assert.assertEquals(empList.toString(), adminImpl.selectAllAdmin().toString());
        //fail("Not yet implemented");
    }

}
