package com.ebp.g4.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.ebp.g4.service.beans.Classification;
import com.ebp.g4.service.beans.MainFrameGoods;
import com.ebp.g4.service.beans.Sort;
import com.ebp.g4.service.beans.UserInfo;
import com.ebp.g4.service.implement.ServiceFactory;
import com.ebp.g4.service.interfaces.MainFrameServiceIntf;
import com.ebp.g4.service.interfaces.UserServiceIntf;
import com.ebp.g4.view.buyer.CartFrame;
import com.ebp.g4.view.buyer.OrderInfoFrame;
import com.ebp.g4.view.seller.GoodsDetailFrame;
import com.ebp.g4.view.seller.GoodsSalesInfoFrame;
import com.ebp.g4.view.seller.MyGoodsFrame;
import com.ebp.g4.view.seller.SalesOrderInfoFrame;
import com.ebp.g4.view.seller.StoreApplyDlg;
import com.ebp.g4.view.seller.StoreInfoModifyDlg;
import com.ebp.g4.view.user.AdminMainFrame;
import com.ebp.g4.view.user.LogInDlg;
import com.ebp.g4.view.user.PwdModifyDlg;
import com.ebp.g4.view.user.RegisterDlg;
import com.ebp.g4.view.user.UserInfoDlg;
import java.awt.FlowLayout;

public class MainFrame extends JFrame
{

    private JPanel contentPane;

    /* private JTable table; */
    private JTable goods_table;

    private JTextField txtDad;

    private MainFrameServiceIntf mainservice = ServiceFactory
            .getMainFrameService();

    private UserServiceIntf userservice = ServiceFactory.getUserService();

    private GoodsDetailFrame frame_goodsdetail; // 商品详情

    private LogInDlg frame_login; // 登陆界面

    private RegisterDlg frame_reg; // 注册界面

    private UserInfoDlg frame_userinfo; // 修改个人信息界面

    private PwdModifyDlg frame_pwd_modify; // 修改密码界面

    private OrderInfoFrame frame_orderinfo; // 个人订单界面

    private CartFrame frame_cart; // 购物车

    private StoreApplyDlg frame_apply; // 申请店铺界面

    private StoreInfoModifyDlg frame_modify; // 修改店铺信息

    private MyGoodsFrame frame_mybaby; // 我的宝贝

    private GoodsSalesInfoFrame frame_salesdlg; // 宝贝销售界面

    private SalesOrderInfoFrame frame_salesinfo; // 销售订单

    private Component jPanel; // 提示框

    public static int isChange = 0;

    public static int isLogin = 0; // 全局变量 未登录0 用户登陆1 管理员登陆2

    public static String Useraccount; // 全局变量 用户账号

    /////
    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MainFrame()
    {
      //  this.setIconImage(new ImageIcon("image/logos/order.png").getImage());
        this.setIconImage(new ImageIcon("image/logos/mainFrame.png").getImage());
        setTitle("4号平台");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300); // 前两个参数 设置 初始弹出位置 横竖
        setSize(800, 600); // 初始框大小

        /* 菜单栏 */
        JMenuBar menuBar = new JMenuBar(); // 菜单栏
        setJMenuBar(menuBar);

