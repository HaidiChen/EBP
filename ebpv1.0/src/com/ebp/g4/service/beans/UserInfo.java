/**
 * 类名：UserInfo
 * 对应界面内容：查看个人信息界面中的信息
 */

package com.ebp.g4.service.beans;

public class UserInfo
{
    private String portrait;        // 头像

    private String nickName;        // 昵称

    private String account;         // 账号

    private String phone;           // 手机号

    private String email;           // 电子邮箱

    private String receiveAddress;  // 收获地址

    private String realName;        // 真是姓名

    public String getPortrait()
    {
        return portrait;
    }

    public void setPortrait(String portrait)
    {
        this.portrait = portrait;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getReceiveAddress()
    {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress)
    {
        this.receiveAddress = receiveAddress;
    }

    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }
    @Override
    public boolean equals(Object obj)
    {
    	UserInfo ui = (UserInfo)obj;
    	if(this.account.equals(ui.account)&&
    			this.nickName.equals(ui.nickName)&&
    			this.phone.equals(ui.phone)&&
    			this.realName.equals(ui.realName)&&
    			this.portrait.equals(ui.portrait)&&
    			this.email.equals(ui.email)&&
    			this.receiveAddress.equals(ui.receiveAddress)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

	@Override
	public String toString() {
		return "UserInfo [portrait=" + portrait + ", nickName=" + nickName + ", account=" + account + ", phone=" + phone
				+ ", email=" + email + ", receiveAddress=" + receiveAddress + ", realName=" + realName + "]";
	}
    
}
