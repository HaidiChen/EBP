/**
 * 类名：Buyer
 * 对应界面内容：管理员界面的管理买家需要的买家信息
 */

package com.ebp.g4.service.beans;

public class Buyer
{
    private String nickName;    // 昵称

    private String account;     // 账号

    private String phone;       // 手机号

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
    @Override
    public boolean equals(Object obj)
    {
    	Buyer buyer = (Buyer)obj;
    	if(this.nickName.equals( buyer.nickName)&&
    			this.account.equals(buyer.account)&&
    			this.phone.equals(buyer.phone)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

	@Override
	public String toString() {
		return "Buyer [nickName=" + nickName + ", account=" + account + ", phone=" + phone + "]";
	}
    
}
