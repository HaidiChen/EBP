package com.ebp.g4.dao.implement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

import com.ebp.g4.dao.beans.Comment;
import com.ebp.g4.dao.interfaces.CommentIntf;

public class CommentImpl implements CommentIntf
{

    private fileOperate op;

    /* 通过评论id查询评论是否存在 */
    @Override
    public boolean selectComment(String id)
    {
        // TODO Auto-generated method stub
        Document doc;
        int flag = 0;
        try
        {
            doc = op.openXml("data", "commentinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("item");

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

    /* 通过评论id删除评论 */
    @Override
    public boolean deleteComment(String id)
    {
        try
        {
            Document doc = op.openXml("data", "commentinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("id").equals(id))
                {
                    root.removeContent(element);
                }
            }
            op.saveXML(doc, "data", "commentinfo.xml");
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

    /* 通过comment对象添加评论 */
    @Override
    public boolean addComment(Comment comment)
    {
        try
        {
            Document doc = op.openXml("data", "commentinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("item");
            Element child = new Element("item");
            String num1 = list.get(list.size()-1).getChildText("id");
            String regex1 = "[a-zA-Z]";
            String num = num1.replaceAll(regex1, "");
            //System.out.println(num);
            int i = Integer.valueOf(num);
            i = i+ 1;
            String str1 = "cmnt"+i;
            child.addContent(new Element("id").setText(str1));
            child.addContent(
                    new Element("userid").setText(comment.getUserid()));
            child.addContent(
                    new Element("goodsid").setText(comment.getGoodsid()));
            child.addContent(
                    new Element("content").setText(comment.getContent()));
            child.addContent(new Element("time").setText(comment.getTime()));
            child.addContent(new Element("type").setText(comment.getType()));
            root.addContent(child);
            op.saveXML(doc, "data", "commentinfo.xml");
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

    /* 通过comment对象修改评论 */
    @Override
    public boolean updateComment(Comment comment)
    {
        try
        {
            Document doc = op.openXml("data", "commentinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素GOODINFO
            List<Element> list = root.getChildren("item");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("id").equals(comment.getId()))
                {
                    Element content = element.getChild("content");
                    content.setText(comment.getContent());
                    Element type = element.getChild("type");
                    type.setText(comment.getContent());
                }
            }
            op.saveXML(doc, "data", "commentinfo.xml");
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

    /* 通过评论id查询评论，并返回comment对象 */
    @Override
    public Comment selectCommentById(String id)
    {
        // TODO Auto-generated method stub
        Document doc;
        Comment comment = new Comment();
        try
        {
            doc = op.openXml("data", "commentinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("item");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("id").equals(id))
                {
                    comment.setId(element.getChildText("id"));
                    comment.setUserid(element.getChildText("userid"));
                    comment.setGoodsid(element.getChildText("goodsid"));
                    comment.setContent(element.getChildText("content"));
                    comment.setTime(element.getChildText("time"));
                    comment.setType(element.getChildText("type"));
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

        return comment;
    }

    /* 查询所有comment对象 */
    @Override
    public List<Comment> selectAllComment()
    {
        // TODO Auto-generated method stub
        Document doc;
        List<Comment> empList = new ArrayList<>();
        try
        {
            doc = op.openXml("data", "commentinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("item");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);

                Comment comment = new Comment();
                comment.setId(element.getChildText("id"));
                comment.setUserid(element.getChildText("userid"));
                comment.setGoodsid(element.getChildText("goodsid"));
                comment.setContent(element.getChildText("content"));
                comment.setTime(element.getChildText("time"));
                comment.setType(element.getChildText("type"));
                empList.add(comment);

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

    @Override
    public List<Comment> selectCommentByGoodsId(String goodsid)
    {
        // TODO Auto-generated method stub
        Document doc;
        List<Comment> empList = new ArrayList<>();
        try
        {
            doc = op.openXml("data", "commentinfo.xml");
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren("item");

            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("goodsid").equals(goodsid))
                {
                    Comment comment = new Comment();
                    comment.setId(element.getChildText("id"));
                    comment.setUserid(element.getChildText("userid"));
                    comment.setGoodsid(element.getChildText("goodsid"));
                    comment.setContent(element.getChildText("content"));
                    comment.setTime(element.getChildText("time"));
                    comment.setType(element.getChildText("type"));
                    empList.add(comment);
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

        return empList;
    }

}
