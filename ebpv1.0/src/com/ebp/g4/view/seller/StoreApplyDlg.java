package com.ebp.g4.view.seller;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.ebp.g4.service.beans.Store;
import com.ebp.g4.service.beans.logCompanys;
import com.ebp.g4.service.beans.shopType;
import com.ebp.g4.service.implement.ServiceFactory;
import com.ebp.g4.service.interfaces.UserServiceIntf;
import com.ebp.g4.view.MainFrame;
import java.awt.Color;

public class StoreApplyDlg extends JDialog
{
    private String arr[] =
    { "用户账号", "昵称", "店铺名称", "手机号", "店铺类型", "真实姓名", "身份证号", "银行账号", "物流公司",
            "店铺简介" };

    private JTextField textField[] = new JTextField[20];

    private JButton submit;

    private String shoptype = null;

    private JLabel lblNewLabel[] = new JLabel[20];

    private UserServiceIntf apply = ServiceFactory.getUserService();

    private GridBagConstraints gbc_lblNewLabel[] = new GridBagConstraints[20];

    private JPanel contentPane;

    private GridBagConstraints gbc_textField[] = new GridBagConstraints[20];

    private JTextArea textArea;

    private JComboBox comboBox;

    private JScrollPane js;

    private Store storeApply = new Store();

    private JList list;

