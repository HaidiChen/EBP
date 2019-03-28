/**
 * 类名：Store
 * 对应界面内容：申请店铺，修改店铺等和店铺有关的信息
 */

package com.ebp.g4.service.beans;

public class Store
{

	

	@Override
	public String toString() {
		return "Store [account=" + account + ", nickName=" + nickName + ", phone=" + phone + ", shopName=" + shopName
				+ ", shopType=" + shopType + ", realName=" + realName + ", IDCard=" + IDCard + ", bankAccount="
				+ bankAccount + ", logisticsCompany=" + logisticsCompany + ", info=" + info + ", shopid=" + shopid
				+ "]";
	}

	private String account;     // 账号

    private String nickName;    // 昵称

    private String phone;       // 手机号

    private String shopName;    // 店铺名称

    private String shopType;    // 店铺类型

    private String realName;    // 真是姓名

    private String IDCard;      // 身份证号

    private String bankAccount; // 银行账号

    private String logisticsCompany;    // 物流公司
    
    private String info;		// 店铺信息
    private String shopid;
    public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid;
	}

	public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getShopName()
    {
        return shopName;
    }

    public void setShopName(String shopName)
    {
        this.shopName = shopName;
    }

    public String getShopType()
    {
        return shopType;
    }

    public void setShopType(String shopType)
    {
        this.shopType = shopType;
    }

    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    public String getIDcart()
    {
        return IDCard;
    }

    public void setIDcart(String IDCard)
    {
        this.IDCard = IDCard;
    }

    public String getBankAccount()
    {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount)
    {
        this.bankAccount = bankAccount;
    }

    public String getLogisticsCompany()
    {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany)
    {
        this.logisticsCompany = logisticsCompany;
    }
    @Override
    public boolean equals(Object obj)
    {
    	Store store = (Store)obj;
    	if(this.account.equals(store.account)&&
    			this.bankAccount.equals(store.bankAccount)&&
    			this.IDCard.equals(store.IDCard)&&
    			this.logisticsCompany.equals(store.logisticsCompany)&&
    			this.nickName.equals(store.nickName)&&
    			this.phone.equals(store.phone)&&
    			this.realName.equals(store.realName)&&
    			this.shopName.equals(store.shopName)&&
    			this.shopType.equals(store.shopType)
    			){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

	public String getIDCard() {
		return IDCard;
	}

	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}

	public String getInfo() {
		// TODO Auto-generated method stub
		return info;
	}
	
	public void setInfo(String info){
		this.info = info;
	}
}
