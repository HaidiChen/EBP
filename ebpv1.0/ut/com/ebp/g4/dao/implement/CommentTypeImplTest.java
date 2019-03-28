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

import com.ebp.g4.dao.beans.CommentType;

import junit.framework.Assert;

public class CommentTypeImplTest
{
    private fileOperate op;
    CommentTypeImpl commentTypeImpl= new CommentTypeImpl();
    CommentType commentType = new CommentType();
    @Test
    public void testSelectAllCommentType()
    {
        Document doc;
        List<CommentType> empList = new ArrayList<>();
        try{
            doc = op.openXml("data", "config.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element commenttype =root.getChild("commenttype");
            List<Element> list = commenttype.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
 
                    CommentType commentType = new CommentType();
                    commentType.setId(element.getChildText("id"));
                    commentType.setName(element.getChildText("name"));
                    empList.add(commentType);
 
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
        Assert.assertEquals(empList.toString(), commentTypeImpl.selectAllCommentType().toString());     
        //fail("Not yet implemented");   
    }

}
