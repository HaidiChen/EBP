package com.ebp.g4.view.user;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ebp.g4.service.beans.LogIn;
import com.ebp.g4.service.implement.ServiceFactory;
import com.ebp.g4.service.interfaces.MainFrameServiceIntf;
import com.ebp.g4.service.interfaces.UserServiceIntf;
import com.ebp.g4.view.MainFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;

/*
 * 修改版
 * 
 * 修改内容
 * 布局
 * 只关闭当前窗口 
 * 退出按钮  实现  关闭提示
 * 
 * 未实现
 * 读取账户  密码 文本    实现 登陆判断
 * 
 * 
 * */
public class LogInDlg extends JDialog {

	private JPanel contentPane;
	private JTextField Login_account;
	private JPasswordField Login_psw;
	private UserServiceIntf loginservice=ServiceFactory.getUserService();
	 private Component jPanel;                           //  提示框  
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInDlg frame = new LogInDlg();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public LogInDlg() {
	    setResizable(false);
		setTitle("登录");
		this.setModal(true);
		this.setIconImage(new ImageIcon("image/logos/add.png").getImage());
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//只关闭当前窗口
		setBounds(100, 100, 450, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
		
		Login_account = new JTextField();      //账号   文本框
		Login_account.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        
		    }
		});
		
		Login_account.setColumns(10);
		
		JLabel label_tip = new JLabel("");          //错误提示框
		label_tip.setForeground(Color.RED);
		label_tip.setBackground(Color.RED);
		
		Login_psw = new JPasswordField();         //密码文本框
		
		JLabel account = new JLabel("账号");              //文字账号显示
		account.setFont(new Font("宋体", Font.PLAIN, 15));
		
		JLabel pwd= new JLabel("密码");                   //文字密码显示
		pwd.setFont(new Font("宋体", Font.PLAIN, 15));
		
		JPanel panel_7 = new JPanel();
		
		JButton button_login = new JButton("登录");                   //登陆按钮
		button_login.setBackground(new Color(144, 238, 144));
		button_login.setForeground(new Color(255, 255, 255));
		
		/*
         * 登陆提示框
         * 
         * 
         * 
         * */
		button_login.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        
		        LogIn login=new LogIn();
		        login.setUserID(Login_account.getText());
		       
		        login.setPassword(String.valueOf(Login_psw.getPassword()));   //将 password  的 char[] 转为String 存入对象
		        if(null == Login_account.getText() || Login_account.getText().equals(""))
		        {
		            label_tip.setText("请输入账号");
		        }
		        else
		        {
		            if(null == String.valueOf(Login_psw.getPassword())||String.valueOf( Login_psw.getPassword()).equals(""))
		            {
		                label_tip.setText("请输入密码");
		            }
		            else
		            {
		                if(loginservice.verifyLogInData(login)==true)
		                {
		       
		                    MainFrame.isLogin=1;
		                   
		                    MainFrame.Useraccount=Login_account.getText();
		           
		                    label_tip.setText("登陆成功"); 
		                    
		               
		                    dispose();
		                    
		                }
		                else
		                {
		                   
		                   if(loginservice.verifyAdminLogInData(login)==true)
		                   {
		                      
		                       MainFrame.isLogin=2;
	                           MainFrame.Useraccount=Login_account.getText();
	                           label_tip.setText("登陆成功"); 
	                           dispose();
	                           
	                          
	                            
		                   }
		                   else
		                   {
		                       label_tip.setText("账号或密码不正确");
		                      /* System.out.println(Login_account.getText());
	                            System.out.println(login.getPassword());
	                           System.out.println(login.getUserID());
	                           System.out.println(loginservice.verifyLogInData(login));*/
		                   }
		                  
		                   
		                }
		            }
		        }
		    }
		});
		button_login.setFont(new Font("宋体", Font.PLAIN, 15));
		 
		JButton button_exit = new JButton("退出");                           //退出按钮
		button_exit.setBackground(new Color(144, 238, 144));
		button_exit.setForeground(new Color(255, 255, 255));
		button_exit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		     //   System.exit(EXIT_ON_CLOSE);;
		        closeFrame();  
		    }
		});
		
		button_exit.setFont(new Font("宋体", Font.PLAIN, 15));   
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
		    gl_panel_7.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_panel_7.createSequentialGroup()
		            .addGap(74)
		            .addComponent(button_login)
		            .addGap(118)
		            .addComponent(button_exit)
		            .addGap(280))
		);
		gl_panel_7.setVerticalGroup(
		    gl_panel_7.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_panel_7.createSequentialGroup()
		            .addGap(5)
		            .addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
		                .addComponent(button_login)
		                .addComponent(button_exit)))
		);
		panel_7.setLayout(gl_panel_7);
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
		    gl_panel_6.createParallelGroup(Alignment.TRAILING)
		        .addGroup(gl_panel_6.createSequentialGroup()
		            .addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
		                .addGroup(Alignment.TRAILING, gl_panel_6.createSequentialGroup()
		                    .addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
		                        .addComponent(account, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
		                        .addComponent(pwd, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
		                    .addGap(76)
		                    .addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
		                        .addComponent(Login_psw, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
		                        .addComponent(Login_account, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
		                    .addGap(259))
		                .addGroup(Alignment.TRAILING, gl_panel_6.createSequentialGroup()
		                    .addContainerGap()
		                    .addComponent(panel_7, GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE))
		                .addGroup(gl_panel_6.createSequentialGroup()
		                    .addGap(173)
		                    .addComponent(label_tip, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
		            .addContainerGap())
		);
		gl_panel_6.setVerticalGroup(
		    gl_panel_6.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_panel_6.createSequentialGroup()
		            .addContainerGap()
		            .addComponent(label_tip)
		            .addGap(22)
		            .addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
		                .addComponent(Login_account, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                .addComponent(account))
		            .addGap(41)
		            .addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
		                .addComponent(Login_psw, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                .addComponent(pwd))
		            .addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
		            .addComponent(panel_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		            .addContainerGap())
		);
		panel_6.setLayout(gl_panel_6);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
		    gl_panel_3.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_panel_3.createSequentialGroup()
		            .addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 268, Short.MAX_VALUE)
		            .addGap(20))
		);
		gl_panel_3.setVerticalGroup(
		    gl_panel_3.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_panel_3.createSequentialGroup()
		            .addGap(20)
		            .addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
		            .addContainerGap(38, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		setVisible(true);
	}
	
	

	//关闭窗口功能
	private void closeFrame()  
    {  
      //  System.out.println("调用窗体关闭功能");  
        int result = JOptionPane.showConfirmDialog(null, "是否要退出？", "退出确认", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);  
        if (result == JOptionPane.YES_OPTION)  
            this.dispose();  
    }  

}
