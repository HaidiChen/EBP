package com.ebp.g4.service.beans;

public class shopType {
	private String shopTypeId;
	private String shopTypeName;
	public String getShopTypeId() {
		return shopTypeId;
	}
	public void setShopTypeId(String shopTypeId) {
		this.shopTypeId = shopTypeId;
	}
	public String getShopTypeName() {
		return shopTypeName;
	}
	public void setShopTypeName(String shopTypeName) {
		this.shopTypeName = shopTypeName;
	}
	@Override
	public String toString() {
		return "shopType [shopTypeId=" + shopTypeId + ", shopTypeName=" + shopTypeName + "]";
	}
	@Override
    public boolean equals(Object obj)
    {
		shopType shopType = (shopType)obj;
    	if(this.shopTypeId.equals(shopType.shopTypeId)&&
    			this.shopTypeName.equals(shopType.shopTypeName)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
}
