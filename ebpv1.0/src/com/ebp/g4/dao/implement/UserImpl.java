package com.ebp.g4.dao.implement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
//import org.junit.Test;

import com.ebp.g4.dao.beans.User;
import com.ebp.g4.dao.interfaces.UserIntf;

public class UserImpl implements UserIntf
{
    private fileOperate op;

    @Override
    public boolean selectUser(String id)
    {
        // TODO Auto-generated method stub
        Document doc;
        int flag = 0;
        try
        {
            doc = op.openXml("data", "accountinfo.xml");
            Element root = doc.getRootElement(); // 鑾峰彇鏍瑰厓绱�
            Element User = root.getChild("user");
            List<Element> list = User.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("id").equals(id))
                {
                    flag = flag + 1;
                }
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

        if (flag != 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean deleteUser(String id)
    {
        // TODO Auto-generated method stub
        try
        {
            Document doc = op.openXml("data", "accountinfo.xml");
            Element root = doc.getRootElement(); // 鑾峰彇鏍瑰厓绱�
            Element User = root.getChild("user");
            List<Element> list = User.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("id").equals(id))
                {
                    User.removeContent(element);
                }
            }
            op.saveXML(doc, "data", "accountinfo.xml");
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

        return true;
    }

    @Override
    public boolean addUser(User user)
    {
        // TODO Auto-generated method stub
        try
        {
            Document doc = op.openXml("data", "accountinfo.xml");
            Element root = doc.getRootElement();
            Element User = root.getChild("user");
            // List<Element> list = root.getChildren("item");
            /*String photo = System.getProperty("user.dir") + File.separator
                    + "image" + File.separator+"userphoto"+File.separator + user.getPhoto();*/
            Element child = new Element("item");
            child.addContent(new Element("id").setText(user.getId()));
            child.addContent(
                    new Element("password").setText(user.getPassword()));
            child.addContent(
                    new Element("nickname").setText(user.getNickname()));
            child.addContent(new Element("address").setText(user.getAddress()));
            child.addContent(new Element("phone").setText(user.getPhone()));
            child.addContent(new Element("email").setText(user.getEmail()));
            child.addContent(new Element("photo").setText(user.getPhoto()));
            User.addContent(child);
            op.saveXML(doc, "data", "accountinfo.xml");
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

        return true;
    }

    @Override
    public boolean updateUser(User user)
    {
        // TODO Auto-generated method stub
        try
        {
            Document doc = op.openXml("data", "accountinfo.xml");
            Element root = doc.getRootElement(); // 鑾峰彇鏍瑰厓绱�
            Element User = root.getChild("user");
            List<Element> list = User.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("id").equals(user.getId()))
                {
                    Element password = element.getChild("password");
                    password.setText(user.getPassword());
                    Element nickname = element.getChild("nickname");
                    nickname.setText(user.getNickname());
                    Element address = element.getChild("address");
                    address.setText(user.getAddress());
                    Element photo = element.getChild("photo");
                    photo.setText(user.getPhoto());
                    Element phone = element.getChild("phone");
                    phone.setText(user.getPhone());
                    Element email = element.getChild("email");
                    email.setText(user.getEmail());
                }
            }
            op.saveXML(doc, "data", "accountinfo.xml");
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

        return true;
    }

    @Override
    public User selectUserById(String id)
    {
        // TODO Auto-generated method stub
        Document doc;
        User user = new User();
        try
        {
            doc = op.openXml("data", "accountinfo.xml");
            Element root = doc.getRootElement(); // 鑾峰彇鏍瑰厓绱�
            Element User = root.getChild("user");
            List<Element> list = User.getChildren("item");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("id").equals(id))
                {
                    user.setId(element.getChildText("id"));
                    user.setPassword(element.getChildText("password"));
                    user.setNickname(element.getChildText("nickname"));
                    user.setPhoto(element.getChildText("photo"));
                    user.setAddress(element.getChildText("address"));
                    user.setPhone(element.getChildText("phone"));
                    user.setEmail(element.getChildText("email"));
                }
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

        return user;
    }

    public User selectUserByNickname(String nickname)
    {
        // TODO Auto-generated method stub
        Document doc;
        User user = new User();
        try
        {
            doc = op.openXml("data", "accountinfo.xml");
            Element root = doc.getRootElement(); // 鑾峰彇鏍瑰厓绱�
            Element User = root.getChild("user");
            List<Element> list = User.getChildren("item");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("nickname").equals(nickname))
                {
                    user.setId(element.getChildText("id"));
                    user.setPassword(element.getChildText("password"));
                    user.setNickname(element.getChildText("nickname"));
                    user.setPhoto(element.getChildText("photo"));
                    user.setAddress(element.getChildText("address"));
                    user.setPhone(element.getChildText("phone"));
                    user.setEmail(element.getChildText("email"));
                }
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

        return user;
    }

    public List<User> selectAllUser()
    {
        Document doc;
        List<User> empList = new ArrayList<>();
        try
        {
            doc = op.openXml("data", "accountinfo.xml");
            Element root = doc.getRootElement(); // 鑾峰彇鏍瑰厓绱�
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

        return empList;
    }

}
