/**
 * 类名：LogIn
 * 对应界面内容：登录界面的信息
 */

package com.ebp.g4.service.beans;

public class LogIn
{
    private String userID;      // 账号

    private String passWord;    // 密码

    public String getUserID()
    {
        return userID;
    }

    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    public String getPassword()
    {
        return passWord;
    }

    public void setPassword(String passWord)
    {
        this.passWord = passWord;
    }
    @Override
    public boolean equals(Object obj)
    {
    	LogIn li = (LogIn)obj;
    	if(this.userID .equals( li.userID)&&
    			this.passWord.equals(li.passWord)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

	@Override
	public String toString() {
		return "LogIn [userID=" + userID + ", passWord=" + passWord + "]";
	}
    
}
