package com.ebp.g4.dao.implement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
//import org.junit.Test;

import com.ebp.g4.dao.beans.CommentType;
import com.ebp.g4.dao.interfaces.CommentTypeIntf;

public class CommentTypeImpl implements CommentTypeIntf
{
    private static fileOperate op;
    
    /*返回所有的评论类型*/
    @Override
    public List<CommentType> selectAllCommentType( )
    {
        // TODO Auto-generated method stub
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
