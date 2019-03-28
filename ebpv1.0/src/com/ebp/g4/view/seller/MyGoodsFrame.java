package com.ebp.g4.view.seller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import com.ebp.g4.service.beans.MyGoods;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.HashSet;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Color;

public class MyGoodsFrame extends JFrame
{
    /**
     * 卖家我的宝贝列表
     */
    private static final long serialVersionUID = 1L;

    private JTable table;

    private DefaultTableModel model;

    private GoodsServiceIntf goodsService = ServiceFactory.getGoodsService();

    private UserServiceIntf userService = ServiceFactory.getUserService();

    private List<String> head = goodsService.getMyGoodsPropertyNames();

    private Object[][] infomation;

    private String name;

    private String accountID = MainFrame.Useraccount;// lulu

    private List<MyGoods> list = goodsService.getAllMyGoods(accountID);

    public MyGoodsFrame()
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
        String[] tableHead = new String[head.size()];
        head.toArray(tableHead);

        MyGoods tableBody[] = new MyGoods[list.size()];
        list.toArray(tableBody);
        infomation = new Object[tableBody.length][head.size()];
        for (int i = 0; i < tableBody.length; i++)
        {
            Object[] info = new Object[head.size()];
            info[0] = tableBody[i].getGoodsName();
            info[1] = tableBody[i].getGoodsType();
            info[2] = tableBody[i].getGoodsPrice() + "";
            info[3] = tableBody[i].getGoodsCarriage() + "";
            info[4] = tableBody[i].getGoodsSales();
            info[5] = tableBody[i].getGoodsStock() + "";
            info[6] = tableBody[i].getGoodsShelfTime() + "";
            infomation[i] = info;
        }
        model = new DefaultTableModel(infomation, tableHead)
        {
            /**
             * 定义我的宝贝的表格的属性，每行都不可编辑
             */
            private static final long serialVersionUID = 1L;

            boolean[] columnEditables = new boolean[]
            { false, false, false, false, false, false, false };

            public boolean isCellEditable(int row, int column)
            {
                return columnEditables[column];
            }
        };
        table.setModel(model);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(4).setResizable(false);
        table.getColumnModel().getColumn(5).setResizable(false);

        JTableHeader h = table.getTableHeader(); // 创建表格标题对象
        h.setPreferredSize(new Dimension(h.getWidth(), 35));// 设置表头大小
        h.setFont(new Font("宋体", Font.PLAIN, 18));// 设置表格字体
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

