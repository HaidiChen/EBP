package com.ebp.g4.view.buyer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.ebp.g4.service.beans.GoodsCmt;
import com.ebp.g4.service.implement.ServiceFactory;
import com.ebp.g4.service.interfaces.GoodsServiceIntf;
import com.ebp.g4.view.MainFrame;
import com.ebp.g4.view.user.RegisterDlg;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GoodsCmtFrame extends JFrame {
	private GoodsServiceIntf  Goodsservices = ServiceFactory.getGoodsService();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */

	
    public GoodsCmtFrame(String goodsname)   {
       /* setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);*/
        this.setIconImage(new ImageIcon("image/logos/commment.png").getImage());
	    setResizable(false);  
		setTitle("商品评价");                                //窗口标题
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//只关闭当前窗口
		setBounds(100, 100, 338, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton B_GoodCmt = new JButton("好评");    //提交好评按钮
		
		B_GoodCmt.setBounds(35, 220, 93, 23);
		contentPane.add(B_GoodCmt);
		
		JButton B_BadCmt = new JButton("差评");  //提交差评按钮
		B_BadCmt.setBounds(207, 220, 93, 23);
		contentPane.add(B_BadCmt);
	/*	GoodsCmtText.setToolTipText("");*/
		
		JLabel label = new JLabel("评价：");   //标题栏 
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		label.setBounds(35, 10, 141, 15);
		contentPane.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 35, 263, 159);
		contentPane.add(scrollPane);
		
		JTextPane GoodsCmtText = new JTextPane();  //文本输入

		GoodsCmtText.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {		        
		      /* char ch = (char) e.getKeyChar();*/  //得到当前输入字符
	             String text = GoodsCmtText.getText(); 
		                if(text.length()>=100)            // 设置输入长度
		                {
		                    e.consume();
		                }	        
		    }
		});
		Date d = new Date();
		String data = new SimpleDateFormat("yyyy-MM-dd").format(d);
	
		scrollPane.setViewportView(GoodsCmtText);
		GoodsCmtText.setText("");
		
		GoodsCmt goodsCmt = new GoodsCmt();
		B_GoodCmt.addActionListener(new ActionListener() { //点击好评获取文本内容
            public void actionPerformed(ActionEvent e) {
            	goodsCmt.setCommentType("cmntt1");
            	goodsCmt.setCommentContent(GoodsCmtText.getText());
            	goodsCmt.setCommentDate(data);
            	goodsCmt.setUserName(MainFrame.Useraccount);
            	goodsCmt.setGoodsName(goodsname);
           //     System.out.println(MainFrame.Useraccount+" "+goodsname+" "+GoodsCmtText.getText()+"商品名"+goodsname);

            
            	
				if(Goodsservices.putCommentIntoDataBase(goodsCmt)) {
					JOptionPane.showMessageDialog(contentPane, "评价成功！");
					B_GoodCmt.setEnabled(false);
					B_BadCmt.setEnabled(false);
					dispose();
				}
					else {
					JOptionPane.showMessageDialog(contentPane, "评价失败！");	
					B_GoodCmt.setEnabled(false);
					B_BadCmt.setEnabled(false);
					}
            		
           //    System.out.println(GoodsCmtText.getText().trim());
            
               
            } 
        });
		B_BadCmt.addActionListener(new ActionListener() { //点击差评获取文本内容
            public void actionPerformed(ActionEvent e) {
            	goodsCmt.setCommentType("cmntt2");
            	goodsCmt.setCommentContent(GoodsCmtText.getText());
            	goodsCmt.setCommentDate(data);
            	goodsCmt.setUserName(MainFrame.Useraccount);
            	goodsCmt.setGoodsName(goodsname);
            	
            	
            	if(Goodsservices.putCommentIntoDataBase(goodsCmt)) {
                    JOptionPane.showMessageDialog(contentPane, "评价成功！");
                    B_GoodCmt.setEnabled(false);
                    B_BadCmt.setEnabled(false);
                    dispose();
                }
                    else {
                    JOptionPane.showMessageDialog(contentPane, "评价失败！");    
                    B_GoodCmt.setEnabled(false);
                    B_BadCmt.setEnabled(false);
                    }
            //   System.out.println(GoodsCmtText.getText().trim());
               
            }
        });
	}
}

