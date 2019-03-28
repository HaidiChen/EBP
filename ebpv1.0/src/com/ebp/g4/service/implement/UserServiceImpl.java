package com.ebp.g4.service.implement;

import java.util.ArrayList;
import java.util.List;

import com.ebp.g4.dao.beans.Admin;
import com.ebp.g4.dao.beans.LogCompany;
import com.ebp.g4.dao.beans.Shop;
import com.ebp.g4.dao.beans.ShopType;
import com.ebp.g4.dao.beans.User;
import com.ebp.g4.dao.implement.DaoFactory;
import com.ebp.g4.dao.interfaces.AdminIntf;
import com.ebp.g4.dao.interfaces.LogCompanyIntf;
import com.ebp.g4.dao.interfaces.SellerIntf;
import com.ebp.g4.dao.interfaces.ShopIntf;
import com.ebp.g4.dao.interfaces.ShopTypeIntf;
import com.ebp.g4.dao.interfaces.UserIntf;
import com.ebp.g4.service.beans.Buyer;
import com.ebp.g4.service.beans.LogIn;
import com.ebp.g4.service.beans.PwdModify;
import com.ebp.g4.service.beans.Register;
import com.ebp.g4.service.beans.Seller;
import com.ebp.g4.service.beans.Store;
import com.ebp.g4.service.beans.UserInfo;
import com.ebp.g4.service.beans.logCompanys;
import com.ebp.g4.service.beans.shopType;
import com.ebp.g4.service.interfaces.UserServiceIntf;

class UserServiceImpl implements UserServiceIntf
{
	AdminIntf adminDao = DaoFactory.getAdminDao();
	SellerIntf      sellerDao = DaoFactory.getSellerDao();
	UserIntf	userDao =DaoFactory.getUserDao();
	ShopIntf	shopDao = DaoFactory.getShopDao();
	ShopTypeIntf	shopTypeDao = DaoFactory.getShopTypeDao();
	LogCompanyIntf logCompanyDao = DaoFactory.getLogCompanyDao();
    @Override
    public List<Buyer> getAllBuyers()
    {
    	List<Buyer> BuyerList = new ArrayList<Buyer>();
    	 /*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而
         * 进行Buyer的提取，并赋值到BuyerList对象中
         */
    	List<User> Buyers = userDao.selectAllUser();
       for(User buyer : Buyers){
    	   Buyer e = new Buyer();
    	   User users = userDao.selectUserById(buyer.getId());
    	   String nickName=  users.getNickname();
    	   String account =	users.getId();
    	   String phone = 	users.getPhone();
    	   e.setNickName(nickName);
    	   e.setAccount(account);
    	   e.setPhone(phone);
    	   BuyerList.add(e);
       }
        return BuyerList;
    }
    
    @Override
    public Buyer getBuyerByAccount(String account)
    {
    	Buyer gb = new	Buyer();
    	/*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而
         * 进行Buyer的提取，并赋值到BuyerList对象中
         */
    	User user = userDao.selectUserById(account);      
        String buyerid = user.getId();
        String nickName=  user.getNickname();
 	    String phone = 	user.getPhone();
 	    gb.setNickName(nickName);
 	    gb.setPhone(phone);
 	    gb.setAccount(buyerid);
        return gb;
    }
    
    @Override
    public List<Store> getAllSellers()
    {
    	List<Store> SellerList = new ArrayList<Store>();
    	/*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而
         * 进行Seller的提取，并赋值到SellerList对象中
         */
    	List<com.ebp.g4.dao.beans.Seller> Sellers = sellerDao.selectAllSeller();
        for(com.ebp.g4.dao.beans.Seller seller : Sellers){
        	Store e = new Store();
        	Shop shop = shopDao.selectShopById(seller.getShopid());
        	User user = userDao.selectUserById(seller.getId());
        	String storeName = shop.getName();
        	String storeType = shop.getType();
        	String nickName = user.getNickname();
        	String account = user.getId();
        	String phone = user.getPhone();
        	e.setNickName(nickName);
        	e.setPhone(phone);
        	e.setAccount(account);
        	e.setShopName(storeName);
        	e.setShopType(storeType);
     	  SellerList.add(e);
        }
        return SellerList;
    }
    public List<shopType> getAllShopTypes(){
    	List<shopType> shopTypeList = new ArrayList<shopType>();
    	List<ShopType> shopTypes = shopTypeDao.selectAllShopType();
    	for(ShopType shopType:	shopTypes){
    		shopType e = new shopType();
    		ShopType ShopType = shopTypeDao.selectShopTypeById(shopType.getId());
    		String shopTypeName = ShopType.getName();
    		String shopTypeId = ShopType.getId();
    		e.setShopTypeId(shopTypeId);
    		e.setShopTypeName(shopTypeName);
    		shopTypeList.add(e);
    	}
    	
    	

		return shopTypeList;
    	
    }
    public List<logCompanys> getAllLogCompanys(){
    	List<logCompanys> logCompanyList = new ArrayList<logCompanys>();
    	List<LogCompany> logCompanys = logCompanyDao.selectAllLogCompany();
    	for(LogCompany logCompany:logCompanys){
    		logCompanys e = new logCompanys();
    		LogCompany LogCompany = logCompanyDao.selectLogCompany(logCompany.getId());
    		String logCompanyName = LogCompany.getName();
    		String logCompanyId = LogCompany.getId();
    		e.setCompanyName(logCompanyName);
    		e.setCompanyId(logCompanyId);
    		logCompanyList.add(e);
    	}
		return logCompanyList;
    	
    }
    @Override
    public Seller getSellerByAccount(String account)
    {
    	Seller gs = new Seller();
    	/*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而
         * 进行Seller的提取，并赋值到SellerList对象中
         */
    	com.ebp.g4.dao.beans.Seller seller = sellerDao.selectSellerById(account);
    	String sellerid = seller.getShopid();
    	Shop shop = shopDao.selectShopById(sellerid);
    	String storeName = shop.getName();
    	String storeType = shop.getType();
    	gs.setAccount(sellerid);
    	gs.setStoreName(storeName);
    	gs.setStoreType(storeType);
        return gs;
    }
    
