/**
 * 类名：PwdModify
 * 对应界面内容：修改密码界面的信息
 */

package com.ebp.g4.service.beans;

public class PwdModify
{
    private String newPassword;         // 新密码

    private String originalPassword;    // 旧密码

    private String account;             // 账号

    public String getNewPassword()
    {
        return newPassword;
    }

    public void setNewPassword(String newPassword)
    {
        this.newPassword = newPassword;
    }

    public String getOriginalPassword()
    {
        return originalPassword;
    }

    public void setOriginalPassword(String originalPassword)
    {
        this.originalPassword = originalPassword;
    }

    public String getUserID()
    {
        return account;
    }

    public void setUserID(String userID)
    {
        this.account = userID;
    }

    public String getConfirmNewPassword()
    {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword)
    {
        this.confirmNewPassword = confirmNewPassword;
    }

    private String confirmNewPassword;
    @Override
    public boolean equals(Object obj)
    {
    	PwdModify pm = (PwdModify)obj;
    	if(this.newPassword.equals(pm.newPassword)&&
    			this.account.equals(pm.account)&&
    					this.originalPassword.equals(pm.originalPassword)&&
    					this.confirmNewPassword.equals(pm.confirmNewPassword)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

	@Override
	public String toString() {
		return "PwdModify [newPassword=" + newPassword + ", originalPassword=" + originalPassword + ", account="
				+ account + ", confirmNewPassword=" + confirmNewPassword + "]";
	}
    
}