        JMenuItem menuItem_mod = new JMenuItem("修改");
        popupMenu.add(menuItem_mod);
        /**
         * 右键菜单，修改选中宝贝详情，只能单选
         */
        menuItem_mod.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                // TODO Auto-generated method stub
                int rows[] = table.getSelectedRows();
                if (rows.length > 1)
                {
                    JOptionPane.showMessageDialog(null, "只能修改一行信息", "alert",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (rows.length != 0)
                {
                    modify();
                }
            }
        });

        JMenuItem menuItem_down = new JMenuItem("下架");
        popupMenu.add(menuItem_down);

        menuItem_down.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                // TODO Auto-generated method stub
                remove();
            }
        });

        GridBagConstraints gbc_table = new GridBagConstraints();
        gbc_table.insets = new Insets(0, 0, 5, 0);
        gbc_table.gridwidth = 4;
        gbc_table.fill = GridBagConstraints.BOTH;
        gbc_table.gridx = 1;
        gbc_table.gridy = 0;
        panel.add(js, gbc_table);

        JButton up = new JButton("上架新宝贝");
        up.setForeground(Color.WHITE);
        up.setBackground(new Color(144, 238, 144));
        GridBagConstraints gbc_up = new GridBagConstraints();
        gbc_up.insets = new Insets(0, 0, 10, 100);
        gbc_up.gridx = 1;
        gbc_up.gridy = 1;
        panel.add(up, gbc_up);

        up.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                // TODO Auto-generated method stub
                up();
            }
        });

        JButton modify = new JButton("修改");
        modify.setBackground(new Color(144, 238, 144));
        modify.setForeground(Color.WHITE);
        GridBagConstraints gbc_modify = new GridBagConstraints();
        gbc_modify.insets = new Insets(0, 0, 10, 100);
        gbc_modify.gridx = 2;
        gbc_modify.gridy = 1;
        panel.add(modify, gbc_modify);
        modify.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                // TODO Auto-generated method stub
                int rows[] = table.getSelectedRows();
                if (rows.length > 1)
                {
                    JOptionPane.showMessageDialog(null, "只能修改一行信息", "alert",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (rows.length != 0)
                {
                    modify();
                }
            }
        });

        JButton down = new JButton("下架");
        down.setBackground(new Color(144, 238, 144));
        down.setForeground(Color.WHITE);
        GridBagConstraints gbc_down = new GridBagConstraints();
        gbc_down.insets = new Insets(0, 0, 10, 100);
        gbc_down.gridx = 3;
        gbc_down.gridy = 1;
        panel.add(down, gbc_down);
        down.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                // TODO Auto-generated method stub
                remove();
            }
        });

        JCheckBox checkBox = new JCheckBox("全选");
        GridBagConstraints gbc_checkBox = new GridBagConstraints();
        gbc_checkBox.insets = new Insets(0, 20, 0, 5);
        gbc_checkBox.gridx = 4;
        gbc_checkBox.gridy = 1;
        panel.add(checkBox, gbc_checkBox);
        /**
         * 复选框，全选按钮监听
         */
        checkBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                // TODO Auto-generated method stub
                if (checkBox.isSelected())
                    table.selectAll();
                else
                    table.clearSelection();
            }
        });
        this.setIconImage(
                new ImageIcon("image/logos/salesinfo.png").getImage());
        setBounds(100, 100, 900, 600);
        this.setTitle("我的宝贝");
        this.setVisible(true);
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

    /**
     * 上架新宝贝，并反馈在表格上
     */
    private void up()
    {
        NewGoodsDlg newGoods = new NewGoodsDlg(accountID);
        /*
         * newGoods.setVisible(true); newGoods.setModal(true);
         */
        newGoods.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosed(WindowEvent arg0)
            {
                // TODO Auto-generated method stub
                List<MyGoods> newList = goodsService.getAllMyGoods(accountID);
                MyGoods newTableBody = newList.get(newList.size() - 1);

                Object[] info = new Object[head.size()];
                info[0] = newTableBody.getGoodsName();
                int i = 0;
                for (i = 0; i < table.getColumnCount(); i++)
                {
                    if (table.getColumnName(i) == "名称")
                        break;
                }
                if (table.getRowCount()==0||(table.getRowCount()!=0&&!info[0].toString()
                        .equals(table.getValueAt(table.getRowCount() - 1, i))))
                {
                    info[1] = newTableBody.getGoodsType();
                    info[2] = newTableBody.getGoodsPrice() + "";
                    info[3] = newTableBody.getGoodsCarriage() + "";
                    info[4] = newTableBody.getGoodsSales();
                    info[5] = newTableBody.getGoodsStock() + "";
                    info[6] = newTableBody.getGoodsShelfTime() + "";
                    model.insertRow(table.getRowCount(), info);
                }
            }
        });
    }

    /**
     * 下架宝贝，并反馈在表格上
     */
    private void remove()
    {
        int rows[] = table.getSelectedRows();
        if (rows.length != 0)
        {
            int n = JOptionPane.showConfirmDialog(null, "确认下架吗?", "确认对话框",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION)
            {
                for (int j = rows.length - 1; j >= 0; j--)
                {
                    int i = 0;
                    for (i = 0; i < table.getColumnCount(); i++)
                    {
                        if (table.getColumnName(i) == "名称")
                            break;
                    }
                    String name = table.getValueAt(rows[j], i).toString();

                    /* 删除文件 */
                    String path = "image/goodsphoto/";
                    MyGoods myGoods = goodsService.getMyGoodsByGoodsName(name);
                    String pictureName = myGoods.getPicture().toString();

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
                    if (set.contains(pictureName))
                    {
                        File f = new File(path + pictureName);
                        if (f.delete())
                        {
                            JOptionPane.showMessageDialog(null, "删除成功！", "提示",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    goodsService.deleteMyGoodsByGoodsName(name);
                    model.removeRow(rows[j]);
                }
            }
            else if (n == JOptionPane.NO_OPTION)
            {
            }
        }
    }

    /**
     * 修改宝贝详情，并反馈在表格上
     */
    private void modify()
    {
        /*
         * String head = ""; String info = ""; for (int k = 0; k <
         * table.getColumnCount(); k++) { head = head + model.getColumnName(k) +
         * "!"; info = info + table.getValueAt(rows[j], k) + "!"; }
         */
        int index = table.getSelectedRow();
        int i = 0;
        for (i = 0; i < table.getColumnCount(); i++)
        {
            if (table.getColumnName(i) == "名称")
                break;
        }
        name = table.getValueAt(index, i).toString();
        MyGoods myGoods = goodsService.getMyGoodsByGoodsName(name);
        String time = myGoods.getGoodsShelfTime() + "";
        GoodsInfoModifyDlg moDialog = new GoodsInfoModifyDlg(name, time,
                accountID);
        moDialog.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosed(WindowEvent arg0)
            {
                // TODO Auto-generated method stub
                MyGoods myNewGoods = goodsService.getMyGoodsByGoodsName(name);

                if (!myNewGoods.equals(myGoods))
                {
                    Object[] info = new Object[head.size()];
                    info[0] = myNewGoods.getGoodsName();
                    info[1] = myNewGoods.getGoodsType();
                    info[2] = myNewGoods.getGoodsPrice() + "";
                    info[3] = myNewGoods.getGoodsCarriage() + "";
                    info[4] = myNewGoods.getGoodsSales();
                    info[5] = myNewGoods.getGoodsStock() + "";
                    info[6] = myNewGoods.getGoodsShelfTime() + "";
                    model.insertRow(table.getRowCount(), info);
                    model.removeRow(index);
                }
            }
        });
    }

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        new MyGoodsFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
