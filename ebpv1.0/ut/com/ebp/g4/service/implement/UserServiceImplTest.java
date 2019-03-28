
package com.ebp.g4.service.implement;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

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
import com.ebp.g4.service.beans.*;
import com.ebp.g4.service.implement.UserServiceImpl;
import junit.framework.TestCase;

public class UserServiceImplTest extends TestCase {
	
	UserServiceImpl usi = new UserServiceImpl();
	AdminIntf adminDao = DaoFactory.getAdminDao();
	SellerIntf      sellerDao = DaoFactory.getSellerDao();
	UserIntf	userDao =DaoFactory.getUserDao();
	ShopIntf	shopDao = DaoFactory.getShopDao();
	ShopTypeIntf	shopTypeDao = DaoFactory.getShopTypeDao();
	LogCompanyIntf logCompanyDao = DaoFactory.getLogCompanyDao();
	protected void setUp() throws Exception
    {
        User newuser = new User();
        
        newuser.setId("u00");
        
        userDao.addUser(newuser);
    }

    protected void tearDown() throws Exception
    {
        userDao.deleteUser("u00");
    }
	public void testGetAllBuyers() {
		List<Buyer> BuyerList = new ArrayList<Buyer>();
        
		List<User> buyers = userDao.selectAllUser();
		for(User buyer : buyers){
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
		assertEquals(true, usi.getAllBuyers().toString().equals(BuyerList.toString()));
	}

	public void testGetBuyerByAccount() {
		Buyer gb = new Buyer();
		String account="u2";
		User user = userDao.selectUserById(account);      
        String buyerid = user.getId();
        String nickName=  user.getNickname();
 	    String phone = 	user.getPhone();
 	    gb.setNickName(nickName);
 	    gb.setPhone(phone);
 	    gb.setAccount(buyerid);
		assertEquals(true, usi.getBuyerByAccount("u2").equals(gb));
	}

	public void testGetAllSellers() {
		List<Store> SellerList = new ArrayList<Store>();

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
        
		assertEquals(true, usi.getAllSellers().toString().equals(SellerList.toString()));

	}
	public void testGetAllLogCompanys(){
    	List<logCompanys> logCompanyList = new ArrayList<logCompanys>();
    	List<LogCompany> logCompanys = logCompanyDao.selectAllLogCompany();
    	for(LogCompany logCompany:logCompanys){
    		logCompanys e = new logCompanys();
    		LogCompany LogCompany = logCompanyDao.selectLogCompany(logCompany.getId());
    		String logCompanyName = LogCompany.getName();
    		String logCompanyId = LogCompany.getId();
    		e.setCompanyId(logCompanyId);
    		e.setCompanyName(logCompanyName);
    		logCompanyList.add(e);
    	}
    	assertEquals(true, usi.getAllLogCompanys().toString().equals(logCompanyList.toString()));
	}
	public void testgetAllShopTypes(){
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
    
    	assertEquals(true, usi.getAllShopTypes().toString().equals(shopTypeList.toString()));
    	
    }
	public void testGetSellerByAccount() {
		Seller gs = new Seller();
		String account="u2";
    	com.ebp.g4.dao.beans.Seller seller = sellerDao.selectSellerById(account);
    	String sellerid = seller.getShopid();
    	Shop shop = shopDao.selectShopById(sellerid);
    	String storeName = shop.getName();
    	String storeType = shop.getType();
    	gs.setAccount(sellerid);
    	gs.setStoreName(storeName);
    	gs.setStoreType(storeType);
		assertEquals(true, usi.getSellerByAccount("u2").equals(gs));
	}

	public void testGetUserInfoByAccount() {
		UserInfo gu = new UserInfo();
		String account = "u2";
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
		assertEquals(true, usi.getUserInfoByAccount("u2").equals(gu));
	}

	public void testModifyUserInfoByAccount() {
		UserInfo newUserInfo = new UserInfo();
		assertEquals(true, usi.modifyUserInfoByAccount("u1", newUserInfo));
	}

	public void testVerifyLogInData() {
		LogIn newLogInData = new LogIn();
		
		assertEquals(false, usi.verifyLogInData(newLogInData));
		
		LogIn login = new LogIn();
		login.setUserID("u1");
		login.setPassword("123321");
		
		assertEquals(true, usi.verifyLogInData(login));
		
		login.setPassword("u00");
		login.setUserID("u1");
		assertEquals(false, usi.verifyLogInData(login));
		
		login.setUserID("uuu");
		login.setPassword("ss");
	    assertEquals(false, usi.verifyLogInData(login));
	    
	    login.setUserID("uuuu");
	    login.setPassword("123321");
	    assertEquals(false, usi.verifyLogInData(login));
	}

	public void testVerifyRegisterData() {
		Register register = new Register();
		
		assertEquals(true, usi.verifyRegisterData(register));
		
		register.setAccount("u1");
		assertEquals(false, usi.verifyRegisterData(register));
	}

	public void testVerifyPwdModification() {
		PwdModify newPwdModifyObject = new  PwdModify();
		assertEquals(false, usi.verifyPwdModification("u1", newPwdModifyObject));
		
		newPwdModifyObject.setUserID("u00");
		newPwdModifyObject.setOriginalPassword("");
		newPwdModifyObject.setNewPassword("111");
		newPwdModifyObject.setConfirmNewPassword("111");
		assertEquals(true, usi.verifyPwdModification("u00", newPwdModifyObject));
	}

	public void testHasStore() {
		User newUser = new User();
		String userAccount = newUser.getId();
		assertEquals(false, usi.hasStore(userAccount));
		assertEquals(true, usi.hasStore("u2"));
	}

	
	public void testPutStoreIntoDataBase() {
		Store newStore= new Store();
		
		assertEquals(false, usi.putStoreIntoDataBase("u1", newStore));
		
		newStore.setBankAccount("6666666666666666666");
		
		assertEquals(true, usi.putStoreIntoDataBase("u1", newStore));
		
		assertEquals(false, usi.putStoreIntoDataBase("", newStore));
	}

	public void testGetStoreByAccount() {
		Store gst = new Store();
		String account="u2";
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
		assertEquals(true, usi.getStoreByAccount("u2").equals(gst));
	}

	public void testModifyStoreByAccount() {	
		Store newStore = new Store();
		assertEquals(true, usi.modifyStoreByAccount("u2", newStore));
	}
	@Test
	public void testGetBuyersPropertyNames() {
		 List<String> itemNames = new ArrayList<String>();
	        
	        itemNames.add("昵称");
	        itemNames.add("账号");
	        itemNames.add("手机号");
	        assertEquals(true, usi.getBuyersPropertyNames().equals(itemNames));
	}

	@Test
	public void testGetSellersPropertyNames() {
		 List<String> itemNames = new ArrayList<String>();
	        
	        itemNames.add("昵称");
	        itemNames.add("账号");
	        itemNames.add("手机号");
	        itemNames.add("店铺名称");
	        itemNames.add("店铺类型");
	        assertEquals(true, usi.getSellersPropertyNames().equals(itemNames));
	}
	@Test
	public void testVerifyAdminLogInData() {
		LogIn newLogInData1 = new LogIn();
		newLogInData1.setUserID("a1");
		newLogInData1.setPassword("admin123");
		LogIn lg = new LogIn();
		assertEquals(false, usi.verifyAdminLogInData(lg));
		assertEquals(true, usi.verifyAdminLogInData(newLogInData1));
		newLogInData1.setPassword("22222");
	    assertEquals(false, usi.verifyAdminLogInData(newLogInData1));

	}
	
	public void testGetShopId(){
	    String account = "u2";
	    com.ebp.g4.dao.beans.Seller s = sellerDao.selectSellerById(account);
	    String id = s.getShopid();
	    assertEquals(true, usi.getShopId(account).equals(id));
	    assertEquals(false, usi.getShopId("u1").equals(id));
	}

}