    public StoreApplyDlg() throws HeadlessException
    {
        this.setIconImage(new ImageIcon("image/logos/add.png").getImage());
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
        gbl_panel.columnWeights = new double[]
        { 0.0, 1.0 };
        JPanel panel = new JPanel(gbl_panel);
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 1;
        gbc_panel.gridy = 1;
        getContentPane().add(panel, gbc_panel);

        for (int i = 0; i < arr.length; i++)
        {
            lblNewLabel[i] = new JLabel(arr[i]);
            gbc_lblNewLabel[i] = new GridBagConstraints();
            gbc_lblNewLabel[i].insets = new Insets(0, 100, 10, 0);
            gbc_lblNewLabel[i].anchor = GridBagConstraints.EAST;
            gbc_lblNewLabel[i].gridx = 0;
            gbc_lblNewLabel[i].gridy = i;
            panel.add(lblNewLabel[i], gbc_lblNewLabel[i]);

            if (arr[i] == "物流公司")
            {
                JPanel panel_comp = new JPanel();
                JComboBox comboBox = new JComboBox();
                comboBox.addItem(arr[i]);
                List<logCompanys> vals = apply.getAllLogCompanys();

                for (int j = 0; j < vals.size(); j++)
                {
                    comboBox.addItem(vals.get(j).getCompanyName());
                }
                list = new JList();
                DefaultListModel model = new DefaultListModel();
                list.setModel(model);
                js = new JScrollPane(); // 滚动条panel
                js.setPreferredSize(new java.awt.Dimension(50, 57));
                js.setViewportView(list);
                panel_comp.add(comboBox);

                comboBox.addActionListener(new ActionListener()
                {

                    @Override
                    public void actionPerformed(ActionEvent arg0)
                    {
                        // TODO Auto-generated method stub
                        if (comboBox.getSelectedItem() != "物流公司")
                        {
                            int temp = 0;
                            for (int j = 0; j < model.getSize(); j++)
                            {
                                if (model.getElementAt(j) == comboBox
                                        .getSelectedItem())
                                {
                                    temp = 1;
                                    break;
                                }
                            }
                            if (temp == 0)
                                model.addElement(comboBox.getSelectedItem());
                        }
                    }

                });

                panel_comp.add(js);
                JPopupMenu popupMenu = new JPopupMenu();
                addPopup(list, popupMenu);

                JMenuItem menuItem = new JMenuItem("删除");
                popupMenu.add(menuItem);
                menuItem.addActionListener(new ActionListener()
                {

                    @Override
                    public void actionPerformed(ActionEvent arg0)
                    {
                        // TODO Auto-generated method stub
                        if (list.getSelectedIndex() < model.getSize())
                            model.removeElementAt(list.getSelectedIndex());
                    }

                });
                GridBagConstraints gbc_comboBox = new GridBagConstraints();
                gbc_comboBox.insets = new Insets(0, 0, 10, 0);
                gbc_comboBox.gridx = 1;
                gbc_comboBox.gridy = i;
                panel.add(panel_comp, gbc_comboBox);
            }
            else if (arr[i] == "店铺类型")
            {
                List<shopType> lis = apply.getAllShopTypes();
                JComboBox type = new JComboBox();
                for (int j = 0; j < lis.size(); j++)
                {
                    type.addItem(lis.get(j).getShopTypeName());
                }

                GridBagConstraints gbc_comboBox = new GridBagConstraints();
                gbc_comboBox.insets = new Insets(0, 0, 10, 0);
                gbc_comboBox.gridx = 1;
                gbc_comboBox.gridy = i;
                panel.add(type, gbc_comboBox);
                shoptype = type.getSelectedItem().toString();

            }
            else if (arr[i] == "店铺简介")
            {
                textArea = new JTextArea();
                GridBagConstraints gbc_textArea = new GridBagConstraints();
                gbc_textArea.insets = new Insets(0, 0, 10, 0);
                gbc_textArea.gridx = 1;
                gbc_textArea.gridy = i;
                textArea.setColumns(15);
                textArea.setRows(3);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                JScrollPane js = new JScrollPane(textArea);
                js.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                panel.add(js, gbc_textArea);
            }
            else
            {
                textField[i] = new JTextField();
                gbc_textField[i] = new GridBagConstraints();
                gbc_textField[i].insets = new Insets(0, 0, 10, 0);
                gbc_textField[i].gridx = 1;
                gbc_textField[i].gridy = i;
                panel.add(textField[i], gbc_textField[i]);
                textField[i].setColumns(15);
            }
        }
        // MainFrame.Useraccount = "u1";// 用户账号
        textField[0].setText(MainFrame.Useraccount);// 设置账号
        textField[0].setEditable(false);

        String name = apply.getBuyerByAccount(MainFrame.Useraccount)
                .getNickName();
        textField[1].setText(name);// 设置昵称
        textField[1].setEditable(false);

        String phone = apply.getBuyerByAccount(MainFrame.Useraccount)
                .getPhone();
        textField[3].setText(phone);// 设置手机号
        textField[3].setEditable(false);

        JPanel panel1 = new JPanel();
        submit = new JButton("提交");
        submit.setForeground(Color.WHITE);
        submit.setBackground(new Color(144, 238, 144));
        JLabel label = new JLabel("      ");
        JButton btnNewButton = new JButton("重置");
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(new Color(144, 238, 144));
        panel1.add(submit, BorderLayout.WEST);
        panel1.add(label, BorderLayout.CENTER);
        panel1.add(btnNewButton, BorderLayout.EAST);
        GridBagConstraints gbc_button = new GridBagConstraints();
        gbc_button.insets = new Insets(0, 0, 10, 100);
        gbc_button.gridx = 1;
        gbc_button.gridy = arr.length;
        panel.add(panel1, gbc_button);
        btnNewButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                textField[2].setText(null);
                textField[5].setText(null);
                textField[6].setText(null);
                textField[7].setText(null);
                textArea.setText(null);
            }
        });

        submit.addActionListener(new ActionListener()
        {
            @Override

            public void actionPerformed(ActionEvent e)
            {
                storeApply.setAccount(textField[0].getText());
                storeApply.setNickName(textField[1].getText());
                storeApply.setShopName(textField[2].getText());
                storeApply.setPhone(textField[3].getText());
                storeApply.setShopType(shoptype);
                storeApply.setRealName(textField[5].getText());
                storeApply.setIDCard(textField[6].getText());
                storeApply.setBankAccount(textField[7].getText());
                storeApply.setLogisticsCompany(list.getModel().toString());
                storeApply.setInfo(textArea.getText());

                if (!storeApply.getShopName().isEmpty()
                        && !storeApply.getShopType().isEmpty()
                        && !storeApply.getShopType().isEmpty()
                        && !storeApply.getRealName().isEmpty()
                        && !storeApply.getIDCard().isEmpty()
                        && !storeApply.getBankAccount().isEmpty()
                        && !storeApply.getLogisticsCompany().isEmpty()
                        && !storeApply.getInfo().isEmpty()
                        && textField[6].getText()=="^[1-9]\\d{5}(18|19|20)\\d{2}[0-9]\\d{11}[0-9Xx]$"
                    	&& textField[7].getText()== "^\\d{19}$")
                {
                    if (apply.putStoreIntoDataBase(MainFrame.Useraccount,
                            storeApply))
                    {
                    	if(textField[6].getText()=="^[1-9]\\\\d{5}(18|19|20)\\\\d{2}[0-9]\\\\d{11}[0-9Xx]$"
                    	&&textField[7].getText()== "^\\d{19}$" )
                        JOptionPane.showMessageDialog(contentPane, "申请成功！账号为"
                                + apply.getShopId(MainFrame.Useraccount));
                        dispose();
                    }
                    
               

                }
//                else if(textField[6].getText()!="^[1-9]\\\\d{5}(18|19|20)\\\\d{2}[0-9]\\\\d{11}[0-9Xx]$")	
//            		JOptionPane.showMessageDialog(contentPane, "请输入正确的身份证号码！");
//        		
//                else if(textField[7].getText()!="^\\d{19}$") {	
//            		JOptionPane.showMessageDialog(contentPane, "请输入正确的银行卡号！");}

                else if(storeApply.getShopName().isEmpty()
                        && storeApply.getShopType().isEmpty()
                        && storeApply.getShopType().isEmpty()
                        && storeApply.getRealName().isEmpty()
                        && storeApply.getIDCard().isEmpty()
                        && storeApply.getBankAccount().isEmpty()
                        && storeApply.getLogisticsCompany().isEmpty()
                        && storeApply.getInfo().isEmpty())
                {
                    JOptionPane.showMessageDialog(contentPane, "请完善信息", "提示",
                            JOptionPane.WARNING_MESSAGE);
                }
                else 
                {
                    JOptionPane.showMessageDialog(contentPane, "申请失败！");

                }

            }
        });

        this.setModal(true);
        this.setTitle("申请店铺");
        this.setSize(500, 600);
        this.setResizable(false);
        this.setVisible(true);
        // this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

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

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        new StoreApplyDlg();
    }

}