        JMenu Main_menu_loginregister = new JMenu("登录/注册");
        menuBar.add(Main_menu_loginregister);
        /*
         * 登陆菜单项事件
         * 
         * 
         * 
         * 
         * 
         */
        JMenuItem menu_login = new JMenuItem("登录");
        menu_login.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                frame_login = new LogInDlg(); // 转跳登陆界面
                // frame_login.setVisible(true); //显示
                update(frame_login);
            }
        });

        /*
         * 注册菜单事件
         * 
         * 
         * 
         * 
         */
        Main_menu_loginregister.add(menu_login);
        JMenuItem menu_register = new JMenuItem("注册");
        menu_register.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                frame_reg = new RegisterDlg(); // 转跳注册界面
                // frame_reg.setVisible(true); //显示
                update(frame_reg);

            }
        });
        Main_menu_loginregister.add(menu_register);

        /*
         * 个人中心菜单
         */
        JMenu Main_menu_selfcenter = new JMenu("个人中心");
        menuBar.add(Main_menu_selfcenter);
        /*
         * 个人信息 菜单事件 883行事件监听
         * 
         * 
         */
        JMenuItem menu_selfmessage = new JMenuItem("个人信息");

        Main_menu_selfcenter.add(menu_selfmessage);
        /*
         * 购物车 菜单事件 9
         * 
         * 
         */
        JMenuItem menu_shoppingbusket = new JMenuItem("购物车");

        Main_menu_selfcenter.add(menu_shoppingbusket);

        /*
         * 查看订单 菜单事件
         * 
         * 
         */
        JMenuItem menu_vieworder = new JMenuItem("查看订单");
        menu_vieworder.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (isLogin == 0)
                {
                    frame_login = new LogInDlg(); // 转跳登陆界面
                    // frame_login.setVisible(true); //显示
                    update(frame_login);
                }
                else
                {

                    frame_orderinfo = new OrderInfoFrame(); // 跳出订单车界面
                    // frame_orderinfo.setVisible(true); //显示
                }
            }
        });
        Main_menu_selfcenter.add(menu_vieworder);
        /**
         * 
         * 
         * 修改密码菜单事件
         * 
         */
        JMenuItem menu_pwdmodify = new JMenuItem("修改密码");
        menu_pwdmodify.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (isLogin == 0)
                {
                    frame_login = new LogInDlg(); // 转跳登陆界面
                    // frame_login.setVisible(true); //显示
                    update(frame_login);
                }
                else
                {

                    frame_pwd_modify = new PwdModifyDlg(); // 跳转界面
                    // frame_pwd_modify.setVisible(true); //显示
                }
            }
        });
        Main_menu_selfcenter.add(menu_pwdmodify);

        JMenu Main_menu_sellercenter = new JMenu("卖家中心");
        menuBar.add(Main_menu_sellercenter);

        /***
         * 
         * 注 销 菜单 按钮
         * 
         */
        if (isLogin == 1)
        {
            JMenu Main_menu_exit = new JMenu("注销");
            menuBar.add(Main_menu_exit);
            Main_menu_exit.setBounds(10, 10, 10, 10);
            Main_menu_exit.addMouseListener(new MouseListener()
            {
                @Override
                public void mouseClicked(MouseEvent e)// 鼠标单击事件
                {
                    int result = JOptionPane.showConfirmDialog(null, "确认注销？",
                            "确认", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION)
                    {
                        MainFrame.isLogin = 0;
                        MainFrame.isChange = 0;
                        MainFrame.Useraccount = null;
                        dispose();
                        if (frame_goodsdetail != null)
                        {
                            frame_goodsdetail.dispose();
                        }
                        if (frame_orderinfo != null)
                        {
                            frame_orderinfo.dispose();
                        }
                        if (frame_mybaby != null)
                        {
                            frame_mybaby.dispose();
                        }
                        if (frame_salesinfo != null)
                        {
                            frame_salesinfo.dispose();
                        }
                        /*
                         * if(frame_goodsdetail!=null) {
                         * frame_goodsdetail.dispose(); }
                         */
                        // frame_login.dispose();
                        // frame_reg.dispose();
                        // frame_userinfo.dispose();
                        // frame_pwd_modify.dispose();
                        // frame_orderinfo.dispose();
                        // frame_cart.dispose();
                        // frame_apply.dispose();
                        // frame_modify.dispose();
                        // frame_mybaby.dispose();
                        // frame_salesdlg.dispose();
                        // frame_salesinfo.dispose();
                        MainFrame a = new MainFrame();
                        a.setVisible(true);
                    }
                }

                @Override
                public void mousePressed(MouseEvent e)
                {
                    // TODO Auto-generated method stub

                }

                @Override
                public void mouseReleased(MouseEvent e)
                {
                    // TODO Auto-generated method stub

                }

                @Override
                public void mouseEntered(MouseEvent e)
                {
                    // TODO Auto-generated method stub

                }

                @Override
                public void mouseExited(MouseEvent e)
                {
                    // TODO Auto-generated method stub

                }
            });
        }
        /*
         * 申请店铺 菜单事件
         * 
         * 
         */
        JMenuItem menu_applyashop = new JMenuItem("申请店铺");
        menu_applyashop.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // 判断登陆
                if (isLogin == 0)
                {
                    frame_login = new LogInDlg(); // 转跳登陆界面
                    // frame_login.setVisible(true);
                    update(frame_login);
                }
                else
                {

                    if (userservice.hasStore(Useraccount))
                    {
                        JOptionPane.showMessageDialog(jPanel, "您以拥有店铺", "提示",
                                JOptionPane.WARNING_MESSAGE); // 显示
                    }
                    else
                    {
                        frame_apply = new StoreApplyDlg(); // 跳出申请店面界面

                    }
                }

            }
        });
        Main_menu_sellercenter.add(menu_applyashop);

        /*
         * 修改店铺信息 菜单事件
         * 
         * 
         */
        JMenuItem menu_changeshopmessage = new JMenuItem("修改店铺信息");
        menu_changeshopmessage.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // 判断登陆
                if (isLogin == 0)
                {
                    frame_login = new LogInDlg(); // 转跳登陆界面
                    // frame_login.setVisible(true);
                    update(frame_login);
                }
                else
                {
                    if (userservice.hasStore(Useraccount) == true)
                    {
                        frame_modify = new StoreInfoModifyDlg(); // 跳出修改店面界面

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(jPanel,
                                "您未拥有店铺，请先申请一个店铺！", "提示",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        Main_menu_sellercenter.add(menu_changeshopmessage);
        /*
         * 我的宝贝 菜单事件
         * 
         * 
         */
        JMenuItem menu_mytreasure = new JMenuItem("我的宝贝");

        Main_menu_sellercenter.add(menu_mytreasure);

        /*
         * 宝贝销售情况 菜单事件
         * 
         * 
         */
        JMenuItem menn_sellingcondition = new JMenuItem("宝贝销售情况");
        menn_sellingcondition.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (isLogin == 0)
                {

                    frame_login = new LogInDlg(); // 转跳登陆界面
                    // frame_login.setVisible(true); //显示
                    update(frame_login);

                }
                else
                {
                    if (userservice.hasStore(Useraccount) == true)
                    {
                        frame_salesdlg = new GoodsSalesInfoFrame(); // 跳出宝贝销售界面

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(jPanel,
                                "您未拥有店铺，请先申请一个店铺！", "提示",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        // frame_salesdlg.
        Main_menu_sellercenter.add(menn_sellingcondition);
        JMenuItem menn_sellorder = new JMenuItem("查看销售订单");
        menn_sellorder.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (isLogin == 0)
                {
                    frame_login = new LogInDlg(); // 转跳登陆界面
                    // frame_login.setVisible(true); //显示
                    update(frame_login);
                }
                else
                {
                    if (userservice.hasStore(Useraccount) == true)
                    {
                        frame_salesinfo = new SalesOrderInfoFrame(); // 查看销售订单
                        // frame_salesinfo.setVisible(true); //显示
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(jPanel,
                                "您未拥有店铺，请先申请一个店铺！", "提示",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        Main_menu_sellercenter.add(menn_sellorder);

        JPanel panel_right = new JPanel(); // 右侧容器

        JPanel panel_table = new JPanel(); // table 容器
        panel_table.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.CYAN,
                Color.CYAN, Color.CYAN, Color.CYAN));
        panel_table.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane(); // 滚动条
        panel_table.add(scrollPane);

        goods_table = new JTable(); // 商品列表

        JPopupMenu popupMenu = new JPopupMenu(); // 添加右键点击事件
        addPopup(goods_table, popupMenu);
        /**
         * 
         * 右击 table 查看详情
         *
         * 
         * 
         * 
         * 
         */
        JMenuItem menuItem = new JMenuItem("查看详情"); // 选中table 行 右击查看详情

        goods_table.setFillsViewportHeight(true);
        goods_table.setBorder(new BevelBorder(BevelBorder.LOWERED,
                new Color(176, 224, 230), new Color(176, 224, 230),
                new Color(176, 224, 230), new Color(176, 224, 230)));
        scrollPane.setViewportView(goods_table);
        /**
         * 
         * 商品列表 信息读取 以及显示 ----
         * 
         * 
         * 
         * 
         * 
         **/

        List<String> Pname = mainservice.getGoodsPropertyNames(); // 通过接口方法获取 首页
                                                                  // 表头 返回List
        List<MainFrameGoods> Goodslist = new ArrayList<MainFrameGoods>();
        Goodslist = mainservice.getAllGoodsToBeShown();

        DefaultTableModel model = new DefaultTableModel()
        { // 新建一个默认数据模型
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false; // 表格不允许被编辑
            }
        };

        goods_table.setModel(model); // 加入model
        Object[] pname2 = Pname.toArray(); //
        model.setDataVector(null, pname2);
        for (int i = 0; i < Goodslist.size(); i++)
        { // 按行 添加数据
            Object[] rowData =
            { // 将每行数据点入
                    Goodslist.get(i).getGoodsName(),
                    Goodslist.get(i).getGoodsType(),
                    Goodslist.get(i).getGoodsPrice(),
                    Goodslist.get(i).getGoodsSalesAmount(),
                    Goodslist.get(i).getGoodsStock(),
                    Goodslist.get(i).getGoodStoreName()

            };

            model.addRow(rowData);
        }

        for (int i = 0; i < Pname.size(); i++)
        {
            goods_table.getColumnModel().getColumn(i).setResizable(false); // 设置不可修改列宽
        }

        /**
         * 
         * 右击 table 查看详情 事件
         *
         * 
         * 
         * 
         * 
         */
        menuItem.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                if (goods_table.getSelectedRow() != -1 && goods_table
                        .getValueAt(goods_table.getSelectedRow(), 0) != null) // 判断是否选中一行有内容的订单信息
                {
                    // update=new OrderInfo();

                    int index1 = goods_table.getSelectedRow();
                    int i = 0;
                    int j = 0;
                    for (i = 0; i < goods_table.getColumnCount(); i++)
                    {
                        if (goods_table.getColumnName(i).equals("商品名称"))
                            break;
                    }
                    for (j = 0; j < goods_table.getColumnCount(); j++)
                    {
                        if (goods_table.getColumnName(j).equals("店铺名称"))
                            break;
                    }

                    String goods_name = goods_table.getValueAt(index1, i)
                            .toString();
                    String shop_name = goods_table.getValueAt(index1, j)
                            .toString();
                    frame_goodsdetail = new GoodsDetailFrame(goods_name,
                            shop_name); // 跳出商品详情界面
                    frame_goodsdetail.setVisible(true); // 显示
                    /****
                     * 
                     * 监听 商品详情 页面 关闭 刷新 首页List
                     * 
                     */
                    frame_goodsdetail.addWindowListener(new WindowAdapter()
                    {
                        @Override
                        public void windowClosed(WindowEvent e)
                        {
                            if (isLogin == 1)
                            {
                                if (isChange == 1)
                                {
                                    update_table(model);
                                    MainFrame.isChange = 0;
                                }
                                else
                                {

                                }
                            }
                        }
                    });

                }
                else
                { // 提示

                    JOptionPane.showMessageDialog(jPanel, "请选择一行有内容的商品", "提示",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        popupMenu.add(menuItem);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel panel_f_select = new JPanel(); // 选择功能容器

        JPanel panel_show_info = new JPanel(); // 个人面板容器

        JPanel panel_ad_picture = new JPanel(); // 广告容器

        /* 初始广告图片显示 */
        JLabel Label_ad_picture = new JLabel(""); // label显示 图片

        panel_ad_picture.add(Label_ad_picture); // 容器添加label
        ImageIcon image_ad_picture = new ImageIcon("image/adphoto/ad1.jpg");
        image_ad_picture.setImage(image_ad_picture.getImage()
                .getScaledInstance(360, 80, Image.SCALE_DEFAULT)); // 设置图片长宽
        Label_ad_picture.setIcon(image_ad_picture);

        // pack();
        GroupLayout gl_panel_right = new GroupLayout(panel_right);
        gl_panel_right.setHorizontalGroup(gl_panel_right
                .createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_right
                        .createSequentialGroup()
                        .addGroup(gl_panel_right
                                .createParallelGroup(Alignment.TRAILING)
                                .addComponent(panel_table, Alignment.LEADING,
                                        GroupLayout.DEFAULT_SIZE, 717,
                                        Short.MAX_VALUE)
                                .addGroup(gl_panel_right.createSequentialGroup()
                                        .addGroup(gl_panel_right
                                                .createParallelGroup(
                                                        Alignment.LEADING)
                                                .addGroup(gl_panel_right
                                                        .createSequentialGroup()
                                                        .addGap(10)
                                                        .addComponent(
                                                                panel_ad_picture,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                353,
                                                                Short.MAX_VALUE))
                                                .addComponent(panel_f_select,
                                                        Alignment.TRAILING,
                                                        GroupLayout.DEFAULT_SIZE,
                                                        363, Short.MAX_VALUE))
                                        .addPreferredGap(
                                                ComponentPlacement.UNRELATED)
                                        .addComponent(panel_show_info,
                                                GroupLayout.DEFAULT_SIZE, 344,
                                                Short.MAX_VALUE)))
                        .addGap(2)));
        gl_panel_right.setVerticalGroup(gl_panel_right
                .createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_right.createSequentialGroup()
                        .addGroup(gl_panel_right
                                .createParallelGroup(Alignment.LEADING, false)
                                .addGroup(gl_panel_right.createSequentialGroup()
                                        .addComponent(panel_f_select,
                                                GroupLayout.PREFERRED_SIZE, 61,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(
                                                ComponentPlacement.RELATED)
                                        .addComponent(panel_ad_picture,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE))
                                .addComponent(panel_show_info,
                                        GroupLayout.PREFERRED_SIZE, 152,
                                        GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panel_table, GroupLayout.DEFAULT_SIZE,
                                356, Short.MAX_VALUE)
                        .addContainerGap()));

        JPanel panel_user_picture = new JPanel();

        JLabel label_picture_user = new JLabel("");
        /**
         * 头像 显示
         * 
         * 
         */
        if (isLogin == 0)
        {
            ImageIcon image_picture_user = new ImageIcon(
                    "image/userphoto/moren.jpg"); // 图片路径查找
            image_picture_user.setImage(image_picture_user.getImage()
                    .getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            label_picture_user.setIcon(image_picture_user);
        }
        else
        {
            UserInfo user = userservice
                    .getUserInfoByAccount(MainFrame.Useraccount);
            if (null == user.getPortrait() || user.getPortrait().equals(""))
            {
                ImageIcon image_picture_user = new ImageIcon(
                        "image/userphoto/moren.jpg"); // 图片路径查找
                image_picture_user.setImage(image_picture_user.getImage()
                        .getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                label_picture_user.setIcon(image_picture_user);

            }
            else
            {
                String path = "image/userphoto/" + user.getPortrait();
                ImageIcon image_picture_user = new ImageIcon(path); // 图片路径查找
                image_picture_user.setImage(image_picture_user.getImage()
                        .getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                label_picture_user.setIcon(image_picture_user);
            }
        }

        /**
         * 登陆按钮 及其实现功能 实现. 转跳登陆页功能 未实现 登陆成功 登陆按钮隐藏---------- 关闭登陆 窗口 不关闭主窗---以实现
         * 优化
         */
        JButton button_login = new JButton("登录"); // 登录按钮
        button_login.setForeground(Color.WHITE);
        button_login.setBackground(new Color(144, 238, 144));

        button_login.addActionListener(new ActionListener()
        { // 监听器
            @Override
            public void actionPerformed(ActionEvent e)
            {

                LogInDlg frame1 = new LogInDlg(); // 转跳登陆界面
                // frame1.setVisible(true); //显示
                update(frame1); // 刷新 主页

            }
        });

        /**
         * 注册页面的显示
         * 
         * 
         * 注册页面退出当前页面
         * 
         * 注册功能
         */
        JButton button_register = new JButton("注册"); // 注册按钮
        button_register.setForeground(Color.WHITE);
        button_register.setBackground(new Color(144, 238, 144));
        button_register.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                RegisterDlg frame2 = new RegisterDlg(); // 转跳注册界面
                // frame2.setVisible(true); //显示
                update(frame2); // 刷新 主页

            }
        });

        GroupLayout gl_panel_show_info = new GroupLayout(panel_show_info);
        gl_panel_show_info.setHorizontalGroup(
            gl_panel_show_info.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_show_info.createSequentialGroup()
                    .addGap(36)
                    .addComponent(button_login, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .addGap(51)
                    .addComponent(button_register, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .addGap(31))
                .addGroup(gl_panel_show_info.createSequentialGroup()
                    .addComponent(panel_user_picture, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                    .addGap(16))
        );
        gl_panel_show_info.setVerticalGroup(
            gl_panel_show_info.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_show_info.createSequentialGroup()
                    .addComponent(panel_user_picture, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_panel_show_info.createParallelGroup(Alignment.BASELINE)
                        .addComponent(button_login)
                        .addComponent(button_register))
                    .addContainerGap())
        );
        panel_user_picture.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel_user_picture.add(label_picture_user);
        panel_show_info.setLayout(gl_panel_show_info);

        /**
         * 搜索功能
         * 
         * 
         * 搜索下拉列表内容 选中文本内容 点击搜索按钮 更改列表信息
         * 
         * 
         */
        // 下拉框的提取显示
        JComboBox comboBox_select = new JComboBox(); // 下拉列表选择
        Sort sort = mainservice.getSortingNames();
        Vector sort_text = new Vector(sort.getNames()); // 下拉表的信息传入
        comboBox_select.setModel(new DefaultComboBoxModel(sort_text));

        // 输入文本框的显示和监听
        txtDad = new JTextField();
        txtDad.setColumns(10);
        txtDad.addKeyListener(new KeyAdapter() // 限制 输入长度 为10
        {
            @Override
            public void keyTyped(KeyEvent e)

            {
                String text = txtDad.getText();
                if (text.length() >= 10)
                {
                    e.consume();
                }
            }
        });

        // 搜索按钮的事件
        JButton bt_select = new JButton("搜索"); // 搜索按钮
        bt_select.setForeground(Color.WHITE);
        bt_select.setBackground(new Color(144, 238, 144));
        bt_select.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                String sort = comboBox_select.getSelectedItem().toString();
                String txt_select = txtDad.getText();
                if (null == txt_select || txt_select.equals(""))
                {
                    JOptionPane.showMessageDialog(jPanel, "请输入搜索内容且长度不超过10",
                            "提示", JOptionPane.WARNING_MESSAGE);

                }
                else
                {

                    // System.out.println(sort+" "+txt_select);
                    List<MainFrameGoods> new_goodstable = mainservice
                            .getGoodsBySorting(sort, txt_select);
                    model.setRowCount(0); // 讲model 行数设为0 直接清空
                    for (int i = 0; i < new_goodstable.size(); i++)
                    { // 按行 添加数据
                        Object[] rowData_sort =
                        { // 将每行数据点入
                                new_goodstable.get(i).getGoodsName(),
                                new_goodstable.get(i).getGoodsType(),
                                new_goodstable.get(i).getGoodsPrice(),
                                new_goodstable.get(i).getGoodsSalesAmount(),
                                new_goodstable.get(i).getGoodsStock(),
                                new_goodstable.get(i).getGoodStoreName()

                        };
                        model.addRow(rowData_sort);
                    }
                }
            }
        });

        GroupLayout gl_panel_f_select = new GroupLayout(panel_f_select);
        gl_panel_f_select.setHorizontalGroup(gl_panel_f_select
                .createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_f_select
                        .createSequentialGroup().addGap(25)
                        .addComponent(comboBox_select,
                                GroupLayout.PREFERRED_SIZE, 57,
                                GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(txtDad, GroupLayout.DEFAULT_SIZE, 172,
                                Short.MAX_VALUE)
                        .addGap(18)
                        .addComponent(bt_select, GroupLayout.PREFERRED_SIZE, 71,
                                GroupLayout.PREFERRED_SIZE)
                        .addContainerGap()));
        gl_panel_f_select.setVerticalGroup(gl_panel_f_select
                .createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_f_select.createSequentialGroup().addGap(18)
                        .addGroup(gl_panel_f_select
                                .createParallelGroup(Alignment.BASELINE)
                                .addComponent(bt_select)
                                .addComponent(txtDad,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGroup(gl_panel_f_select
                                        .createSequentialGroup().addGap(2)
                                        .addComponent(comboBox_select)))
                        .addGap(20)));
        panel_f_select.setLayout(gl_panel_f_select);
        panel_right.setLayout(gl_panel_right);

        JPanel panel_classify = new JPanel();
       
        JLabel label = new JLabel("分类列表");
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout
                                        .createParallelGroup(Alignment.LEADING)
                                        .addComponent(panel_classify,
                                                GroupLayout.PREFERRED_SIZE, 59,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(panel_right,
                                        GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)));
        groupLayout.setVerticalGroup(groupLayout
                .createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup().addGap(16)
                        .addComponent(panel_right, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(Alignment.TRAILING,
                        groupLayout.createSequentialGroup().addGap(4)
                                .addComponent(label)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(panel_classify,
                                        GroupLayout.DEFAULT_SIZE, 505,
                                        Short.MAX_VALUE)
                                .addGap(10)));
        panel_classify.setLayout(new BorderLayout(0, 0));

        /**
         * 个人信息修改 显示
         * 
         */
        menu_selfmessage.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // 判断是否登陆
                if (isLogin == 0)
                {
                    frame_login = new LogInDlg(); // 转跳登陆界面
                    // frame_login.setVisible(true); //显示
                    update(frame_login);
                }
                else
                {

                    frame_userinfo = new UserInfoDlg(); // 转跳个人信息界面
                    // frame_userinfo.setVisible(true);
                    frame_userinfo.addWindowListener(new WindowAdapter()
                    {
                        @Override
                        public void windowClosed(WindowEvent e)
                        {
                            UserInfo user = userservice.getUserInfoByAccount(
                                    MainFrame.Useraccount);
                            if (null == user.getPortrait()
                                    || user.getPortrait().equals(""))
                            {
                                ImageIcon image_picture_user = new ImageIcon(
                                        "image/userphoto/moren.jpg"); // 图片路径查找
                                image_picture_user.setImage(image_picture_user
                                        .getImage().getScaledInstance(100, 100,
                                                Image.SCALE_DEFAULT));
                                label_picture_user.setIcon(image_picture_user);

                            }
                            else
                            {
                                String path = "image/userphoto/"
                                        + user.getPortrait();
                                ImageIcon image_picture_user = new ImageIcon(
                                        path); // 图片路径查找
                                image_picture_user.setImage(image_picture_user
                                        .getImage().getScaledInstance(100, 100,
                                                Image.SCALE_DEFAULT));
                                label_picture_user.setIcon(image_picture_user);
                            }
                        }
                    });

                }
            }
        });
        /**
         * 分类 list
         * 
         * 
         * 
         *
         * 分类列表项 读取 并 完整 显示
         * 
         * 分类 不默认选中状态
         * 
         * 选中分类 刷新 显示列表
         * 
         * 
         * 
         */
        Classification classfy = mainservice.getClassificationList();
        Object[] classfies = classfy.getNames().toArray(); // 分类name List
                                                           // 转换为Object
        JList list_classify = new JList();

        list_classify
                .setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
        list_classify.setBackground(SystemColor.inactiveCaption);
        list_classify.setLayoutOrientation(JList.VERTICAL_WRAP);
        list_classify
                .setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list_classify.setPreferredSize(new Dimension(400, 500));
        list_classify.setVisibleRowCount(classfies.length);
        // list_classify.setListData(new String[]{"香蕉", "雪梨", "苹果", "荔枝","香蕉1",
        // "雪梨1", "苹果1", "荔枝1","1香蕉", "1雪梨", "1苹果", "1荔枝"});
        list_classify.setListData(classfies); // classifies
        list_classify.addListSelectionListener(new ListSelectionListener()

        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                // 获取所有被选中的选项索引

                if (list_classify.getValueIsAdjusting())
                {
                    int[] indices = list_classify.getSelectedIndices();
                    // 获取选项数据的 ListModel
                    ListModel<String> listModel = list_classify.getModel();

                    // 输出选中的选项

                    for (int index : indices)
                    {

                        if (index == 0)
                        {

                            update_table(model);

                        }
                        else
                        {
                            List<MainFrameGoods> goodslist = mainservice
                                    .getGoodsByClassification(
                                            listModel.getElementAt(index));
                            model.setRowCount(0); // 讲model 行数设为0 直接清空
                            for (int i = 0; i < goodslist.size(); i++)
                            { // 按行 添加数据
                                Object[] rowData_byclassify =
                                { // 将每行数据点入
                                        goodslist.get(i).getGoodsName(),
                                        goodslist.get(i).getGoodsType(),
                                        goodslist.get(i).getGoodsPrice(),
                                        goodslist.get(i).getGoodsSalesAmount(),
                                        goodslist.get(i).getGoodsStock(),
                                        goodslist.get(i).getGoodStoreName()

                                };

                                model.addRow(rowData_byclassify);

                            }
                        }
                        list_classify.clearSelection();

                    }

                }
            }
        });
        list_classify.setSelectedIndex(2);
        panel_classify.add(list_classify);
        getContentPane().setLayout(groupLayout);

        JMenuItem menu_viewsellorder = new JMenuItem("查看销售订单");

        /**
         * 
         * 购物车 菜单 监听 以及 刷新list
         * 
         * 
         */
        menu_shoppingbusket.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (isLogin == 0)
                {
                    frame_login = new LogInDlg(); // 转跳登陆界面
                    // frame_login.setVisible(true); //显示
                    update(frame_login);
                }
                else
                {

                    frame_cart = new CartFrame(); // 跳出购物车界面
                    // frame_cart.setVisible(true);
                    frame_cart.addWindowListener(new WindowAdapter()
                    {
                        @Override
                        public void windowClosed(WindowEvent e)
                        {

                            update_table(model);

                        }
                    });
                }
            }
        });
        /**
         * 我的宝贝 菜单 点击 以及 刷新主界面list
         * 
         * 
         * 
         * 
         * 
         */
        menu_mytreasure.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (isLogin == 0)
                {
                    frame_login = new LogInDlg(); // 转跳登陆界面
                    // frame_login.setVisible(true);
                    update(frame_login);
                }
                else
                {
                    if (userservice.hasStore(Useraccount) == true)
                    {
                        frame_mybaby = new MyGoodsFrame(); // 跳出我的宝贝 界面
                        // frame_mybaby.setVisible(true); //显示
                        // System.out.println(userservice.hasStore(Useraccount));
                        frame_mybaby.addWindowListener(new WindowAdapter()
                        {
                            @Override
                            public void windowClosing(WindowEvent e)
                            {
                                System.out.println("new");
                                update_table(model);

                            }
                        });
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(jPanel,
                                "您未拥有店铺，请先申请一个店铺！", "提示",
                                JOptionPane.WARNING_MESSAGE);
                    }

                }
            }
        });
        /**
         * 登陆 后 和 登陆 前 首页 登陆/注册按钮的显示
         * 
         * 
         * 按钮隐藏出现问题
         * 
         * 账号显示
         * 
         */
        if (isLogin == 1)
        {
            
           // panel_show_info.remove(button_login);
           // panel_show_info.remove(button_register);
            button_login.setVisible(false);
            button_register.setVisible(false);
            menu_login.setEnabled(false);
            menu_register.setEnabled(false);
            JLabel label_account= new JLabel("昵称： ");
             JLabel label_text_account= new JLabel("  ");
             if(userservice.getUserInfoByAccount(MainFrame.Useraccount).getNickName().equals("")||null==userservice.getUserInfoByAccount(MainFrame.Useraccount).getNickName())
             {
                 label_text_account.setText("未设置");
             }
             else
             {
                 label_text_account.setText(userservice.getUserInfoByAccount(MainFrame.Useraccount).getNickName());

             }
             JLabel label_text_nicheng= new JLabel("          ");
             panel_user_picture.add(label_text_nicheng);
             panel_user_picture.add(label_account);
             panel_user_picture.add(label_text_account);

             
        }
        TableStyle.Tablestyle(goods_table);                        // table 样式设计

    }

    // 刷新主页的商品列表
    public void update_table(DefaultTableModel model)
    {

        List<MainFrameGoods> new_goodstable = mainservice
                .getAllGoodsToBeShown();
        model.setRowCount(0); // 讲model 行数设为0 直接清空
        for (int i = 0; i < new_goodstable.size(); i++)
        { // 按行 添加数据
            Object[] rowData_sort =
            { // 将每行数据点入
                    new_goodstable.get(i).getGoodsName(),
                    new_goodstable.get(i).getGoodsType(),
                    new_goodstable.get(i).getGoodsPrice(),
                    new_goodstable.get(i).getGoodsSalesAmount(),
                    new_goodstable.get(i).getGoodsStock(),
                    new_goodstable.get(i).getGoodStoreName()

            };
            model.addRow(rowData_sort);
        }
    }

    // 监听窗口关闭 重启主界面
    public void update(JDialog frame1)
    {
        frame1.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosed(WindowEvent e)
            {
                if (isLogin == 0)
                {
                    // System.out.println("关闭后"+isLogin);

                }
                else
                {
                    if (isLogin == 1)
                    {

                        dispose();
                        MainFrame a = new MainFrame();
                        a.setVisible(true);

                    }
                    else
                    {

                        dispose();
                        AdminMainFrame adminframe = new AdminMainFrame();

                    }
                }
            }

        });
    }

    // 添加鼠标右键事件
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
