package com.ebp.g4.view.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ebp.g4.service.beans.UserInfo;
import com.ebp.g4.service.implement.ServiceFactory;
import com.ebp.g4.service.interfaces.UserServiceIntf;
import com.ebp.g4.view.MainFrame;

public class UserInfoDlg extends JDialog
{
    private final JPanel contentPanel = new JPanel();
    private JTextField textField[]=new JTextField[6];
    private JLabel label[]=new JLabel[7];
    JScrollPane jscrollPane;
    JTextArea textArea;
    private UserServiceIntf userinfoservice=ServiceFactory.getUserService();
    /**
     * Launch the application.
     */
  /*  public static void main(String[] args)
    {
        UserInfoDlg dialog = new UserInfoDlg();
        
    }
*/
    /**
     * Create the dialog.
     */
    public UserInfoDlg()
    {
        this.setIconImage(new ImageIcon("image/logos/user.png").getImage());
        setBounds(100, 100, 646, 688);
        this.setModal(true);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        this.setTitle("个人信息");
        /**
         *
         * 通过用户账户     获得用户详细信息
         * 
         * */
        UserInfo userinfo=new UserInfo();
        userinfo=userinfoservice.getUserInfoByAccount(MainFrame.Useraccount);
      {
            label[0]=new JLabel("头像");
            label[1] = new JLabel("昵称");
            label[2] = new JLabel("账号");
            label[3] = new JLabel("手机号");
            label[4] = new JLabel("电子邮箱");
            label[5] = new JLabel("真实姓名");
            label[6] = new JLabel("收货地址");
            label[6].setBounds(112, 460, 72, 35);
            contentPanel.add(label[6]);
        }
        for(int i=0,j=0;i<label.length-1;i++,j=j+70)
        {
            label[i].setBounds(112, 40+j, 72, 35);
            contentPanel.add(label[i]);
        }
        for(int i=0;i<textField.length;i++){
            textField[i] = new JTextField();
            textField[i].setColumns(10);
            contentPanel.add(textField[i]);            
        }
        textField[0].setBounds(199, 40, 188, 35);
        for(int i=1,j=0;i<textField.length;i++,j=j+70) {
            textField[i].setBounds(199, 110+j, 280, 35);               
         }     
       
        textArea = new JTextArea();
        textArea.setLineWrap(true);// 设置文本区的换行策略。
        jscrollPane = new JScrollPane(textArea); 
        //设置矩形大小，是在滚动条上设置，而不是在文本框上设置；
        jscrollPane.setBounds(199, 460, 280, 60);
        //默认的设置是超过文本框才会显示滚动条，以下设置让滚动条一直显示
        jscrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentPanel.add(jscrollPane);
        
        //添加提示label_tip
        JLabel label_tip=new JLabel("");
        label_tip.setBounds(295, 520, 280, 35);
        label_tip.setForeground(Color.RED);
        label_tip.setBackground(Color.RED);
        contentPanel.add(label_tip);
        //头像 textField[0]
        textField[0].setText(userinfo.getPortrait());
        textField[0].setEditable(false);
        //昵称textField[1]
        textField[1].setText(userinfo.getNickName());
        textField[1].addKeyListener(new KeyAdapter()     //限制   昵称    输入长度   为   15
                {
                    @Override
                    public void keyTyped(KeyEvent e) 
                    
                    {             
                        
                         String text =textField[1].getText(); 
                                if(text.length()>=15)          
                                {
                                    e.consume();
                                }           
                    }
                });
        //账号textField[2]
        textField[2].setText(MainFrame.Useraccount);
        textField[2].setEditable(false);
        //手机号textField[3]
        textField[3].setText(userinfo.getPhone());
        textField[3].addKeyListener(new KeyAdapter()     //限制   手机号码    输入长度    且只能输入数字  为11
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
        //电子邮箱textField[4]
        textField[4].setText(userinfo.getEmail());
        //真实姓名textField[5]
        textField[5].setText(userinfo.getRealName());
        //收货地址textField[6]
        textArea.setText(userinfo.getReceiveAddress());
        JButton btnNewButton = new JButton("上传");
        btnNewButton.setBackground(new Color(144, 238, 144));
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBounds(388, 40, 88, 35);
        contentPanel.add(btnNewButton);
        btnNewButton.addMouseListener(new MouseAdapter() { // 添加鼠标点击事件
            public void mouseClicked(MouseEvent event) {
         
             textField[0].setText( eventOnImport(new JButton()));
            }
           });
        
        JButton okButton = new JButton("提交");
        okButton.setBackground(new Color(144, 238, 144));
        okButton.setForeground(new Color(255, 255, 255));
        okButton.setBounds(199, 560, 88, 35);
        contentPanel.add(okButton);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(null==textField[0].getText()|| textField[0].getText().equals(""))
                {
                    label_tip.setText("请上传头像");
                }
                else
                {
                    if(null==textField[1].getText()|| textField[1].getText().equals(""))
                    {
                        label_tip.setText("请输入昵称");
                    }
                    else
                    {
                        if(null==textField[3].getText()|| textField[3].getText().equals("")
                                ||textField[3].getText().matches("^[1](([3][0-9])|([4][5,7,9])|([5][^4,6,9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$")==false)
                        {
                            label_tip.setText("手机号不正确");
                        }
                        else
                        {
                            if(null==textField[4].getText()|| textField[4].getText().equals("")
                                    ||textField[4].getText().matches("[a-zA-Z0-9_]+@[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)+")==false)
                            {
                                label_tip.setText("请输入邮箱");
                            }
                            else
                            {
                                if(null==textField[5].getText()|| textField[5].getText().equals(""))
                                {
                                    label_tip.setText("请输入真实姓名");
                                }
                                else
                                {
                                    if(null==textArea.getText()|| textArea.getText().equals(""))
                                    {
                                        label_tip.setText("请输入收货地址");
                                    }
                                    else
                                    {
                                     //   userinfo.setPortrait(textField[0].getText());
                                        UserInfo newuserinfo=new UserInfo();
                                        newuserinfo.setPortrait(textField[0].getText());
                                        newuserinfo.setNickName(textField[1].getText());
                                        newuserinfo.setPhone(textField[3].getText());
                                        newuserinfo.setEmail(textField[4].getText());
                                        newuserinfo.setRealName(textField[5].getText());
                                        newuserinfo.setReceiveAddress(textArea.getText());
                                    //    boolean modifyUserInfoByAccount(String account, UserInfo newUser);
                                        if(userinfoservice.modifyUserInfoByAccount(MainFrame.Useraccount, newuserinfo)==true)
                                        {
                                            
                                            label_tip.setText("修改成功");
                                            exitFrame();
                                            
                                        }
                                        else
                                        {
                                            label_tip.setText("修改失败");
                                        }
                                    }
                                }
                            }
                        }
                    }
                    
                }
            }
        });
        
        JButton cancelButton = new JButton("取消");
        cancelButton.setBackground(new Color(144, 238, 144));
        cancelButton.setForeground(new Color(255, 255, 255));
        cancelButton.setBounds(391, 560, 88, 35);
        contentPanel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             //   System.exit(EXIT_ON_CLOSE);;
               closeFrame();  
                
            }
        });
        
        
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
       
        this.setResizable(false);
       
    }
    /**
     * 文件上传功能
     * 
     * @param developer
     *            按钮控件名称
     */
    public  String  eventOnImport(JButton developer)
    {
     JFileChooser chooser = new JFileChooser();
     chooser.setMultiSelectionEnabled(true);
     /** 过滤文件类型 * */
     FileNameExtensionFilter filter1 = new FileNameExtensionFilter("jpg","jpg");
     FileNameExtensionFilter filter2 = new FileNameExtensionFilter("jpeg","jpeg");
     chooser.addChoosableFileFilter(filter2);chooser.addChoosableFileFilter(filter1);
     int returnVal = chooser.showOpenDialog(developer);
     if (returnVal == JFileChooser.APPROVE_OPTION) 
     {
         /** 得到选择的文件* */
         File[] arrfiles = chooser.getSelectedFiles();
         if (arrfiles == null || arrfiles.length == 0) 
         {
             return   "无";
         }
         FileInputStream input = null;
         FileOutputStream out = null;
         String path = "image/userphoto/";
         try 
        {
             for (File f : arrfiles) 
             {
                 File dir = new File(path);
                 //System.out.print(path);
                 /** 目标文件夹 * */
                 File[] fs = dir.listFiles();
                 HashSet<String> set = new HashSet<String>();
                 for (File file : fs)
                 {
                     set.add(file.getName());
                 }
                /** 判断是否已有该文件* */
                 if (set.contains(f.getName())) 
                 {
                     JOptionPane.showMessageDialog(new JDialog(),
                     f.getName() + ":该文件已存在！"+path);
                     return  ""  ;
                 }
                 input = new FileInputStream(f);
                 byte[] buffer = new byte[1024];
                 File des = new File(path, f.getName());
                 out = new FileOutputStream(des);
                 int len = 0;
                 while (-1 != (len = input.read(buffer))) 
                {
                    out.write(buffer, 0, len);
                }
                  out.close();
                  input.close();
            }
            JOptionPane.showMessageDialog(null, "上传成功！", "提示",
            JOptionPane.INFORMATION_MESSAGE);

         } 
         catch (FileNotFoundException e1) 
         {
            JOptionPane.showMessageDialog(null, "上传失败！", "提示",
            JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
         } 
         catch (IOException e1) 
         {
            JOptionPane.showMessageDialog(null, "上传失败！", "提示",
            JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
         }
     }
    return chooser.getSelectedFile()==null?"":chooser.getSelectedFile().getName();
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
