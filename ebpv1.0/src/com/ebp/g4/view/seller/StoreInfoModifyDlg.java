package com.ebp.g4.view.seller;

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
import com.ebp.g4.service.implement.ServiceFactory;
import com.ebp.g4.service.interfaces.UserServiceIntf;
import com.ebp.g4.view.MainFrame;
import java.awt.Color;

public class StoreInfoModifyDlg extends JDialog
{
    private String arr[] =
    { "用户账号", "昵称", "手机号", "店铺名称", "店铺类型", "真实姓名", "身份证号", "银行账号", "物流公司",
            "店铺简介" };

    private JTextField textField[] = new JTextField[20];

    private JPanel contentPane;

    private JLabel lblNewLabel[] = new JLabel[20];

    private UserServiceIntf modify = ServiceFactory.getUserService();

    private GridBagConstraints gbc_lblNewLabel[] = new GridBagConstraints[20];

    private JButton submit;

    private GridBagConstraints gbc_textField[] = new GridBagConstraints[20];

    private String shoptype = null;

    private GridBagConstraints gbc_button;

    private JScrollPane js;

    private JComboBox comboBox;

    private JTextField field;

    private JTextArea textArea;

    private Store StoreModify, storeApply;

    private JList list;

    public StoreInfoModifyDlg() throws HeadlessException
    {
        // this.setDefaultLookAndFeelDecorated(true);
        this.setIconImage(new ImageIcon("image/logos/user.png").getImage());
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

        // MainFrame.Useraccount = "u2";
        StoreModify = modify.getStoreByAccount(MainFrame.Useraccount);
        for (int i = 0; i < arr.length; i++)
        {
            lblNewLabel[i] = new JLabel(arr[i]);
            gbc_lblNewLabel[i] = new GridBagConstraints();
            gbc_lblNewLabel[i].insets = new Insets(0, 100, 10, 5);
            gbc_lblNewLabel[i].anchor = GridBagConstraints.EAST;
            gbc_lblNewLabel[i].gridx = 0;
            gbc_lblNewLabel[i].gridy = i;
            panel.add(lblNewLabel[i], gbc_lblNewLabel[i]);
            if (arr[i] == "物流公司")
            {
                JPanel panel_comp = new JPanel();
                comboBox = new JComboBox();
                comboBox.addItem(arr[i]);
                List<logCompanys> vals = modify.getAllLogCompanys();

                for (int j = 0; j < vals.size(); j++)
                {
                    comboBox.addItem(vals.get(j).getCompanyName());
                }
                list = new JList();
                DefaultListModel model = new DefaultListModel();
                model.addElement(StoreModify.getLogisticsCompany());
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
                gbc_comboBox.insets = new Insets(0, 0, 10, 100);
                gbc_comboBox.gridx = 1;
                gbc_comboBox.gridy = i;
                panel.add(panel_comp, gbc_comboBox);
            }
            else if (arr[i] == "店铺类型")
            {
                field = new JTextField(StoreModify.getShopType());
                GridBagConstraints gbc_comboBox = new GridBagConstraints();
                gbc_comboBox.insets = new Insets(0, 0, 10, 100);
                gbc_comboBox.gridx = 1;
                gbc_comboBox.gridy = i;
                field.setEditable(false);
                field.setColumns(15);
                panel.add(field, gbc_comboBox);
            }
            else if (arr[i] == "店铺简介")
            {
                textArea = new JTextArea(StoreModify.getInfo());
                GridBagConstraints gbc_textArea = new GridBagConstraints();
                gbc_textArea.insets = new Insets(0, 0, 10, 100);
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
                gbc_textField[i].insets = new Insets(0, 0, 10, 100);
                gbc_textField[i].gridx = 1;
                gbc_textField[i].gridy = i;
                panel.add(textField[i], gbc_textField[i]);
                textField[i].setColumns(15);
            }
        }
        submit = new JButton("修改");
        submit.setForeground(new Color(255, 255, 255));
        submit.setBackground(new Color(144, 238, 144));
        gbc_button = new GridBagConstraints();
        gbc_button.insets = new Insets(0, 0, 10, 100);
        gbc_button.gridx = 1;
        gbc_button.gridy = arr.length;
        panel.add(submit, gbc_button);

        textField[0].setText(MainFrame.Useraccount);
        textField[0].setEditable(false);
        textField[1].setText(StoreModify.getNickName());
        textField[1].setEditable(false);
        textField[2].setText(StoreModify.getPhone());
        textField[2].setEditable(false);
        textField[3].setText(StoreModify.getShopName());
        textField[5].setText(StoreModify.getRealName());
        textField[5].setEditable(false);
        textField[6].setText(StoreModify.getIDCard());
        textField[6].setEditable(false);
        textField[7].setText(StoreModify.getBankAccount());
        textField[7].setEditable(false);
        submit.addActionListener(new ActionListener()
        {
            @Override

            public void actionPerformed(ActionEvent e)
            {
                storeApply = new Store();
                storeApply.setAccount(MainFrame.Useraccount);
                storeApply.setNickName(textField[1].getText());
                storeApply.setShopName(textField[2].getText());
                storeApply.setPhone(textField[3].getText());
                storeApply.setShopType(shoptype);
                storeApply.setRealName(textField[5].getText());
                storeApply.setIDCard(textField[6].getText());
                storeApply.setBankAccount(textField[7].getText());
                storeApply.setLogisticsCompany(list.getModel().toString());
                storeApply.setInfo(textArea.getText());
                if (modify.modifyStoreByAccount(MainFrame.Useraccount,
                        storeApply))
                {
                    JOptionPane.showMessageDialog(contentPane, "修改成功！");
                    dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(contentPane, "修改失败！");

                }

                // TODO Auto-generated constructor stub

            }
        });

        this.setSize(500, 500);
        this.setTitle("修改店铺信息");
        this.setModal(true);
        this.setResizable(false);
        this.setVisible(true);
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
        new StoreInfoModifyDlg();
    }

}
