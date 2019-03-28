package com.ebp.g4.view.seller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.ebp.g4.service.beans.OrderDetail;
import com.ebp.g4.service.implement.ServiceFactory;
import com.ebp.g4.service.interfaces.OrderServiceIntf;
import java.awt.Color;

public class OrderDetailsDlg extends JDialog
{

    private final JPanel contentPanel = new JPanel();

    private JTextField textField[] = new JTextField[7];

    private JLabel label[] = new JLabel[8];

    private JTextArea textArea;

    private OrderServiceIntf orderService = ServiceFactory.getOrderService();

    private OrderDetail order;

    public OrderDetailsDlg(String orderNumber)
    {
        this.setIconImage(new ImageIcon("image/logos/order.png").getImage());
        setBounds(100, 100, 646, 788);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        {
            label[0] = new JLabel("订单号");
            label[1] = new JLabel("商品编号");
            label[2] = new JLabel("店铺名称");
            label[3] = new JLabel("交易时间");
            label[4] = new JLabel("收货人");
            label[5] = new JLabel("手机号");
            label[6] = new JLabel("物流");
            label[7] = new JLabel("收货地址");
        }
        for (int i = 0, j = 0; i < label.length; i++, j = j + 70)
        {
            label[i].setBounds(112, 40 + j, 72, 35);
            contentPanel.add(label[i]);
        }
        for (int i = 0, j = 0; i < textField.length; i++, j = j + 70)
        {
            textField[i] = new JTextField();
            textField[i].setColumns(10);
            textField[i].setBounds(199, 40 + j, 288, 35);
            textField[i].setEditable(false);
            contentPanel.add(textField[i]);
        }
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setBounds(199, 530, 288, 60);
        textArea.setEditable(false);
        contentPanel.add(textArea);

        order = orderService.getOrderDetailsByOrderNumber(orderNumber);
        {
            textField[0].setText(order.getOrderNumber());
            textField[1].setText(order.getGoodsNumber());
            textField[2].setText(order.getStoreName());
            textField[3].setText(order.getOrderDate());
            textField[4].setText(order.getReceiver());
            textField[5].setText(order.getPhone());
            textField[6].setText(order.getLogisticsName());
            textArea.setText(order.getReceiveAddress());
        }
        JButton okButton = new JButton("确认");
        okButton.setBackground(new Color(144, 238, 144));
        okButton.setForeground(Color.WHITE);
        okButton.setBounds(250, 650, 88, 35);
        okButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                dispose();
            }
        });
        contentPanel.add(okButton);
        this.setVisible(true);
        this.setTitle("订单详情");
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    }
}