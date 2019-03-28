package com.ebp.g4.view.seller;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import com.ebp.g4.service.beans.CartGoods;
import com.ebp.g4.service.beans.GoodsCmt;
import com.ebp.g4.service.beans.GoodsDetail;
import com.ebp.g4.service.beans.MainFrameGoods;
import com.ebp.g4.service.beans.Store;
import com.ebp.g4.service.implement.ServiceFactory;
import com.ebp.g4.service.interfaces.GoodsServiceIntf;
import com.ebp.g4.service.interfaces.MainFrameServiceIntf;
import com.ebp.g4.service.interfaces.OrderServiceIntf;
import com.ebp.g4.service.interfaces.UserServiceIntf;
import com.ebp.g4.view.*;
import com.ebp.g4.view.buyer.CartFrame;
import com.ebp.g4.view.buyer.GoodsCmtFrame;
import com.ebp.g4.view.user.LogInDlg;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class GoodsDetailFrame extends JFrame {

	private JPanel contentPane;
	private JTable table_evaluate;
	//private String goods_name;
	private GoodsServiceIntf goodsservice=ServiceFactory.getGoodsService();
	private OrderServiceIntf orderservice=ServiceFactory.getOrderService();
	private UserServiceIntf userservice=ServiceFactory.getUserService();
	private Component jPanel;       //  提示框  
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoodsDetailFrame frame = new GoodsDetailFrame("养乐多","妞妞零食店");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GoodsDetailFrame(String goods_name,String shop_name) {
		setTitle("商品详情");
		this.setIconImage(new ImageIcon("image/logos/order.png").getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setSize(796,600);
		/**
		 *通过商品名获取  相应商品详情  
		 * 
		 * 
		 * */
		GoodsDetail goodsdetail=goodsservice.getGoodsDetailByGoodsName(goods_name);   //获取 goodsdetail 通过 商品名
		JPanel panel_left = new JPanel();
		/**
		 * 显示商品名
		 * */
		JLabel label_goodsname = new JLabel(goods_name);
		label_goodsname.setFont(new Font("宋体", Font.PLAIN, 15));
		
		JPanel panel_5 = new JPanel();
		/**
		 * 商品图片的显示--------------
		 * 
		 * 
		 * */
		JLabel label_picture = new JLabel("  ");
		String path="image/goodsphoto/"+goodsdetail.getPitcure();
		ImageIcon image_recommend = new ImageIcon(path);
		image_recommend.setImage(image_recommend.getImage().getScaledInstance(180,130,Image.SCALE_DEFAULT));
		label_picture.setIcon(image_recommend);
		JButton button_buynow = new JButton("立即购买");
		button_buynow.setForeground(new Color(255, 255, 255));
		button_buynow.setBackground(new Color(144, 238, 144));
		/**
		 * 
		 * 宝贝详情的显示
		 * 
		 * 
		 * 
		 * 
		 * */
		JLabel label_baobei = new JLabel("宝贝详情");
		String goodinfo= goodsdetail.getGoodsInfo();          // 详情获取获取
		JTextPane textPane_goodsinfo = new JTextPane();
		textPane_goodsinfo.setEditable(false);
		textPane_goodsinfo.setText(goodinfo);               //文本显示
		
		
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
		    gl_panel_5.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_panel_5.createSequentialGroup()
		            .addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
		                .addGroup(gl_panel_5.createSequentialGroup()
		                    .addGap(86)
		                    .addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
		                        .addComponent(textPane_goodsinfo, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
		                        .addComponent(label_picture, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)))
		                .addGroup(gl_panel_5.createSequentialGroup()
		                    .addGap(143)
		                    .addComponent(label_baobei))
		                .addGroup(gl_panel_5.createSequentialGroup()
		                    .addGap(127)                                  //横
		                    .addComponent(button_buynow)))
		            .addContainerGap(81, Short.MAX_VALUE))
		);
		gl_panel_5.setVerticalGroup(
		    gl_panel_5.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_panel_5.createSequentialGroup()
		            .addContainerGap()
		            .addComponent(label_picture, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
		            .addGap(42)
		            .addComponent(button_buynow)                       //
		            .addGap(40)
		            .addComponent(label_baobei)
		            .addGap(37)
		            .addComponent(textPane_goodsinfo, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
		            .addGap(35))
		);
		panel_5.setLayout(gl_panel_5);
		
		JPanel panel_2 = new JPanel();
		
		JPanel panel_3 = new JPanel();
		
		JPanel panel_4 = new JPanel();
		panel_4.setForeground(Color.DARK_GRAY);
		
		
		/**
		 * 
		 * 商品价格显示
		 * 
		 * 
		 * */
		JLabel label_price_cn = new JLabel("价格：￥");
		JLabel label_pricenum = new JLabel();
		//System.out.println("价格"+goodsdetail.getGoodsPrice());                 //----------输出测试
		String price=String.valueOf(goodsdetail.getGoodsPrice());  //将商品价格   改为  string 类型  再显示----似乎有问题
		label_pricenum.setText(price);
		label_pricenum.setFont(new Font("Dialog", Font.BOLD, 15));
		/**
		 * 商品交易成功数额
		 * 
		 * */
		JLabel label_sellnum_cn = new JLabel("交易成功：");
		
		JLabel label_sellnum = new JLabel();
		//System.out.print("交易成功"+goodsdetail.getSellingNumbers());            //-------------输出测试
		String sellnum=String.valueOf(goodsdetail.getSellingNumbers());    //将    出售数额 改为 字符型  输出----无数据读入
		label_sellnum.setText(sellnum);
		label_sellnum.setForeground(new Color(50, 205, 50));
		label_sellnum.setFont(new Font("Dialog", Font.BOLD, 12));
		/**
		 * 
		 * 
		 * 选择数目
		 * 
		 * */
		JLabel label_buynum_cn = new JLabel("购买数量：");
		JSpinner spinner_buynum = new JSpinner();
		if(goodsdetail.getGoodsNumbers()==0)
		{
		    spinner_buynum.setModel(new SpinnerNumberModel(0, 0,goodsdetail.getGoodsNumbers() , 1));
		}
		else
		{
		 spinner_buynum.setModel(new SpinnerNumberModel(1, 1,goodsdetail.getGoodsNumbers() , 1));
		}
       
        
		/**
		 * 
		 * 库存的显示 
		 * 
		 * 
		 * 
		 * **/
		JLabel label_stock_cn = new JLabel("库存：");
		
		JLabel label_stocknum = new JLabel("");
		//System.out.print("库存"+goodsdetail.getGoodsNumbers());         //输出测试
	    if(goodsdetail.getGoodsNumbers()>0)
	    {
		 String stocknum=String.valueOf(goodsdetail.getGoodsNumbers());     //库存 转换为 字符 
		 label_stocknum.setText(stocknum);
	    }
	    else
	    {
	        label_stocknum.setText("0");
	    }
		/**
		 * 立即购买功能实现
		 * 
		 * */
		button_buynow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              //  boolean createOrder(CartGoods goods, String time);
                CartGoods new_order=new CartGoods();
                new_order.setGoodsName(goods_name);
                new_order.setShopName(shop_name);
                new_order.setGoodsNumber(Integer.valueOf(spinner_buynum.getValue().toString()));
                new_order.setGoodsPrice(goodsdetail.getGoodsPrice());
                new_order.setGoodsInfo(goodsdetail.getGoodsInfo());
                new_order.setMoney(Integer.valueOf(spinner_buynum.getValue().toString())*goodsdetail.getGoodsPrice());
               // boolean createOrder(String account, CartGoods goods, String time);
                Date date = new Date();
                //设置要获取到什么样的时间
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                //获取String类型的时间
                String time = sdf.format(date);
               // System.out.println(time);
                Store s=userservice.getStoreByAccount(MainFrame.Useraccount);
                if(shop_name.equals(s.getShopName()))
                { 
                    JOptionPane.showMessageDialog
                    (jPanel, "不可购买本店铺商品!", "提示",JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                     if(new_order.getGoodsNumber()>goodsdetail.getGoodsNumbers()||goodsdetail.getGoodsNumbers()==0)
                     {
                         //System.out.println(new_order.getGoodsNumber()+"  "+goodsdetail.getGoodsNumbers());
                         JOptionPane.showMessageDialog
                         (jPanel, "库存不足!", "提示",JOptionPane.WARNING_MESSAGE); 
                     }
                     else
                     {
                         int result = JOptionPane.showConfirmDialog(null, "确认直接购买", "确认", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);  
                         if (result == JOptionPane.YES_OPTION)  
                         {
                    
                             if(orderservice.createOrder(MainFrame.Useraccount, new_order, time)==true)
                             {
                                 JOptionPane.showMessageDialog
                                 (jPanel, "生成订单成功，可以去个人订单界面查看详细信息!", "提示",JOptionPane.WARNING_MESSAGE); 
                                 MainFrame.isChange=1;
                             }
                             else
                             {
                                 JOptionPane.showMessageDialog
                                 (jPanel, "生成订单失败！", "提示",JOptionPane.WARNING_MESSAGE); 
                             }
                    
                         }
                     }
                }
            }
        });
		
		
		
		/**
		 * 
		 * 加入购物车的功能 -----------------------------  
		 * 
		 * 
		 * 
		 * 
		 * 
		 * */
		JButton button_go_cart = new JButton("加入购物车");
		button_go_cart.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) 
		    {
		      //  CartGoods createCartGoodsByName(String goodsName,String shopName,int goodsNumber);
		        Store s=userservice.getStoreByAccount(MainFrame.Useraccount);
                if(shop_name.equals(s.getShopName()))
                { 
                    JOptionPane.showMessageDialog
                    (jPanel, "不可购买本店铺商品!", "提示",JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    if(Integer.valueOf(spinner_buynum.getValue().toString())>goodsdetail.getGoodsNumbers()||goodsdetail.getGoodsNumbers()==0)
                    {
                        JOptionPane.showMessageDialog
                        (jPanel, "库存不足!", "提示",JOptionPane.WARNING_MESSAGE); 
                    }
                    else
                    {
                        int result = JOptionPane.showConfirmDialog(null, "确认加入购物车", "确认", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);  
                        if (result == JOptionPane.YES_OPTION)  
                        {
                            if(goodsservice.createCartGoodsByName(goods_name,MainFrame.Useraccount,Integer.valueOf(spinner_buynum.getValue().toString()) ))
                            {
                                JOptionPane.showMessageDialog
		                        (jPanel, "已加入购物车，可以去购物车界面查看信息!", "提示",JOptionPane.WARNING_MESSAGE); 
                            }
                            else
                            {
                                JOptionPane.showMessageDialog
                                (jPanel, "加入购物车失败!", "提示",JOptionPane.WARNING_MESSAGE); 
	                     
                            }
		            
		           
                        } 
                    }
                }
		    }
		});
		/***
		 * 
		 * 选择商品数目的监听
		 * 
		 * 
		 * */
	/*	spinner_buynum.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String ch = e.get();  //得到当前输入字符
                if(Integer.valueOf(ch) > goodsdetail.getGoodsNumbers())
                {
                    e.consume();
                }
                else
                {
                   // e.consume();
                }
                
                
                
            }
        });*/
		/**
		 * 判断是否登陆
		 * 
		 * */
		if(MainFrame.isLogin==0)
        {
            
            button_go_cart.setEnabled(false);
            button_buynow.setEnabled(false);
            
            
        }
        else
        {
            button_go_cart.setEnabled(true);
            button_buynow.setEnabled(true);
        }
		
		
		

		button_go_cart.setForeground(new Color(255, 255, 255));
		button_go_cart.setBackground(new Color(144, 238, 144));
		
		JLabel label_evaluate_cn = new JLabel("累计评论：");
		// int getCommentAmountByGoodsName(String goodsName);
		JLabel label_evaluate_num = new JLabel("");
		label_evaluate_num.setForeground(Color.RED);
	//	System.out.println(String.valueOf(goodsservice.getCommentAmountByGoodsName(goods_name)));
		label_evaluate_num.setText(String.valueOf(goodsservice.getCommentAmountByGoodsName(goods_name)));
		
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton radioButton_all_evaluate = new JRadioButton("全部");
		JRadioButton radioButton_good_evaluate = new JRadioButton("好评");
		JRadioButton radioButton_bad_evaluate = new JRadioButton("差评");
		buttonGroup.add(radioButton_all_evaluate);
		buttonGroup.add(radioButton_good_evaluate);
		buttonGroup.add(radioButton_bad_evaluate);
		radioButton_all_evaluate.setSelected(true);                                 //默认选中的是全选按钮
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
		    gl_contentPane.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_contentPane.createSequentialGroup()
		            .addComponent(panel_left, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE)
		            .addPreferredGap(ComponentPlacement.RELATED)
		            .addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
		            .addGap(19)
		            .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		        .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 774, GroupLayout.PREFERRED_SIZE)
		);
		gl_contentPane.setVerticalGroup(
		    gl_contentPane.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_contentPane.createSequentialGroup()
		            .addGap(10)
		            .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
		                .addComponent(panel_left, GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
		                .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 531, GroupLayout.PREFERRED_SIZE)
		                .addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE))
		            .addPreferredGap(ComponentPlacement.RELATED)
		            .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		GroupLayout gl_panel_left = new GroupLayout(panel_left);
		gl_panel_left.setHorizontalGroup(
		    gl_panel_left.createParallelGroup(Alignment.TRAILING)
		        .addGroup(gl_panel_left.createSequentialGroup()
		            .addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
		            .addContainerGap())
		        .addGroup(gl_panel_left.createSequentialGroup()
		            .addContainerGap(132, Short.MAX_VALUE)
		            .addComponent(label_goodsname, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
		            .addGap(26))
		);
		gl_panel_left.setVerticalGroup(
		    gl_panel_left.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_panel_left.createSequentialGroup()
		            .addComponent(label_goodsname, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
		            .addPreferredGap(ComponentPlacement.RELATED)
		            .addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE))
		);
		panel_left.setLayout(gl_panel_left);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
		    gl_panel_4.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_panel_4.createSequentialGroup()
		            .addContainerGap()
		            .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
		            .addContainerGap())
		        .addGroup(gl_panel_4.createSequentialGroup()
		            .addGap(22)
		            .addComponent(label_stock_cn)
		            .addContainerGap(329, Short.MAX_VALUE))
		        .addGroup(Alignment.TRAILING, gl_panel_4.createSequentialGroup()
		            .addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
		                .addGroup(gl_panel_4.createSequentialGroup()
		                    .addContainerGap()
		                    .addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING, false)
		                        .addGroup(gl_panel_4.createSequentialGroup()
		                            .addComponent(radioButton_all_evaluate)
		                            .addPreferredGap(ComponentPlacement.RELATED)
		                            .addComponent(radioButton_good_evaluate))
		                        .addGroup(gl_panel_4.createSequentialGroup()
		                            .addComponent(label_evaluate_cn)
		                            .addGap(20)
		                            .addComponent(label_evaluate_num, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
		                    .addPreferredGap(ComponentPlacement.RELATED)
		                    .addComponent(radioButton_bad_evaluate))
		                .addGroup(Alignment.TRAILING, gl_panel_4.createSequentialGroup()
		                    .addContainerGap()
		                    .addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
		                        .addComponent(label_price_cn)
		                        .addComponent(label_buynum_cn)
		                        .addComponent(label_sellnum_cn))
		                    .addGap(55)
		                    .addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
		                        .addComponent(label_sellnum, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
		                        .addComponent(label_pricenum, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
		                        .addComponent(spinner_buynum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                        .addComponent(label_stocknum, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)))
		                .addGroup(gl_panel_4.createSequentialGroup()
		                    .addGap(45)
		                    .addComponent(button_go_cart)))
		            .addGap(226))
		);
		gl_panel_4.setVerticalGroup(
		    gl_panel_4.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_panel_4.createSequentialGroup()
		            .addGap(63)
		            .addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
		                .addComponent(label_price_cn)
		                .addComponent(label_pricenum))
		            .addGap(18)
		            .addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
		                .addComponent(label_sellnum_cn)
		                .addComponent(label_sellnum))
		            .addGap(18)
		            .addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
		                .addComponent(label_buynum_cn)
		                .addComponent(spinner_buynum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		            .addGap(18)
		            .addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
		                .addComponent(label_stock_cn)
		                .addComponent(label_stocknum))
		            .addGap(43)
		            .addComponent(button_go_cart)
		            .addGap(39)
		            .addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
		                .addComponent(label_evaluate_cn)
		                .addComponent(label_evaluate_num))
		            .addGap(6)
		            .addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
		                .addComponent(radioButton_all_evaluate)
		                .addComponent(radioButton_good_evaluate)
		                .addComponent(radioButton_bad_evaluate))
		            .addPreferredGap(ComponentPlacement.UNRELATED)
		            .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
		            .addGap(20))
		);
		/**
		 * 评论列表显示
		 * 
		 * */
		table_evaluate = new JTable();
		List<String> Cname=goodsservice.getGoodsCommentPropertyNames();
		List<GoodsCmt> Cmtlist=goodsservice.getAllCommentByGoodsName(goods_name);
		DefaultTableModel model = new DefaultTableModel()
        {            // 新建一个默认数据模型
            public boolean isCellEditable(int row, int column)
            {
                       return false;                        //表格不允许被编辑
            }
        }; 
        table_evaluate.setModel(model);                       //加入model
        Object[] pname2=Cname.toArray();                   //
        model.setDataVector(null, pname2);
        for(int i=0;i<Cmtlist.size();i++){                             //按行 添加数据
            Object[]rowData={                                                //将每行数据点入
                    Cmtlist.get(i).getUserName(),Cmtlist.get(i).getCommentContent(),
                    Cmtlist.get(i).getCommentDate()
                    };
            model.addRow(rowData);                                                    
        }
        for(int i=0;i<Cname.size();i++)                 
        {
            table_evaluate.getColumnModel().getColumn(i).setResizable(false);    //设置不可修改列宽
        }
         
	
		table_evaluate.getColumnModel().getColumn(1).setPreferredWidth(260);
		table_evaluate.getColumnModel().getColumn(2).setPreferredWidth(157);
		scrollPane.setViewportView(table_evaluate);
		panel_4.setLayout(gl_panel_4);
		contentPane.setLayout(gl_contentPane);
		
		
		//button_buynow.setBounds(1100,50,80,30);
		/**
		 * 全部评论 按钮  的事件
		 * 
		 * **/
		radioButton_all_evaluate.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
             //     List<GoodsCmt> getAllCommentByGoodsName(String goodsName);
                /**
                                 评论列表显示 
                 * */
             List<GoodsCmt> all_evaluate=goodsservice.getAllCommentByGoodsName(goods_name);
             model.setRowCount(0);                                //清空表
             for(int i=0;i<all_evaluate.size();i++){                             //按行 添加数据
                 Object[]rowData={                                                //将每行数据点入
                         all_evaluate.get(i).getUserName(),all_evaluate.get(i).getCommentContent(),
                         all_evaluate.get(i).getCommentDate()
                         };
                // System.out.println(rowData);
                 model.addRow(rowData);                                                    
             }
           
            }
            
         });
		
		radioButton_good_evaluate.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
             //     List<GoodsCmt> getAllCommentByGoodsName(String goodsName);
                /**
                 * 好评按钮
                 * 
                 * 
                 * */
             String evaluate_type=new String("cmntt1");
                
             List<GoodsCmt> good_evaluate=goodsservice.getGoodCommentByGoodsName(goods_name, evaluate_type);
             model.setRowCount(0);                                //清空表
             for(int i=0;i<good_evaluate.size();i++)
             {                             //按行 添加数据
                 Object[]rowData={                                                //将每行数据点入
                         good_evaluate.get(i).getUserName(),good_evaluate.get(i).getCommentContent(),
                         good_evaluate.get(i).getCommentDate()
                         };
              
                 model.addRow(rowData);                                                    
             }
            }
         });
		
		radioButton_bad_evaluate.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
             //     List<GoodsCmt> getAllCommentByGoodsName(String goodsName);
            /**
             *   差评按钮
             * 
             * */
             String evaluate_type=new String("cmntt2");
            // System.
             List<GoodsCmt> bad_evaluate=goodsservice.getGoodCommentByGoodsName(goods_name, evaluate_type);
             model.setRowCount(0);                                //清空表
             for(int i=0;i<bad_evaluate.size();i++){                             //按行 添加数据
                 Object[]rowData={                                                //将每行数据点入
                         bad_evaluate.get(i).getUserName(),bad_evaluate.get(i).getCommentContent(),
                         bad_evaluate.get(i).getCommentDate()
                         };
       
                 model.addRow(rowData);                                                    
             }
           
            }
            
         });
		TableStyle.Tablestyle(table_evaluate);
	}
}
