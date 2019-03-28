package com.ebp.g4.view.seller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import com.ebp.g4.service.beans.GoodsSales;
import com.ebp.g4.service.beans.Seller;
import com.ebp.g4.service.implement.ServiceFactory;
import com.ebp.g4.service.interfaces.GoodsServiceIntf;
import com.ebp.g4.service.interfaces.UserServiceIntf;
import com.ebp.g4.view.MainFrame;
import javax.swing.JPopupMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.ListSelectionModel;

public class GoodsSalesInfoFrame extends JDialog
{
    /**
     * 卖家宝贝销售情况
     */
    private static final long serialVersionUID = 1L;

    private JTable table;

    private GoodsServiceIntf goodsService = ServiceFactory.getGoodsService();

    private UserServiceIntf userService = ServiceFactory.getUserService();

    private String accountID = MainFrame.Useraccount;// lulu

    public GoodsSalesInfoFrame()
    {
        // TODO Auto-generated constructor stub
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]
        { 0, 0, 0 };
        gridBagLayout.rowHeights = new int[]
        { 0, 0, 0 };
        gridBagLayout.columnWeights = new double[]
        { 0.0, 1.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[]
        { 0.0, 1.0, Double.MIN_VALUE };
        getContentPane().setLayout(gridBagLayout);

        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.rowWeights = new double[]
        { 1.0, 0.0 };
        gbl_panel.columnWeights = new double[]
        { 0.0, 1.0, 0.0 };
        JPanel panel = new JPanel(gbl_panel);
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.insets = new Insets(50, 50, 10, 50);
        gbc_panel.gridx = 1;
        gbc_panel.gridy = 1;
        getContentPane().add(panel, gbc_panel);

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        List<String> h = goodsService.getGoodsSalesPropertyNames();
        String[] tableHead = new String[h.size()];
        h.toArray(tableHead);
        List<GoodsSales> list = goodsService.getAllGoodsSalesInfo(accountID);
        GoodsSales[] tableBody = new GoodsSales[list.size()];
        list.toArray(tableBody);
        Object[][] infomation = new Object[tableBody.length][h.size()];
        for (int i = 0; i < tableBody.length; i++)
        {
            Object[] info = new Object[h.size()];
            info[0] = tableBody[i].getGoodsName();
            info[1] = tableBody[i].getGoodsType();
            info[2] = tableBody[i].getGoodsPrice() + "";
            info[3] = tableBody[i].getGoodsShipping() + "";
            info[4] = tableBody[i].getOrderNumber();
            info[5] = tableBody[i].getOrderDate() + "";
            info[6] = tableBody[i].getTotalRevenue() + "";
            infomation[i] = info;
        }
        table.setModel(new DefaultTableModel(infomation, tableHead)
        {
            /**
             * 定义宝贝销售情况的表格的属性，每行都不可编辑
             */
            private static final long serialVersionUID = 1L;

            boolean[] columnEditables = new boolean[]
            { false, false, false, false, false, false, false };

            public boolean isCellEditable(int row, int column)
            {
                return columnEditables[column];
            }
        });
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(4).setResizable(false);
        table.getColumnModel().getColumn(6).setResizable(false);

        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
        head.setFont(new Font("宋体", Font.PLAIN, 18));// 设置表格字体
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(0).setMinWidth(30);
        table.setRowHeight(30);
        table.setFont(new Font("楷体", Font.PLAIN, 18));
        DefaultTableCellRenderer ter = new DefaultTableCellRenderer()// 设置表格间隔色
        {
            /**
             * 定义表格的样式，奇数行和偶数行颜色相间
             */
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object

            value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                // table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
                if (row % 2 == 0)
                    setBackground(new Color(244, 247, 252));
                else if (row % 2 == 1)
                    setBackground(Color.white);
                return super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);
            }
        };
        ter.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, ter);

        JScrollPane js = new JScrollPane(table);

        JPopupMenu popupMenu = new JPopupMenu();
        addPopup(table, popupMenu);

        JMenuItem menuItem = new JMenuItem("宝贝详情");
        popupMenu.add(menuItem);
        
        /**
         * 右键菜单，宝贝详情
         */
        menuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                // TODO Auto-generated method stub
                int index = table.getSelectedRow();
                int i = 0;
                for (i = 0; i < table.getColumnCount(); i++)
                {
                    if (table.getColumnName(i) == "名称")
                        break;
                }
                Seller seller = userService.getSellerByAccount(accountID);
                GoodsDetailFrame detail = new GoodsDetailFrame(
                        table.getValueAt(index, i).toString(),
                        seller.getStoreName());
                detail.setVisible(true);
            }
        });

        GridBagConstraints gbc_table = new GridBagConstraints();
        gbc_table.insets = new Insets(0, 0, 5, 0);
        gbc_table.gridwidth = 5;
        gbc_table.fill = GridBagConstraints.BOTH;
        gbc_table.gridx = 1;
        gbc_table.gridy = 0;
        panel.add(js, gbc_table);

        JLabel label = new JLabel("盈利额：￥");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.insets = new Insets(0, 0, 0, 5);
        gbc_label.gridx = 2;
        gbc_label.gridy = 1;
        panel.add(label, gbc_label);

        float money = 0;
        for (int j = 0; j < table.getRowCount(); j++)
        {
            int p = 0, m = 0, n = 0;
            for (int k = 0; k < table.getColumnCount(); k++)
            {
                if (table.getColumnName(k) == "价格")
                    p = k;
                if (table.getColumnName(k) == "运费")
                    m = k;
                if (table.getColumnName(k) == "总销售量")
                    n = k;
            }
            money += (Float.parseFloat(table.getValueAt(j, p).toString())
                    - Float.parseFloat(table.getValueAt(j, m).toString()))
                    * Float.parseFloat(table.getValueAt(j, n).toString());
        }
        JLabel lblNewLabel = new JLabel(money + "");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel.gridx = 4;
        gbc_lblNewLabel.gridy = 1;
        panel.add(lblNewLabel, gbc_lblNewLabel);

        this.setIconImage(
                new ImageIcon("image/logos/salesinfo.png").getImage());
        setBounds(100, 100, 900, 600);
        this.setTitle("宝贝销售情况");
        this.setVisible(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        new GoodsSalesInfoFrame();
    }

    private static void addPopup(Component component, final JPopupMenu popup)
    {
        component.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                if (e.isPopupTrigger())
                {
                    showMenu(e);
                }
            }

            public void mouseReleased(MouseEvent e)
            {
                if (e.isPopupTrigger())
                {
                    showMenu(e);
                }
            }

            private void showMenu(MouseEvent e)
            {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }
}