    @Override
    public UserInfo getUserInfoByAccount(String account)
    {
    	UserInfo gu = new UserInfo();
    	/*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而
         * 进行UserInfo的提取，并赋值到UserInfoList对象中
         */
    	User user = userDao.selectUserById(account);
    	String userId = user.getId();
    	String nickName=  user.getNickname();
 	    String phone = 	user.getPhone();
 	    String portrait = user.getPhoto();
 	    String email = user.getEmail();
 	    String receiveAddress = user.getAddress();
 	    com.ebp.g4.dao.beans.Seller seller = sellerDao.selectSellerById(account);
 	    String realName = seller.getRealname();
 	    gu.setAccount(userId);
 	    gu.setEmail(email);
 	    gu.setNickName(nickName);
 	    gu.setPhone(phone);
 	    gu.setPortrait(portrait);
 	    gu.setRealName(realName);
 	    gu.setReceiveAddress(receiveAddress);
        return gu;
    }
    
    @Override
    public boolean modifyUserInfoByAccount(String account, UserInfo newUserInfo)
    {
    	
    	String newNickName=  newUserInfo.getNickName();
 	    String newPhone = 	newUserInfo.getPhone();
 	   String newPhoto = newUserInfo.getPortrait();
 	    String newEmail = newUserInfo.getEmail();
 	    String newReceiveAddress = newUserInfo.getReceiveAddress();
 	    User user = userDao.selectUserById(account);
 	    user.setEmail(newEmail);
 	    user.setNickname(newNickName);
 	    user.setPhone(newPhone);
 	    user.setPhoto(newPhoto);
 	    user.setAddress(newReceiveAddress);
        return userDao.updateUser(user);
        
    }
    
    @Override
    public boolean verifyLogInData(LogIn logInData)
    {	
    	String newUserID =  logInData.getUserID();
    	String newPwd;
    	if (newUserID != null){
    	    newPwd = logInData.getPassword();
    	    User user = userDao.selectUserById(newUserID);
            String comfirmPwd = user.getPassword();
            
            if((newPwd).equals(comfirmPwd)){
                    return true;
            }
            else{
                return false;
            }
    	}
    	else {
    	    return false;
    	}
    	
    }
    
    @Override
    public boolean verifyRegisterData(Register register)
    {
    	String newAccount=  register.getAccount();
 	    String newPhone = 	register.getPhoneNumber();	
 	    String newEmail = register.getEmail();
 	    String newPwd = register.getPassWord();
 	    User user = userDao.selectUserById(newAccount);
 	    String account = user.getId();
 	    if(account	== null/*||"".equals(account)*/){
 	    user.setEmail(newEmail);
 	    user.setId(newAccount);
 	    user.setPassword(newPwd);
 	    user.setPhone(newPhone);
 	    return userDao.addUser(user);
 	    }
 	    else{
 	    	return false;
 	    }
 	    
        	
		 
        
    }
   
    @Override
    public boolean verifyPwdModification(String account,PwdModify newPwdModifyObject)
    {
    	String newPwd = newPwdModifyObject.getNewPassword();

    	String originalPwd = newPwdModifyObject.getOriginalPassword();
    	User user = userDao.selectUserById(account);
    	String oldPwd = user.getPassword();
    	if((originalPwd == null?" ":originalPwd).equals(oldPwd))
		{
    		
    		user.setPassword(newPwd);
    		userDao.updateUser(user);
    		return true;
		}
    	else{
    		return false;
    	}
    }
    
