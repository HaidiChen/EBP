package com.ebp.g4.view.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ebp.g4.service.beans.LogIn;
import com.ebp.g4.service.beans.Register;
import com.ebp.g4.service.implement.ServiceFactory;
import com.ebp.g4.service.interfaces.UserServiceIntf;
import com.ebp.g4.view.MainFrame;

public class RegisterDlg extends JDialog
{
    private final JPanel contentPanel = new JPanel();
    private JTextField textField[]=new JTextField[5];
    private JLabel label[]=new JLabel[5];
    private JPasswordField pwd = new JPasswordField(); 
    private JPasswordField confirm_pwd = new JPasswordField();   
    private UserServiceIntf registerservice=ServiceFactory.getUserService();    //添加接口
    private Component jPanel;                           //  提示框 
    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        RegisterDlg dialog = new RegisterDlg();
    }

    /**
     * Create the dialog.
     */
    public RegisterDlg()
    {
        this.setIconImage(new ImageIcon("image/logos/user.png").getImage());
        setBounds(100, 100, 646, 588);
        this.setModal(true);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        {
            label[0]=new JLabel("账号");
            label[1] = new JLabel("密码");       
            label[2] = new JLabel("确认密码");          
            label[3] = new JLabel("手机号码");
            label[4] = new JLabel("电子邮箱");
        }
        for(int i=0,j=0;i<label.length;i++,j=j+70)
        {
            label[i].setBounds(112, 40+j, 72, 35);
            contentPanel.add(label[i]);
        }
        
        for(int i=0,j=0;i<textField.length;i++,j=j+70){
            if(i==1)
            {
             //   JPasswordField pwd = new JPasswordField();   
                pwd.setColumns(10);
                
                pwd.setBounds(199, 40+j, 280, 35);
                contentPanel.add(pwd); 
            }
            else
            {
                if(i==2)
                {
                   // JPasswordField confirm_pwd = new JPasswordField();   
                    confirm_pwd.setColumns(10);
                    confirm_pwd.setBounds(199, 40+j, 280, 35);
                    contentPanel.add(confirm_pwd); 
                }
                else
                {
                    textField[i] = new JTextField();
                    textField[i].setColumns(10);
                    textField[i].setBounds(199, 40+j, 280, 35);
                    contentPanel.add(textField[i]);     
                }      
            }     
        }     
        
        //提示label
        JLabel label_tip=new JLabel("账户只能输入字母和数字");
        label_tip.setBounds(285, 370, 280, 35);
        label_tip.setForeground(Color.RED);
        label_tip.setBackground(Color.RED);
        contentPanel.add(label_tip);
        //账号
        textField[0].addKeyListener(new KeyAdapter()     //限制  输入长度   为10
                {
                    @Override
                    public void keyTyped(KeyEvent e) 
                    
                    {             
                        char ch = (char) e.getKeyChar();  //得到当前输入字符
                        if((ch >= '0'&& ch <='9')||(ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z'))
                        {
                            
                        }
                        else
                        {
                            e.consume();
                        }
                         String text =textField[0].getText(); 
                                if(text.length()>=10)          
                                {
                                    e.consume();
                                }           
                    }
                });
        //密码
        //确认密码
        //手机号码
        textField[3].addKeyListener(new KeyAdapter()     //限制   手机号码    输入长度   为11
                {
                    @Override
                    public void keyTyped(KeyEvent e) 
                    
                    {             
                        //textField[3].setText(textField[3].getText().replaceAll("^[0-9]", ""));
                        char ch = (char) e.getKeyChar();  //得到当前输入字符
                        if(ch >= KeyEvent.VK_0 && ch <= KeyEvent.VK_9)
                        {
                            
                        }
                        else
                        {
                            e.consume();
                        }
                         String text =textField[3].getText(); 
                                if(text.length()>=11)          
                                {
                                    e.consume();
                                }           
                    }
                });
        //电子邮箱
        
      //  textFile[]
        /**
         * 
         * 提交注册信息
         * 
         * 
         * **/
        JButton okButton = new JButton("提交");
        okButton.setForeground(new Color(255, 255, 255));
        okButton.setBackground(new Color(144, 238, 144));
        okButton.setBounds(199, 420, 88, 35);
        contentPanel.add(okButton);
        okButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                
             //   boolean verifyRegisterData(Register register);
                Register register_info=new Register();
                //判断 账号是否输入
                if(null==textField[0].getText()|| textField[0].getText().equals(""))
                {
                    
                    label_tip.setText("请输入昵称");
                }
                else
                {   //判断密码是否输入
                    if(null == String.valueOf(pwd.getPassword()) || String.valueOf(pwd.getPassword()).equals(""))
                    {
                        label_tip.setText("请输入密码");
                    }
                    else
                    {   //判断确认密码是否输入
                        if(null == String.valueOf(confirm_pwd.getPassword()) ||String.valueOf(confirm_pwd.getPassword()).equals(""))
                        {
                            label_tip.setText("请输入确认密码");
                        }
                        else
                        {   //判断手机号是否输入
                            String value=textField[3].getText();
                            String phone= "^[1](([3][0-9])|([4][5,7,9])|([5][^4,6,9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$";
                        
                            if(null == textField[3].getText() ||textField[3].getText().equals("")
                                    ||value.matches(phone)==false)
                            {
                                label_tip.setText("手机号不正确");
                            }
                            else
                            {   //判断邮箱是否输入
                                String email=textField[4].getText();
                                if(email.matches("[a-zA-Z0-9_]+@[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)+")==true)
                                {
                                    //判断密码和确认密码是否相同
                                    if(String.valueOf(pwd.getPassword()).equals(String.valueOf(confirm_pwd.getPassword()))==true)    
                                    {
                                        register_info.setAccount(textField[0].getText());
                                        register_info.setPassWord(String.valueOf(pwd.getPassword()));
                                        register_info.setConfirmPassword(String.valueOf(pwd.getPassword()));
                                        register_info.setPhoneNumber(textField[3].getText());
                                        register_info.setEmail(textField[4].getText());
                               
                                        if(registerservice.verifyRegisterData(register_info)==true)
                                        {
                                            label_tip.setText("注册成功");
                                            MainFrame.isLogin=1;
                                            MainFrame.Useraccount=register_info.getAccount();
                                            JOptionPane.showMessageDialog
                                            (jPanel, "注册成功", "提示",JOptionPane.WARNING_MESSAGE);
                                            exitFrame();
                                        }
                                        else
                                        {
                                            label_tip.setText("账号已存在");
                                        }
                                    }
                                    else
                                    {
                                        label_tip.setText("两次密码输入不一致");
                                    }
                                   
                                }
                                else
                                {   
                                   
                                    label_tip.setText("邮箱不正确");
                                }
                            }
                            
                        }
                        
                    }
                }
                
            }
        });
        
        JButton cancelButton = new JButton("取消");
        cancelButton.setForeground(new Color(255, 255, 255));
        cancelButton.setBackground(new Color(144, 238, 144));
        cancelButton.setBounds(391, 420, 88, 35);
        contentPanel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             //   System.exit(EXIT_ON_CLOSE);;
                closeFrame() ;
            }
        });
        
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("注册");
        this.setResizable(false);
       
    }
    private void exitFrame()
    {
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
