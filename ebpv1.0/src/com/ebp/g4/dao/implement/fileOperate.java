package com.ebp.g4.dao.implement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class fileOperate
{
    public static Document openXml(String floderName, String fileName)
            throws FileNotFoundException, JDOMException, IOException
    {
        SAXBuilder sb = new SAXBuilder();
        // 构造文档对象
        /*
         * Document doc = sb.build(new FileReader(System.getProperty("user.dir")
         * + File.separator + floderName + File.separator + fileName));
         */

        InputStream in = new FileInputStream(System.getProperty("user.dir")
                + File.separator + floderName + File.separator + fileName);
        Reader characterStream = new InputStreamReader(in, "utf-8");

        Document doc = sb.build(characterStream);
        return doc;
    }

    public static void saveXML(Document doc, String filePath, String fileName)
    {
        // 将doc对象输出到文件
        try
        {
            // 创建xml文件输出流
            XMLOutputter xmlopt = new XMLOutputter();

            // 创建文件输出流
            OutputStream out = new FileOutputStream(
                    System.getProperty("user.dir") + File.separator + filePath
                            + File.separator + fileName);
            Writer writer = new OutputStreamWriter(out, "utf-8");

            // 指定文档格式
            Format fm = Format.getPrettyFormat();
            fm.setEncoding("utf-8");
            xmlopt.setFormat(fm);

            // 将doc写入到指定的文件中
            xmlopt.output(doc, writer);
            /*
             * // System.out.println(doc.getRootElement().toString()); Element
             * root = doc.getRootElement(); Element Goods =
             * root.getChild("goods"); List<Element> list =
             * Goods.getChildren("item"); System.out.println(list);
             */
            writer.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
