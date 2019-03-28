/**
 * 类名：Register
 * 对应界面的内容：注册界面的信息
 */

package com.ebp.g4.service.beans;

public class Register
{
	private String account;
    private String userName;            // 昵称

    private String passWord;            // 密码

    private String confirmPassword;     // 确认密码

    private String phoneNumber;         // 手机号

    private String email;               // 电子邮箱

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassWord()
    {
        return passWord;
    }

    public void setPassWord(String passWord)
    {
        this.passWord = passWord;
    }

    public String getConfirmPassword()
    {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword)
    {
        this.confirmPassword = confirmPassword;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
    @Override
    public boolean equals(Object obj)
    {
    	Register re = (Register)obj;
    	if(this.userName.equals(re.userName)&&
    			this.phoneNumber.equals(re.phoneNumber)&&
    					this.passWord.equals(re.passWord)&&
    					this.confirmPassword.equals(re.confirmPassword)&&
    					this.email.equals(re.email)
    					){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

	@Override
	public String toString() {
		return "Register [userName=" + userName + ", passWord=" + passWord + ", confirmPassword=" + confirmPassword
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}


    
}
