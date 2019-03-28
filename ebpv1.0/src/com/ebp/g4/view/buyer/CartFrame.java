package com.ebp.g4.view.buyer;  

import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.ebp.g4.dao.beans.Order;
import com.ebp.g4.service.beans.CartGoods;
import com.ebp.g4.service.beans.MyGoods;
import com.ebp.g4.service.beans.OrderInfo;
import com.ebp.g4.service.implement.ServiceFactory;
import com.ebp.g4.service.interfaces.GoodsServiceIntf;
import com.ebp.g4.service.interfaces.OrderServiceIntf;
import com.ebp.g4.view.MainFrame;
import com.ebp.g4.view.seller.GoodsInfoModifyDlg;
import com.ebp.g4.view.seller.NewGoodsDlg;

import javax.swing.ListSelectionModel;
import javax.swing.JPopupMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.ebp.g4.dao.beans.Order;
import com.ebp.g4.service.beans.CartGoods;
import com.ebp.g4.service.beans.OrderInfo;
import com.ebp.g4.service.implement.ServiceFactory;
import com.ebp.g4.service.interfaces.OrderServiceIntf;

import javax.swing.ListSelectionModel;
import javax.swing.JPopupMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
public class CartFrame extends JFrame {

	private JPanel contentPane;
	private List<CartGoods> Goodslist;
    private JScrollPane js;
    private DefaultTableModel model;
    private JPanel jPanel;
    private String name;
    private JLabel label;
    private JLabel label_1;
    private float sum = 0;
    private GoodsServiceIntf cart=ServiceFactory.getGoodsService();
    private OrderServiceIntf orderinfo=ServiceFactory.getOrderService();
    private JTable table;
	/**
	 * Launch the application.
	 */
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CartFrame frame = new CartFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void remove()
    {
        int rows[] = table.getSelectedRows();
        if (rows.length != 0)
        {
            int n = JOptionPane.showConfirmDialog(null, "确认操作?", "确认对话框",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION)
            {
                for (int j = rows.length - 1; j >= 0; j--)
                {
                    model.removeRow(rows[j]);
                }
                sum = 0;
                label_1.setText(sum + "");
            }
            else if (n == JOptionPane.NO_OPTION)
            {
            }
        }
    }
	/**
	 * Create the frame.
	 */
	public CartFrame() {

	        this.setSize(800, 600);
	        this.setTitle("购物车");
	        GridBagLayout gridBagLayout = new GridBagLayout();
	        gridBagLayout.columnWidths = new int[]{0, 0};
	        gridBagLayout.rowHeights = new int[]{0, 0, 0};
	        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	        gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
	        getContentPane().setLayout(gridBagLayout);
	        
	        JScrollPane scrollPane = new JScrollPane();
	        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
	        gbc_scrollPane.fill = GridBagConstraints.BOTH;
	        gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
	        gbc_scrollPane.gridx = 0;
	        gbc_scrollPane.gridy = 0;
	        getContentPane().add(scrollPane, gbc_scrollPane);
	        
	        table = new JTable();
	        scrollPane.setViewportView(table);
			List<String> Pname1= cart.getCartPropertyNames();   //通过接口方法获取 首页 表头    返回List
		        Goodslist= new ArrayList<CartGoods>();
		        Goodslist = cart.getAllCartGoods(MainFrame.Useraccount);
		        model = new DefaultTableModel()
				 {            // 新建一个默认数据模型
		            public boolean isCellEditable(int row, int column)
		            {
		                       return false;                        //表格不允许被编辑
		            }
		        }; 
		        table.addMouseListener(new MouseAdapter()
		        {
		            @Override
		            public void mouseReleased(MouseEvent arg0)
		            {
		                // TODO Auto-generated method stub
		                int rows[] = table.getSelectedRows();
		                if (rows.length != 0)
		                {
		                    sum = 0;
		                    for (int j = 0; j < rows.length; j++)
		                    {
		                        int k = 0;
		                        for (k = 0; k < table.getColumnCount(); k++)
		                        {
		                            if (table.getColumnName(k) == "金额")
		                                break;
		                        }
		                        sum += Float.parseFloat(
		                                table.getValueAt(rows[j], k).toString());
		                    }
		                    label_1.setText(sum + "");
		                }
		            }
		        });
		      table.setModel(model);
			
				        
				        
				      
				        Object[] pname2=Pname1.toArray();               //
				        model.setDataVector(null, pname2);
				        for(int i=0;i<Goodslist.size();i++){                             //按行 添加数据
					        Object[]rowData={                                                //将每行数据点入
					        		Goodslist.get(i).getShopName(),Goodslist.get(i).getGoodsName(),
					        		Goodslist.get(i).getGoodsInfo(),Goodslist.get(i).getGoodsPrice(),Goodslist.get(i).getGoodsNumber(),
				                    Goodslist.get(i).getMoney()
				                    
					                
					                };
					     /*   System.out.println(Goodslist.get(i).getGoodsName()+"-"+Goodslist.get(i).getGoodsType()
					                +"-" +Goodslist.get(i).getGoodsPrice()+"-"+Goodslist.get(i).getGoodsSalesAmount()+"-"+
					                Goodslist.get(i).getGoodsCredit()+"-"+Goodslist.get(i).getGoodStoreName());*/       
					      
					        model.addRow(rowData);                                                    
					        }
				       
				                       
				        for(int i=0;i<Pname1.size();i++)                   
				        {
				            table.getColumnModel().getColumn(i).setResizable(false);    //设置不可修改列宽
				        }
	        scrollPane.setViewportView(table);
	        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
	        head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
	        head.setFont(new Font("宋体", Font.PLAIN, 18));// 设置表格字体
	        table.getColumnModel().getColumn(0).setPreferredWidth(100);
	        table.getColumnModel().getColumn(0).setMinWidth(30);
	        table.setRowHeight(30);
	        table.setFont(new Font("楷体", Font.PLAIN, 18));
	        DefaultTableCellRenderer ter = new DefaultTableCellRenderer()// 设置表格间隔色
	        {
	            @Override
	            public Component getTableCellRendererComponent(JTable table, Object

	            value, boolean isSelected, boolean hasFocus, int row, int column)
	            {
	                // table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
	                if (row % 2 == 0)
	                    setBackground(new Color(244, 255, 255));
	                else if (row % 2 == 1)
	                    setBackground(Color.white);
	                return super.getTableCellRendererComponent(table, value,

	                        isSelected, hasFocus, row, column);
	            }
	        };
	        ter.setHorizontalAlignment(JLabel.CENTER);
	       
	        head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
	        head.setFont(new Font("宋体", Font.PLAIN, 18));// 设置表格字体
	        table.getColumnModel().getColumn(0).setPreferredWidth(100);
	        table.getColumnModel().getColumn(0).setMinWidth(30);
	        table.setRowHeight(30);
	        table.setFont(new Font("楷体", Font.PLAIN, 18));
	        
	        ter.setHorizontalAlignment(JLabel.CENTER);
	        table.setDefaultRenderer(Object.class, ter);
	        
	        JPanel panel = new JPanel();
	        GridBagConstraints gbc_panel = new GridBagConstraints();
	        gbc_panel.fill = GridBagConstraints.BOTH;
	        gbc_panel.gridx = 0;
	        gbc_panel.gridy = 1;
	        getContentPane().add(panel, gbc_panel);
	        GridBagLayout gbl_panel = new GridBagLayout();
	        gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	        gbl_panel.rowHeights = new int[]{0, 0};
	        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	        gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
	        panel.setLayout(gbl_panel);
	        
	        JLabel label_6 = new JLabel("                                ");
	        GridBagConstraints gbc_label_6 = new GridBagConstraints();
	        gbc_label_6.insets = new Insets(0, 0, 0, 5);
	        gbc_label_6.gridx = 1;
	        gbc_label_6.gridy = 0;
	        panel.add(label_6, gbc_label_6);
	        
	        JButton button = new JButton("删除");
	        button.setForeground(Color.WHITE);
	        button.setBackground(new Color(144, 238, 144));
	        GridBagConstraints gbc_button = new GridBagConstraints();
	        gbc_button.insets = new Insets(0, 0, 0, 5);
	        gbc_button.gridx = 2;
	        gbc_button.gridy = 0;
	        panel.add(button, gbc_button);
	        button.addActionListener(new ActionListener()
	        {
	            @Override
	            public void actionPerformed(ActionEvent arg0)
	            {
	            	 int rows[] = table.getSelectedRows();
	            	 for(int i=0;i<rows.length;i++) {
	            	cart.deleteCartGoodsByName(Goodslist.get(i+rows[0]).getGoodsName(), MainFrame.Useraccount);
	                
	            	 }
	            	 remove();
	            }
	        });
	        
	        JLabel label_4 = new JLabel("    ");
	        GridBagConstraints gbc_label_4 = new GridBagConstraints();
	        gbc_label_4.insets = new Insets(0, 0, 0, 5);
	        gbc_label_4.gridx = 4;
	        gbc_label_4.gridy = 0;
	        panel.add(label_4, gbc_label_4);
	        
	        JLabel label_5 = new JLabel("                                         ");
	        GridBagConstraints gbc_label_5 = new GridBagConstraints();
	        gbc_label_5.insets = new Insets(0, 0, 0, 5);
	        gbc_label_5.gridx = 8;
	        gbc_label_5.gridy = 0;
	        panel.add(label_5, gbc_label_5);
	        
	        JLabel label_2 = new JLabel("总计：");
	        GridBagConstraints gbc_label_2 = new GridBagConstraints();
	        gbc_label_2.insets = new Insets(0, 0, 0, 5);
	        gbc_label_2.gridx = 9;
	        gbc_label_2.gridy = 0;
	        panel.add(label_2, gbc_label_2);
	        
	        label_1 = new JLabel("      ");
	        label_1.setForeground(new Color(220, 20, 60));
	        GridBagConstraints gbc_label_1 = new GridBagConstraints();
	        gbc_label_1.gridwidth = 2;
	        gbc_label_1.insets = new Insets(0, 0, 0, 5);
	        gbc_label_1.gridx = 10;
	        gbc_label_1.gridy = 0;
	        panel.add(label_1, gbc_label_1);
	        
	        JLabel label_3 = new JLabel("                    ");
	        GridBagConstraints gbc_label_3 = new GridBagConstraints();
	        gbc_label_3.insets = new Insets(0, 0, 0, 5);
	        gbc_label_3.gridx = 12;
	        gbc_label_3.gridy = 0;
	        panel.add(label_3, gbc_label_3);
	        
	        JButton button_1 = new JButton("结算");
	        GridBagConstraints gbc_button_1 = new GridBagConstraints();
	        button_1.setForeground(Color.WHITE);
	        button_1.setBackground(new Color(144, 238, 144));
	        gbc_button_1.insets = new Insets(0, 0, 0, 5);
	        gbc_button_1.gridx = 13;
	        gbc_button_1.gridy = 0;
	        panel.add(button_1, gbc_button_1);
	        
	        JCheckBox checkBox = new JCheckBox("全选");
	        GridBagConstraints gbc_checkBox = new GridBagConstraints();
	        gbc_checkBox.insets = new Insets(0, 0, 0, 5);
	        gbc_checkBox.gridx = 14;
	        gbc_checkBox.gridy = 0;
	        panel.add(checkBox, gbc_checkBox);
	        checkBox.addActionListener(new ActionListener()
	        {
	            @Override
	            public void actionPerformed(ActionEvent arg0)
	            {
	                // TODO Auto-generated method stub
	                if (checkBox.isSelected()) {
	                    table.selectAll();
	                sum = 0;
                    for (int j = 0; j < table.getRowCount(); j++)
                    {
                        int k = 0;
                        for (k = 0; k < table.getColumnCount(); k++)
                        {
                            if (table.getColumnName(k) == "金额")
                                break;
                        }
                        sum += Float.parseFloat(
                                table.getValueAt(j, k).toString());
                    }
	                
                    label_1.setText(sum + "");
	                }
	                else
	                    table.clearSelection();
	            }
	        });
	        button_1.addActionListener(new ActionListener()
	        {
	            @Override
	            public void actionPerformed(ActionEvent arg0)
	            {
	                Date d = new Date();
	        		String data = new SimpleDateFormat("yyyy-MM-dd").format(d);
	        		int rows[] = table.getSelectedRows();
	            	 for(int i=0;i<rows.length;i++) {
	            		  orderinfo.createOrder(MainFrame.Useraccount, cart.getCartGoodsByName(Goodslist.get(i+rows[0]).getGoodsName(), MainFrame.Useraccount), data);
	            		  cart.deleteCartGoodsByName(Goodslist.get(i+rows[0]).getGoodsName(), MainFrame.Useraccount);
	            	 }	
	              
	                remove();
	                JOptionPane.showMessageDialog(jPanel, "确认成功！");
	            }
	        });
			
	        setVisible(true);
	}
	   
	    
	    
	        
	                
}
