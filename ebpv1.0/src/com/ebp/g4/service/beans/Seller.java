/**
 * 类名：Seller
 * 对应界面内容：管理员主界面的管理卖家所需的卖家信息
 */

package com.ebp.g4.service.beans;

public class Seller extends Buyer
{
    private String storeName;   // 店铺名称

    private String storeType;   // 店铺类型
    
    private String account;		//店铺ID
    public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}


	public String getStoreName()
    {
        return storeName;
    }

    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }

    public String getStoreType()
    {
        return storeType;
    }

    public void setStoreType(String storeType)
    {
        this.storeType = storeType;
    }
    @Override
    public boolean equals(Object obj)
    {
    	Seller seller = (Seller)obj;
    	if(this.account.equals(seller.account)&&
    			this.storeName.equals(seller.storeName)&&
    			this.storeType.equals(seller.storeType)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

	@Override
	public String toString() {
		return "Seller [storeName=" + storeName + ", storeType=" + storeType + ", account=" + account + "]";
	}

}
