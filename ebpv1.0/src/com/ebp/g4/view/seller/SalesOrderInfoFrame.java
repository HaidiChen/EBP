package com.ebp.g4.view.seller;

import static com.ebp.g4.view.TableStyle.Tablestyle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.ebp.g4.service.beans.SalesOrder;
import com.ebp.g4.service.beans.Store;
import com.ebp.g4.service.implement.ServiceFactory;
import com.ebp.g4.service.interfaces.OrderServiceIntf;
import com.ebp.g4.service.interfaces.UserServiceIntf;
import com.ebp.g4.view.MainFrame;

public class SalesOrderInfoFrame extends JFrame
{

    private JPanel contentPane;

    private JTable table;

    private JScrollPane jscrollPane;

    private DefaultTableModel model;

    private Object object[][];

    private List<SalesOrder> tablebody;

    private OrderServiceIntf orderService = ServiceFactory.getOrderService();

    private UserServiceIntf userService = ServiceFactory.getUserService();

    /**
     * Launch the application.
     */

    public static void main(String[] args)
    {
        SalesOrderInfoFrame frame = new SalesOrderInfoFrame();
    }

    /**
     * Create the frame.
     */
    public SalesOrderInfoFrame()
    {
        this.setIconImage(
                new ImageIcon("image/logos/salesinfo.png").getImage());
        // MainFrame.Useraccount = "u2";
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]
        { 202, 113, 113, 0, 0 };
        gbl_panel.rowHeights = new int[]
        { 27, 0, 0 };
        gbl_panel.columnWeights = new double[]
        { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[]
        { 0.0, 0.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);

        JButton btnNewButton = new JButton("确认发货");
        btnNewButton.setBackground(new Color(144, 238, 144));
        btnNewButton.setFont(new Font("宋体", Font.PLAIN, 18));
        btnNewButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                if (table.getSelectedRow() != -1) // 判断是否选中一行订单信息
                {
                    int num[] = table.getSelectedRows();
                    if (num.length > 1)
                    {
                        JOptionPane.showMessageDialog(contentPane, "只能选一行",
                                "提示", JOptionPane.WARNING_MESSAGE);
                    }
                    else
                    {

                        int index = table.getSelectedRow();
                        int i = 0;
                        int j = 0;
                        for (i = 0; i < table.getColumnCount(); i++)
                        {
                            if (table.getColumnName(i).equals("交易状态"))
                                break;
                        }
                        for (j = 0; j < table.getColumnCount(); j++)
                        {
                            if (table.getColumnName(j).equals("订单编号"))
                                break;
                        }
                        String state = table.getValueAt(index, i).toString();
                        String orderNum = table.getValueAt(index, j).toString();
                        if (state.equals("未发货"))
                        {
                            orderService.updateSalesOrderStatus(orderNum,
                                    "已发货");
                            JOptionPane.showMessageDialog(contentPane, "发货成功",
                                    "提示", JOptionPane.WARNING_MESSAGE);
                            table.setValueAt("已发货", index, i);
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
        btnNewButton.setForeground(Color.WHITE);
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
        gbc_btnNewButton.insets = new Insets(20, 0, 50, 5);
        gbc_btnNewButton.gridx = 1;
        gbc_btnNewButton.gridy = 0;
        panel.add(btnNewButton, gbc_btnNewButton);

        JButton btnNewButton_1 = new JButton("详细信息");
        btnNewButton_1.setBackground(new Color(144, 238, 144));
        btnNewButton_1.setForeground(Color.WHITE);
        btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 18));
        btnNewButton_1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (table.getSelectedRow() != -1)
                {
                    int num[] = table.getSelectedRows();
                    if (num.length > 1)
                    {
                        JOptionPane.showMessageDialog(contentPane, "只能选一行",
                                "提示", JOptionPane.WARNING_MESSAGE);
                    }
                    else
                    {
                        int index = table.getSelectedRow();
                        int i = 0;
                        for (i = 0; i < table.getColumnCount(); i++)
                        {
                            if (table.getColumnName(i).equals("订单编号"))
                                break;
                        }
                        OrderDetailsDlg dialog = new OrderDetailsDlg(
                                table.getValueAt(index, i).toString());
                    }
                }
                else
                { // 提示
                    JOptionPane.showMessageDialog(contentPane, "请选择一行订单", "提示",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.fill = GridBagConstraints.VERTICAL;
        gbc_btnNewButton_1.insets = new Insets(20, 100, 50, 50);
        gbc_btnNewButton_1.gridx = 2;
        gbc_btnNewButton_1.gridy = 0;
        panel.add(btnNewButton_1, gbc_btnNewButton_1);

        table = new JTable();
        table.setFillsViewportHeight(true);
        List<String> tablehead = orderService.getSalesOrderPropertyNames();
        Store seller = userService.getStoreByAccount(MainFrame.Useraccount);
        tablebody = orderService
                .getAllSalesOrderInfoByStoreName(seller.getShopName());
        object = new Object[tablebody.size()][tablehead.size()];
        for (int i = 0; i < tablebody.size(); i++)
        {
            object[i][0] = tablebody.get(i).getOrderDate();
            object[i][1] = tablebody.get(i).getOrderNumber();
            object[i][2] = tablebody.get(i).getGoodsName();
            object[i][3] = tablebody.get(i).getGoodsPrice();
            object[i][4] = tablebody.get(i).getGoodsAmount();
            object[i][5] = tablebody.get(i).getGoodsShipping();
            object[i][6] = tablebody.get(i).getGoodsPayFee();
            object[i][7] = tablebody.get(i).getOrderStatus();
        }
        table.setModel(new DefaultTableModel(object, tablehead.toArray())
        {
            boolean[] columnEditables = new boolean[]
            { false, false, false, false, false, false, false, false };

            @Override
            public boolean isCellEditable(int row, int column)
            {
                return columnEditables[column];
            }
        });

        Tablestyle(table);

        jscrollPane = new JScrollPane(table);
        JPopupMenu popupMenu = new JPopupMenu();
        addPopup(table, popupMenu);

        JMenuItem menuItem = new JMenuItem("详细信息");
        popupMenu.add(menuItem);
        menuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (table.getSelectedRow() != -1)
                {
                    int num[] = table.getSelectedRows();
                    if (num.length > 1)
                    {
                        JOptionPane.showMessageDialog(contentPane, "只能选一行",
                                "提示", JOptionPane.WARNING_MESSAGE);
                    }
                    else
                    {
                        int index = table.getSelectedRow();
                        int i = 0;
                        for (i = 0; i < table.getColumnCount(); i++)
                        {
                            if (table.getColumnName(i).equals("订单编号"))
                                break;
                        }
                        OrderDetailsDlg dialog = new OrderDetailsDlg(
                                table.getValueAt(index, i).toString());
                    }
                }
                else
                { // 提示
                    JOptionPane.showMessageDialog(contentPane, "请选择一行订单", "提示",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        jscrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.add(jscrollPane, BorderLayout.CENTER);
        setBounds(100, 100, 900, 600);
        setVisible(true);
        setTitle("查看销售订单");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private static void addPopup(Component component, final JPopupMenu popup)
    {

        component.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                if (e.isPopupTrigger())
                {
                    showMenu(e);
                }
            }

            @Override
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
