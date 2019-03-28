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

import com.ebp.g4.dao.beans.Comment;

import junit.framework.Assert;

public class CommentImplTest
{
    private fileOperate op;
    CommentImpl comImpl = new CommentImpl();
    Comment comment = new Comment();

    @Test
    public void testSelectComment()
    {
       // fail("Not yet implemented");
        Assert.assertEquals(true, comImpl.selectComment("cmnt1"));
        Assert.assertEquals(false, comImpl.selectComment("c1"));
    }

    @Test
    public void testDeleteComment()
    {
        //fail("Not yet implemented");
        Assert.assertEquals(true, comImpl.deleteComment("cmnt4"));
    }

    @Test
    public void testAddComment()
    {
       // fail("Not yet implemented");
        //comment.setId("cmnt5");
        comment.setUserid("u1");
        comment.setType("1");
        comment.setTime("2018-9-5");
        comment.setGoodsid("g1");
        comment.setContent("莱斯");
        System.out.println(comment);
        Assert.assertEquals(true, comImpl.addComment(comment));
    }

    @Test
    public void testUpdateComment()
    {
       // fail("Not yet implemented");
        comment.setId("cmnt5");
        comment.setUserid("u1");
        comment.setType("1");
        comment.setTime("2018-4-4");
        comment.setGoodsid("g1");
        comment.setContent("莱斯");
        Assert.assertEquals(true, comImpl.updateComment(comment));
    }

    @Test
    public void testSelectCommentById()
    {
        //fail("Not yet implemented");
        comment.setId("cmnt1");
        comment.setUserid("u1");
        comment.setType("cmntt2");
        comment.setTime("2018-6-23");
        comment.setGoodsid("g1");
        comment.setContent("太臭了");
        Assert.assertEquals(comment.toString(), comImpl.selectCommentById("cmnt1").toString());
    }

    @Test
    public void testSelectAllComment()
    {
       // fail("Not yet implemented");
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

        Assert.assertEquals(empList.toString(), comImpl.selectAllComment().toString());
    }

    @Test
    public void testSelectCommentByGoodsId()
    {
       // fail("Not yet implemented");
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
                if (element.getChildText("goodsid").equals("g1"))
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
        System.out.println(empList);
        System.out.println(comImpl.selectCommentByGoodsId("g1"));
        Assert.assertEquals(empList.toString(), comImpl.selectCommentByGoodsId("g1").toString());
    }

}
