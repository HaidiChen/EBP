package com.ebp.g4.service.beans;

public class logCompanys {
	private String companyId;
	private String companyName;
	@Override
	public String toString() {
		return "logCompanys [companyId=" + companyId + ", companyName=" + companyName + "]";
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	 @Override
	    public boolean equals(Object obj)
	    {
		 logCompanys logCompany = (logCompanys)obj;
	    	if(this.companyId.equals( logCompany.companyId)&&
	    			this.companyName.equals(logCompany.companyName)){
	    		return true;
	    	}
	    	else{
	    		return false;
	    	}
	    }
}
