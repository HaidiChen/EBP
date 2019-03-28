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
import com.ebp.g4.dao.beans.User;

public class UserImplTest
{
    fileOperate op =new fileOperate();
    UserImpl userImpl = new UserImpl();
    User user = new User();

    @Test
    public void testSelectUser()
    {
        Assert.assertEquals(true, userImpl.selectUser("u1"));
        Assert.assertEquals(false, userImpl.selectUser("u555"));
        //fail("Not yet implemented");
    }

    @Test
    public void testDeleteUser()
    {
        Assert.assertEquals(true, userImpl.deleteUser("u2"));
    }

    @Test
    public void testAddUser()
    {
        user.setId("u2");
        user.setPassword("123456");
        user.setNickname("小鱼人");
        user.setPhoto("123");
        user.setAddress("艾欧尼亚");
        user.setPhone("110");
        user.setEmail("182365784@163.com");
        Assert.assertEquals(true, userImpl.addUser(user));
    }

    @Test
    public void testUpdateUser()
    {
        user.setId("u1");
        user.setPassword("123321");
        user.setNickname("德莱厄斯");
        user.setPhoto("123");
        user.setAddress("诺克萨斯");
        user.setPhone("911");
        user.setEmail("87342568@163.com");
        Assert.assertEquals(true, userImpl.updateUser(user));
    }

    @Test
    public void testSelectUserById()
    {
        user.setId("u3");
        user.setPassword("112233");
        user.setNickname("布隆");
        user.setPhoto("u3.jpg");
        user.setAddress("弗雷尔卓德");
        user.setPhone("12987654321");
        user.setEmail("182365684@163.com");
        //System.out.println(user);
        //System.out.println(userImpl.selectUserById("u2"));
        Assert.assertEquals(user.toString(), userImpl.selectUserById("u3").toString());
    }

    @Test
    public void testSelectUserByNickname()
    {
        user.setId("u3");
        user.setPassword("112233");
        user.setNickname("布隆");
        user.setPhoto("u3.jpg");
        user.setAddress("弗雷尔卓德");
        user.setPhone("12987654321");
        user.setEmail("182365684@163.com");
        //System.out.println(user);
        //System.out.println(userImpl.selectUserByNickname("盖伦"));
        Assert.assertEquals(user.toString(), userImpl.selectUserByNickname("布隆").toString());
    }

    @Test
    public void testSelectAllUser()
    {
        Document doc;
        List<User> empList = new ArrayList<>();
        try
        {
            doc = op.openXml("data", "accountinfo.xml");
            Element root = doc.getRootElement();
            Element User = root.getChild("user");
            List<Element> list = User.getChildren("item");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);

                    User user = new User();
                    user.setId(element.getChildText("id"));
                    user.setPassword(element.getChildText("password"));
                    user.setNickname(element.getChildText("nickname"));
                    user.setPhoto(element.getChildText("photo"));
                    user.setAddress(element.getChildText("address"));
                    user.setPhone(element.getChildText("phone"));
                    user.setEmail(element.getChildText("email"));
                    empList.add(user);
               
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
        Assert.assertEquals(empList.toString(), userImpl.selectAllUser().toString());
    }

}