    @Override
    public boolean hasStore(String userAccount)
    {
    	com.ebp.g4.dao.beans.Seller seller= sellerDao.selectSellerById(userAccount);
    	String shopid= seller.getShopid();
    	boolean shop = shopDao.selectShop(shopid);
    	if(shop){
        return true;
    	}
    	else{
    		return false;
    	}
    }
    
    @Override
    public boolean putStoreIntoDataBase(String account, Store newStore)
    {
    	
    	if (!account.equals("") && newStore.getBankAccount() != null){
    		String newPhone = newStore.getPhone();
        	
        	String newIDCard = newStore.getIDcart();
        	String newBankAccount = newStore.getBankAccount();
        	
        	
        	User user = userDao.selectUserById(account);
        	Shop shop = new Shop();
        	shop.setInfo(newStore.getInfo());
        	shop.setLogcompany(newStore.getLogisticsCompany());
        	shop.setName(newStore.getShopName());
        	shop.setType(newStore.getShopType());
        	shopDao.addShop(shop);
        	com.ebp.g4.dao.beans.Seller seller = new com.ebp.g4.dao.beans.Seller();
        	Shop shop1 = shopDao.selectShopByName(shop.getName());
        	seller.setBankaccount(newBankAccount);
        	seller.setId(account);
        	seller.setRealname(newStore.getRealName());
        	seller.setShopid(shop1.getShopid());
        	seller.setIdcard(newIDCard);
        	user.setPhone(newPhone);
        	
        	sellerDao.addSeller(seller);
        	userDao.updateUser(user);
        	return true;
    	}
    	else {
    		return false;
    	}
        
    }
    	public String getShopId(String account){
    		com.ebp.g4.dao.beans.Seller seller = sellerDao.selectSellerById(account);
    		String shopid;
    		if (seller.getShopid() != ""){
    		    shopid=seller.getShopid();
    		}
    		else {
    		    shopid = "";
    		}
    		
    		return shopid;
    	
    }
    @Override
    public Store getStoreByAccount(String account)
    {
    	Store gst = new Store();
    	/*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而
         * 进行Store的提取，并赋值到StoreList对象中
         */    	
    	com.ebp.g4.dao.beans.Seller seller = sellerDao.selectSellerById(account);
    	String shopId = seller.getShopid();
    	String userid = seller.getId();
    	User user = userDao.selectUserById(userid);
    	Shop store = shopDao.selectShopById(shopId);
    	String nickName = user.getNickname();
    	String phone = user.getPhone();
    	String shopName = store.getName();
    	String shopType = store.getType();
    	String realName = seller.getRealname();
    	String IDCard = seller.getIdcard();
    	String bankAccount = seller.getBankaccount();
    	String logisticsCompany = store.getLogcompany();
    	gst.setAccount(userid);
    	gst.setBankAccount(bankAccount);
    	gst.setLogisticsCompany(logisticsCompany);
    	gst.setNickName(nickName);
    	gst.setIDcart(IDCard);
    	gst.setPhone(phone);
    	gst.setRealName(realName);
    	gst.setShopName(shopName);
    	gst.setShopType(shopType);
        return gst;
    }
   
    @Override
    public boolean modifyStoreByAccount(String account,Store newStore)
    {


    	String newBankAccount = newStore.getBankAccount();
    	String newLogisticsCompany = newStore.getLogisticsCompany();
    	String info = newStore.getInfo();

    	com.ebp.g4.dao.beans.Seller seller = sellerDao.selectSellerById(account);
    	String  shopid= seller.getShopid();
    	Shop shop = shopDao.selectShopById(shopid);
    	shop.setLogcompany(newLogisticsCompany);
    	shop.setInfo(info);
    	seller.setBankaccount(newBankAccount);
        return (shopDao.updateShop(shop)&&sellerDao.updateSeller(seller));

    }
    public List<String> getBuyersPropertyNames()
    {
        List<String> itemNames = new ArrayList<String>();
        
        itemNames.add("昵称");
        itemNames.add("账号");
        itemNames.add("手机号");


        
        return itemNames;
    }
    public List<String> getSellersPropertyNames()
    {
        List<String> itemNames = new ArrayList<String>();
        
        itemNames.add("昵称");
        itemNames.add("账号");
        itemNames.add("手机号");
        itemNames.add("店铺名称");
        itemNames.add("店铺类型");

        
        return itemNames;
    }

    public boolean verifyAdminLogInData(LogIn logInData){
    	String newUserID =  logInData.getUserID();
    	if (newUserID != null){
    	    String newPwd = logInData.getPassword();
            Admin admin = adminDao.selectAdminById(newUserID);
            String comfirmPwd = admin.getPassword();
            
            if((newPwd).equals(comfirmPwd)){
                return true;
            }
            else{
                return false;
            }
    	}
    	else {
    	    return false;
    	}
    	
    }
}
