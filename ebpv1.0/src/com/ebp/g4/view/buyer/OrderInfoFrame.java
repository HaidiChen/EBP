package com.ebp.g4.view.buyer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Button;
import java.awt.Component;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;

import com.ebp.g4.dao.beans.Shop;
import com.ebp.g4.service.beans.MainFrameGoods;
import com.ebp.g4.service.beans.OrderInfo;
import com.ebp.g4.service.implement.ServiceFactory;
import com.ebp.g4.service.interfaces.OrderServiceIntf;
import com.ebp.g4.view.MainFrame;
import com.ebp.g4.view.TableStyle;

import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;

public class OrderInfoFrame extends JFrame //我的订单界面
{	
    private JPanel contentPane;
	private DefaultTableModel model;
	private List<OrderInfo> Goodslist;
//	public static String  GoodsID; //全局变量
	 private OrderServiceIntf orderinfo=ServiceFactory.getOrderService();
	  //  private JTable table;

	    private JTable table_1;
	    private Component jPanel;       //  提示框  
	    
	    
	    
	public OrderInfoFrame() 
	{
		setTitle("我的订单");
		setSize(942,607);
		JButton btn1 = new JButton("确认收货");
		btn1.setFont(new Font("宋体", Font.PLAIN, 15));
		btn1.setBackground(new Color(144, 238, 144));
		btn1.setForeground(Color.WHITE);
        /*确认订单按钮的监听器*/
		this.setIconImage(new ImageIcon("image/logos/order.png").getImage());
		JButton btn2 = new JButton("查看物流");
		btn2.setFont(new Font("宋体", Font.PLAIN, 15));
		btn2.setBackground(new Color(144, 238, 144));
		btn2.setForeground(Color.WHITE);
		btn2.addActionListener(new ActionListener()
        {
            private Component jPanel;
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog
                (jPanel, "暂无此功能", "提示",JOptionPane.WARNING_MESSAGE);
            }
        });
		
		JButton btn3 = new JButton("评价");
		btn3.setFont(new Font("宋体", Font.PLAIN, 15));
		btn3.setBackground(new Color(144, 238, 144));
		btn3.setForeground(Color.WHITE);
		
		
		JButton btn4 = new JButton("售后服务");
		btn4.setFont(new Font("宋体", Font.PLAIN, 15));
		btn4.setBackground(new Color(144, 238, 144));
		btn4.setForeground(Color.WHITE);
		btn4.addActionListener(new ActionListener()
        {
            private Component jPanel;
            public void actionPerformed(ActionEvent e)/*售后服务的监听器*/  //暂无此功能
            {
                JOptionPane.showMessageDialog
                (jPanel, "暂无此功能", "提示",JOptionPane.WARNING_MESSAGE);
            }
        });
		
		JScrollPane scrollPane = new JScrollPane();
		
		table_1 = new JTable();
		table_1.setFont(new Font("宋体", Font.PLAIN, 11));
		table_1.setFillsViewportHeight(true);
		
		DefaultTableModel model = new DefaultTableModel()
		 {            // 新建一个默认数据模型
            public boolean isCellEditable(int row, int column)
            {
                       return false;                        //表格不允许被编辑
            }
        }; 
      table_1.setModel(model);
				List<String> Pname1= orderinfo.getOrderInfoPropertyNames();    //通过接口方法获取 首页 表头    返回List
		        Goodslist= orderinfo.getAllOrderInfoByAccount(MainFrame.Useraccount);
		        
		        
		      
		        Object[] pname2=Pname1.toArray();               //
		        model.setDataVector(null, pname2);
		        for(int i=0;i<Goodslist.size();i++){                             //按行 添加数据
		        Object[]rowData={                                                //将每行数据点入
		        		Goodslist.get(i).getOrderID(),
		        		Goodslist.get(i).getGoodsPrice(),Goodslist.get(i).getNumbers(),
	                    Goodslist.get(i).getGoodsName(),Goodslist.get(i).getStore(),
	                    Goodslist.get(i).getOrderDate(),
	                    Goodslist.get(i).getCarriage(),Goodslist.get(i).getThePay(),
	                    Goodslist.get(i).getStatus()
		                
		                };
		     /*   System.out.println(Goodslist.get(i).getGoodsName()+"-"+Goodslist.get(i).getGoodsType()
		                +"-" +Goodslist.get(i).getGoodsPrice()+"-"+Goodslist.get(i).getGoodsSalesAmount()+"-"+
		                Goodslist.get(i).getGoodsCredit()+"-"+Goodslist.get(i).getGoodStoreName());*/       
		      
		        model.addRow(rowData);                                                    
		        }
		                       
