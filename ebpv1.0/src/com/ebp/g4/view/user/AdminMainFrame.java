package com.ebp.g4.view.user;

import static com.ebp.g4.view.TableStyle.Tablestyle;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ebp.g4.service.beans.Buyer;
import com.ebp.g4.service.beans.Store;
import com.ebp.g4.service.implement.ServiceFactory;
import com.ebp.g4.service.interfaces.UserServiceIntf;
import com.ebp.g4.view.MainFrame;

public class AdminMainFrame extends JFrame
{
    private JFrame jFrame = new JFrame("买家列表");

    private JMenuBar jmenubar;

    private JMenu jmenu;

    private JButton jButton;

    private JMenuItem jmiSoller;// 管理卖家

    private JMenuItem jmiBuyer;// 管理买家

    private JPanel jpBuyer;// 管理买家的面板

    private JPanel jpSeller; // 管理卖家的面板

    private JTable jtableSeller;// 管理卖家的表格

    private JTable jtableBuyer;// 管理买家的表格

    private JScrollPane jscrollPane;

    private DefaultTableModel model;

    private UserServiceIntf userService = ServiceFactory.getUserService();

    public AdminMainFrame()
    {
        jFrame.setIconImage(new ImageIcon("image/logo.jpg").getImage());
        jmenubar = new JMenuBar();
        jmenu = new JMenu("用户管理");
        jmiSoller = new JMenuItem("管理卖家");
        jmiBuyer = new JMenuItem("管理买家");
        jmenubar.add(jmenu);
        jmenu.add(jmiSoller);
        jmenu.add(jmiBuyer);
        JLabel jLabel = new JLabel("           ");
        jmenubar.add(jLabel);
        jButton = new JButton("退出");
        // jButton.setOpaque(false);
        jButton.setBorder(null);
        jButton.setContentAreaFilled(false);
        jmenubar.add(jButton);

        jButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                JOptionPane.showMessageDialog(jFrame, "退出成功！");
                jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jFrame.dispose();
                MainFrame.isLogin = 0;
                MainFrame.Useraccount = null;
                MainFrame adminframe = new MainFrame();
                adminframe.setVisible(true);

            }
        });
        // 初始化 默认的JPanel
        jpBuyer = new JPanel();
        jpBuyer = new JPanel(new GridLayout(1, 1));
        jpBuyer.setBackground(Color.LIGHT_GRAY);
        jtableBuyer = new JTable();
        jtableBuyer.setFillsViewportHeight(true);
        List<String> tablehead1 = userService.getBuyersPropertyNames();
        List<Buyer> tablebody1 = userService.getAllBuyers();
        Object object1[][] = new Object[tablebody1.size()][tablehead1.size()];
        for (int i = 0; i < tablebody1.size(); i++)
        {
            object1[i][0] = tablebody1.get(i).getNickName();
            object1[i][1] = tablebody1.get(i).getAccount();
            object1[i][2] = tablebody1.get(i).getPhone();
        }
        jtableBuyer
                .setModel(new DefaultTableModel(object1, tablehead1.toArray())
                {
                    boolean[] columnEditables = new boolean[]
                    { false, false, false };

                    @Override
                    public boolean isCellEditable(int row, int column)
                    {
                        return columnEditables[column];
                    }
                });
        jscrollPane = new JScrollPane(jtableBuyer);
        jscrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jpBuyer.add(jscrollPane);
        // 对这个菜单按钮进行设置 this 代表的是TestFrame 这个对象
        jmiBuyer.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                jFrame.remove(jpSeller);// 去掉默认JPanel
                jFrame.add(jpBuyer);// 加入新的JPanel
                jFrame.setTitle("买家列表");
                jFrame.validate();// 重构整个界面

            }
        });
        Tablestyle(jtableBuyer);
        jFrame.add(jpBuyer);
        jFrame.setJMenuBar(jmenubar);

        // 初始化 要改变的JPanel
        jpSeller = new JPanel(new GridLayout(1, 1));
        jpSeller.setBackground(Color.LIGHT_GRAY);
        jtableSeller = new JTable();
        jtableSeller.setFillsViewportHeight(true);
        List<String> tablehead = userService.getSellersPropertyNames();
        List<Store> tablebody = userService.getAllSellers();
        Object object[][] = new Object[tablebody.size()][tablehead.size()];
        for (int i = 0; i < tablebody.size(); i++)
        {
            object[i][0] = tablebody.get(i).getNickName();
            object[i][1] = tablebody.get(i).getAccount();
            object[i][2] = tablebody.get(i).getPhone();
            object[i][3] = tablebody.get(i).getShopName();
            object[i][4] = tablebody.get(i).getShopType();
        }
        jtableSeller.setModel(new DefaultTableModel(object, tablehead.toArray())
        {
            boolean[] columnEditables = new boolean[]
            { false, false, false, false, false };

            @Override
            public boolean isCellEditable(int row, int column)
            {
                return columnEditables[column];
            }
        });
        jscrollPane = new JScrollPane(jtableSeller);
        jscrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jpSeller.add(jscrollPane);
        Tablestyle(jtableSeller);
        // 对这个菜单按钮进行设置 this 代表的是TestFrame 这个对象
        jmiSoller.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                jFrame.remove(jpBuyer);// 去掉默认JPanel
                jFrame.add(jpSeller);// 加入新的JPanel
                jFrame.setTitle("卖家列表");
                jFrame.validate();// 重构整个界面

            }
        });
        jFrame.setBounds(100, 100, 900, 700);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args)
    {
        new AdminMainFrame();
    }

}
