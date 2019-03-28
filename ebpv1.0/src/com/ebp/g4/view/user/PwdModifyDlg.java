package com.ebp.g4.view.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ebp.g4.service.beans.PwdModify;
import com.ebp.g4.service.beans.Register;
import com.ebp.g4.service.implement.ServiceFactory;
import com.ebp.g4.service.interfaces.UserServiceIntf;
import com.ebp.g4.view.MainFrame;

public class PwdModifyDlg extends JDialog
{
    private final JPanel contentPanel = new JPanel();
    private JTextField textField[]=new JTextField[4];
    private JPasswordField passwordfield[]=new JPasswordField[4];
    private JLabel label[]=new JLabel[4];
    private UserServiceIntf pwdmodifyservice=ServiceFactory.getUserService();
    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        PwdModifyDlg dialog = new PwdModifyDlg();
    }

    /**
     * Create the dialog.
     */
    public PwdModifyDlg()
    {
        this.setIconImage(new ImageIcon("image/logos/pwd.png").getImage());
        setBounds(100, 100, 646, 488);
        this.setModal(true);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        {
            label[0]=new JLabel("账号");
            label[1] = new JLabel("原始密码");       
            label[2] = new JLabel("新密码");          
            label[3] = new JLabel("确认新密码");
        }
        for(int i=0,j=0;i<label.length;i++,j=j+70)
        {
            label[i].setBounds(112, 40+j, 72, 35);
            contentPanel.add(label[i]);
        }
        for(int i=0,j=0;i<textField.length;i++,j=j+70){
            
            if(i==0)
            {
                //账号    textField[0]
                textField[i] = new JTextField();
                textField[i].setColumns(10);
                textField[i].setBounds(199, 40+j, 280, 35);
                contentPanel.add(textField[i]); 
            }
            else
            {
               
                  //旧密码   passwordfield[1]
                  //新密码   passwordfield[2]
                  //旧密码   passwordfield[3]
                passwordfield[i] = new JPasswordField();
                passwordfield[i].setColumns(10);
                passwordfield[i].setBounds(199, 40+j, 280, 35);
                contentPanel.add(passwordfield[i]);  
            }
                     
        }     
        
        JLabel label_tip=new JLabel("");
        label_tip.setBounds(285, 285, 280, 35);
        label_tip.setForeground(Color.RED);
        label_tip.setBackground(Color.RED);
        contentPanel.add(label_tip);
        /**
         * 
         * 设置  账户显示  且 不可更改
         * 
         * **/
        textField[0].setText(MainFrame.Useraccount);
        textField[0].setEditable(false);
        
        JButton okButton = new JButton("提交");
        okButton.setForeground(new Color(255, 255, 255));
        okButton.setBackground(new Color(144, 238, 144));
        okButton.setBounds(199, 320, 88, 35);
        contentPanel.add(okButton);
        okButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {   
                //判断是否输入  旧密码
                PwdModify pwdmodify=new PwdModify();
                if(null == String.valueOf(passwordfield[1].getPassword()) || String.valueOf(passwordfield[1].getPassword()).equals(""))
                {
                    label_tip.setText("请输入旧密码！");
                }
                else
                {   
                    //判断  新密码是否驶入
                    if(null == String.valueOf(passwordfield[2].getPassword()) || String.valueOf(passwordfield[2].getPassword()).equals(""))
                    {
                        label_tip.setText("请输入新密码！");
                    }
                    else
                    {
                        //判断确认密码是否输入
                        if(null == String.valueOf(passwordfield[3].getPassword()) || String.valueOf(passwordfield[3].getPassword()).equals(""))
                        {
                            label_tip.setText("请输入  确认密码！");
                        }
                        else
                        {
                            if(String.valueOf(passwordfield[2].getPassword()).equals(String.valueOf(passwordfield[3].getPassword()))==true)
                            {
                                pwdmodify.setUserID(textField[0].getText());
                                pwdmodify.setOriginalPassword(String.valueOf(passwordfield[1].getPassword()));
                                pwdmodify.setNewPassword(String.valueOf(passwordfield[2].getPassword()));
                                if(pwdmodifyservice.verifyPwdModification(MainFrame.Useraccount,pwdmodify)==true)
                                {
                                    
                                    label_tip.setText("密码修改成功！");
                                    exitFrame();
                                }
                                else
                                {
                                 
                                    label_tip.setText("原始密码错误！");
                           
                                }
                            }
                            else
                            {
                                label_tip.setText("新密码 和 确认密码不一致！");
                            }
                        }
                    }
                }
            }
        });
        
        JButton cancelButton = new JButton("取消");
        cancelButton.setForeground(new Color(255, 255, 255));
        cancelButton.setBackground(new Color(144, 238, 144));
        cancelButton.setBounds(391, 320, 88, 35);
        contentPanel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             //   System.exit(EXIT_ON_CLOSE);;
                closeFrame() ;
            }
        });
        
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("修改密码");
        this.setResizable(false);
       
    }
    private void exitFrame()
    {
        int result = JOptionPane.showConfirmDialog(null, "是确认修改并退出？", "退出确认", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        this.dispose();
    }
    private void closeFrame()  
    {  
      //  System.out.println("调用窗体关闭功能");  
        int result = JOptionPane.showConfirmDialog(null, "是否要退出？", "退出确认", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);  
        if (result == JOptionPane.YES_OPTION)  
            this.dispose();  
    } 

}
