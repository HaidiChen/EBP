package com.ebp.g4.dao.implement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
//import org.junit.Test;

import com.ebp.g4.dao.beans.GoodsType;
import com.ebp.g4.dao.interfaces.GoodsTypeIntf;

public class GoodsTypeImpl implements GoodsTypeIntf
{
    private static fileOperate op;

    /* 返回所有的商品类型 */
    @Override
    public List<GoodsType> selectAllGoodsType()
    {
        // TODO Auto-generated method stub
        Document doc;
        List<GoodsType> empList = new ArrayList<>();
        try
        {
            doc = op.openXml("data", "config.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element goodstype =root.getChild("goodstype");
            List<Element> list = goodstype.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                GoodsType goodsType = new GoodsType();
                goodsType.setId(element.getChildText("id"));
                goodsType.setName(element.getChildText("name"));
                goodsType.setCid(element.getChildText("cid"));
                empList.add(goodsType);
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

    /* 通过id返回商品类型 */
    @Override
    public GoodsType selectGoodsTypeById(String typeid)
    {
        // TODO Auto-generated method stub
        Document doc;
        GoodsType goodsType = new GoodsType();
        try
        {
            doc = op.openXml("data", "config.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element goodstype =root.getChild("goodstype");
            List<Element> list = goodstype.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("id").equals(typeid))
                {
                    goodsType.setId(element.getChildText("id"));
                    goodsType.setName(element.getChildText("name"));
                    goodsType.setCid(element.getChildText("cid"));
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

        return goodsType;
    }

    @Override
    public GoodsType selectGoodsTypeByName(String name)
    {
        // TODO Auto-generated method stub
        Document doc;
        GoodsType goodsType = new GoodsType();
        try
        {
            doc = op.openXml("data", "config.xml");
            Element root = doc.getRootElement(); // 获取根元素
            Element goodstype =root.getChild("goodstype");
            List<Element> list = goodstype.getChildren("item");
            for (int i = 0; i < list.size(); i++)
            {
                Element element = (Element) list.get(i);
                if (element.getChildText("name").equals(name))
                {
                    goodsType.setId(element.getChildText("id"));
                    goodsType.setName(element.getChildText("name"));
                    goodsType.setCid(element.getChildText("cid"));
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

        return goodsType;
    }

}
