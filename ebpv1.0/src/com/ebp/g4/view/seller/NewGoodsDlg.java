package com.ebp.g4.view.seller;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ebp.g4.dao.beans.GoodsType;
import com.ebp.g4.service.beans.MyGoods;
import com.ebp.g4.service.implement.ServiceFactory;
import com.ebp.g4.service.interfaces.GoodsServiceIntf;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.Color;

public class NewGoodsDlg extends JDialog
{
    /**
     * 上架新宝贝
     */
    private static final long serialVersionUID = 1L;

    private final JPanel contentPanel = new JPanel();

    private JTextField textField[] = new JTextField[5];

    private JLabel label[] = new JLabel[7];

    JScrollPane jscrollPane;

    JTextArea textArea;

    private GoodsServiceIntf goodsService = ServiceFactory.getGoodsService();

    /*
     * public static void main(String[] args) { try { w1 dialog = new w1(); //
     * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
     * //dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); }
     * }
     */

    public NewGoodsDlg(String accountID)
    {
        setBounds(100, 100, 646, 688);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        List<String> list = goodsService.getChangeMyGoodsPropertyNames();
        for (int i = 0; i < list.size(); i++)
        {
            if (i < list.size() - 1)
            {
                label[i] = new JLabel(list.get(i) + "*");
                label[i].setBounds(112, 40 + 70 * i, 72, 35);
            }
            else
            {
                label[i] = new JLabel(list.get(i));
                label[i].setBounds(112, 40 + 70 * i, 72, 35);
            }
            // label[1] = new JLabel("类别");
            // label[1].setBounds(112, 110, 72, 35);
            // label[2] = new JLabel("价格");
            // label[2].setBounds(112, 180, 72, 35);
            // label[3] = new JLabel("运费");
            // label[3].setBounds(112, 250, 72, 35);
            // label[4] = new JLabel("图片");
            // label[4].setBounds(112, 320, 72, 35);
            // label[5] = new JLabel("库存");
            // label[5].setBounds(112, 390, 72, 35);
            // label[6] = new JLabel("宝贝介绍");
            // label[6].setBounds(112, 460, 72, 35);
        }
        for (int i = 0; i < label.length; i++)
        {
            contentPanel.add(label[i]);
        }
        for (int i = 0; i < textField.length; i++)
        {
            textField[i] = new JTextField();
            textField[i].setColumns(10);
            contentPanel.add(textField[i]);
        }
        {
            textField[0].setBounds(199, 40, 280, 35);
            textField[1].setBounds(199, 180, 280, 35);
            textField[2].setBounds(199, 250, 280, 35);
            textField[3].setBounds(199, 320, 188, 35);
            textField[3].setEditable(false);
            textField[4].setBounds(199, 390, 280, 35);
        }
        textArea = new JTextArea();
        textArea.setLineWrap(true);// 设置文本区的换行策略。
        jscrollPane = new JScrollPane(textArea);
        // 设置矩形大小，是在滚动条上设置，而不是在文本框上设置；
        jscrollPane.setBounds(199, 460, 280, 60);
        // 默认的设置是超过文本框才会显示滚动条，以下设置让滚动条一直显示
        jscrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentPanel.add(jscrollPane);

        List<GoodsType> type = goodsService.getGoodsType();
        JComboBox<String> comboBox = new JComboBox<String>();
        for (int i = 0; i < type.size(); i++)
        {
            comboBox.addItem(type.get(i).getName());
        }
        comboBox.setBounds(199, 110, 280, 35);
        comboBox.setFont(new Font("simhei", Font.PLAIN, 15));
        contentPanel.add(comboBox);

        JButton btnNewButton = new JButton("上传");
        btnNewButton.setBackground(new Color(144, 238, 144));
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBounds(388, 320, 88, 35);
        contentPanel.add(btnNewButton);
        /**
         * 给文本框赋值上传的文件名
         */
        btnNewButton.addMouseListener(new MouseAdapter()
        { // 添加鼠标点击事件
            public void mouseClicked(MouseEvent event)
            {
                textField[3].setText(eventOnImport(new JButton()));
            }
        });

        JButton okButton = new JButton("提交");
        okButton.setBackground(new Color(144, 238, 144));
        okButton.setForeground(Color.WHITE);
        okButton.setBounds(199, 570, 88, 35);
        contentPanel.add(okButton);
        /**
         * 提交按钮，判断是否所有必填项已填，然后上传数据到库
         */
        okButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                // TODO Auto-generated method stub
                int k = 0;
                for (k = 0; k < 5; k++)
                {
                    if (textField[k].getText().trim().isEmpty())
                    {
                        break;
                    }
                }
                if (k < 5)
                {
                    JOptionPane.showMessageDialog(new JDialog(), "请填写所有必填项");
                }
                else
                {
                    MyGoods myGoods = new MyGoods();
                    myGoods.setGoodsName(textField[0].getText().trim());
                    myGoods.setGoodsType(comboBox.getSelectedItem() + "");
                    myGoods.setGoodsPrice(
                            Float.parseFloat(textField[1].getText().trim()));
                    myGoods.setGoodsCarriage(
                            Float.parseFloat(textField[2].getText().trim() + ""));
                    myGoods.setPicture(textField[3].getText().trim());
                    myGoods.setGoodsStock(
                            Integer.parseInt(textField[4].getText().trim()));
                    myGoods.setGoodsInfo(textArea.getText().trim());
                    goodsService.putMyGoodsIntoDataBase(myGoods, accountID);
                    // closeFrame("提交");
                    dispose();
                }
            }
        });

        JButton cancelButton = new JButton("取消");
        cancelButton.setBackground(new Color(144, 238, 144));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBounds(320, 570, 88, 35);
        contentPanel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                // TODO Auto-generated method stub
                // closeFrame("退出");
                dispose();
            }

        });
        this.setIconImage(new ImageIcon("image/logos/add.png").getImage());
        this.setModal(true);
        this.setTitle("上架新宝贝");
        this.setVisible(true);
        this.setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    /*
     * private void closeFrame(String s) { int result =
     * JOptionPane.showConfirmDialog(null, "是否要" + s + "？", s + "确认",
     * JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); if (result ==
     * JOptionPane.YES_OPTION) dispose(); }
     */

    /**
     * 文件上传功能
     * 
     * @param developer 按钮控件名称
     */
    public String eventOnImport(JButton developer)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        /** 过滤文件类型 * */
        FileNameExtensionFilter filter1 = new FileNameExtensionFilter("jpg",
                "jpg");
        FileNameExtensionFilter filter2 = new FileNameExtensionFilter("jpeg",
                "jpeg");
        chooser.addChoosableFileFilter(filter2);
        chooser.addChoosableFileFilter(filter1);
        int returnVal = chooser.showOpenDialog(developer);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            /** 得到选择的文件* */
            File[] arrfiles = chooser.getSelectedFiles();
            if (arrfiles == null || arrfiles.length == 0)
            {
                return "";
            }
            FileInputStream input = null;
            FileOutputStream out = null;
            String path = "image/goodsphoto/";
            try
            {
                for (File f : arrfiles)
                {
                    File dir = new File(path);
                    // System.out.print(path);
                    /** 目标文件夹 * */
                    File[] fs = dir.listFiles();
                    HashSet<String> set = new HashSet<String>();
                    for (File file : fs)
                    {
                        set.add(file.getName());
                    }
                    /** 判断是否已有该文件* */
                    if (set.contains(f.getName()))
                    {
                        JOptionPane.showMessageDialog(new JDialog(),
                                f.getName() + ":该文件已存在！" + path);
                        return "";
                    }
                    input = new FileInputStream(f);
                    byte[] buffer = new byte[1024];
                    File des = new File(path, f.getName());
                    out = new FileOutputStream(des);
                    int len = 0;
                    while (-1 != (len = input.read(buffer)))
                    {
                        out.write(buffer, 0, len);
                    }
                    out.close();
                    input.close();
                }
                JOptionPane.showMessageDialog(null, "上传成功！", "提示",
                        JOptionPane.INFORMATION_MESSAGE);

            }
            catch (FileNotFoundException e1)
            {
                JOptionPane.showMessageDialog(null, "上传失败！", "提示",
                        JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
            catch (IOException e1)
            {
                JOptionPane.showMessageDialog(null, "上传失败！", "提示",
                        JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        }
        return chooser.getSelectedFile() == null ? ""
                : chooser.getSelectedFile().getName();
    }
}