		        for(int i=0;i<Pname1.size();i++)                 
		        {
		            table_1.getColumnModel().getColumn(i).setResizable(false);    //设置不可修改列宽
		        }
		
		scrollPane.setViewportView(table_1);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
		    groupLayout.createParallelGroup(Alignment.LEADING)
		        .addGroup(groupLayout.createSequentialGroup()
		            .addGap(79)
		            .addComponent(btn1, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
		            .addGap(10)
		            .addComponent(btn2, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
		            .addGap(10)
		            .addComponent(btn3, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
		            .addGap(10)
		            .addComponent(btn4, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
		            .addGap(88))
		        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
		    groupLayout.createParallelGroup(Alignment.LEADING)
		        .addGroup(groupLayout.createSequentialGroup()
		            .addGap(5)
		            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(btn1)
		                .addComponent(btn2)
		                .addComponent(btn3)
		                .addComponent(btn4))
		            .addPreferredGap(ComponentPlacement.UNRELATED)
		            .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
		            .addGap(0))
		);
		getContentPane().setLayout(groupLayout);
		 btn3.addActionListener(new ActionListener() {       //评价按钮监听
             private Component jPanel;     //  提示框  

             @Override
             public void actionPerformed(ActionEvent e)
             {
                 
                 if(table_1.getSelectedRow()!=-1)       //判断是否选中一行订单信息
                 {
                     int index1 = table_1.getSelectedRow();
                     int i = 0;
                     int j = 0;
                     for (i = 0; i < table_1.getColumnCount(); i++)
                     {
                         if (table_1.getColumnName(i).equals("商品名称"))
                             break;
                     }
          
                     for (j= 0; j< table_1.getColumnCount(); j++)
                     {
                         if (table_1.getColumnName(j).equals("交易状态"))
                             break;
                     }
                     
                     String goodsname = table_1.getValueAt(index1, i).toString();
                      String state = table_1.getValueAt(index1, j).toString();
                      if(state.equals("已收货"))
                      {
                          GoodsCmtFrame frame1 = new GoodsCmtFrame(goodsname);   //显示评价界面
                          frame1.setVisible(true);
                      }
                      else
                      {
                          JOptionPane.showMessageDialog
                          (jPanel, "未收货", "提示",JOptionPane.WARNING_MESSAGE);
                      }
                     
                 }
                 else
                 {                                               //提示
                    
                     JOptionPane.showMessageDialog
                     (jPanel, "请选择一行订单", "提示",JOptionPane.WARNING_MESSAGE);  
                 }
               /*  GoodsCmtFrame frame = new GoodsCmtFrame();   //显示评价界面
                 frame.setVisible(true);*/
             }
             
         });
     
		btn1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                if (table_1.getSelectedRow() != -1) // 判断是否选中一行订单信息
                {
                    int num[] = table_1.getSelectedRows();
                    if (num.length > 1)
                    {
                        JOptionPane.showMessageDialog(contentPane, "只能选一行",
                                "提示", JOptionPane.WARNING_MESSAGE);
                    }
                    else
                    {

                        OrderInfo update=new OrderInfo();
                        
                        
                        int index1 = table_1.getSelectedRow();
                        int i = 0;
                        int j = 0;
                        for (i = 0; i < table_1.getColumnCount(); i++)
                        {
                            if (table_1.getColumnName(i).equals("交易状态"))
                                break;
                        }
                        for (j = 0; j < table_1.getColumnCount(); j++)
                        {
                            if (table_1.getColumnName(j).equals("订单编号"))
                                break;
                        }
                        
                        String state = table_1.getValueAt(index1, i).toString();
                        String orderNum = table_1.getValueAt(index1, j).toString();
             
                      if (state.equals("已发货"))
                        {
                                update =Goodslist.get(index1);
                                update.setStatus("已收货");
                            orderinfo.updateOrderInfo(orderNum, update);
                            JOptionPane.showMessageDialog
                            (jPanel, "确认成功", "提示",JOptionPane.WARNING_MESSAGE);
                            table_1.setValueAt("已收货", index1, i);
                        }
                      else
                      {
                          if(state.equals("未发货"))
                          {
                              JOptionPane.showMessageDialog
                              (jPanel, "未发货", "提示",JOptionPane.WARNING_MESSAGE);
                              //table.setValueAt("已发货", index1, i);
                          }
                      }
                    }

                }
                else
                { // 提示
                    JOptionPane.showMessageDialog(contentPane, "请选择一行订单", "提示",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
		
		
		TableStyle.Tablestyle(table_1);
		setVisible(true);
   

	}
    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    OrderInfoFrame frame = new OrderInfoFrame();
                    frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}


 