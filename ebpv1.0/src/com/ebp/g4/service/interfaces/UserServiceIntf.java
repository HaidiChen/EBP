/**
 * 接口名：UserServiceIntf
 * 功能：提供和用户相关的方法
 */

package com.ebp.g4.service.interfaces;

import java.util.List;

import com.ebp.g4.dao.beans.LogCompany;
import com.ebp.g4.dao.beans.Shop;
import com.ebp.g4.dao.beans.ShopType;
import com.ebp.g4.service.beans.Buyer;
import com.ebp.g4.service.beans.LogIn;
import com.ebp.g4.service.beans.PwdModify;
import com.ebp.g4.service.beans.Register;
import com.ebp.g4.service.beans.Seller;
import com.ebp.g4.service.beans.Store;
import com.ebp.g4.service.beans.UserInfo;
import com.ebp.g4.service.beans.logCompanys;
import com.ebp.g4.service.beans.shopType;

public interface UserServiceIntf
{
	/**
     * 方法名：getAllBuyers
     * 参数：无
     * 返回值：List<Buyer>
     * 功能：获取所有的买家信息
     */
    List<Buyer> getAllBuyers();
    /**
     * 方法名：getBuyerByAccount
     * 参数：String account
     * 返回值：Buyer
     * 功能：通过账号获取买家信息
     */
    Buyer getBuyerByAccount(String account);
    /**
     * 方法名：getAllSellers
     * 参数：无
     * 返回值：Seller
     * 功能：获取卖家信息
     */
    List<Store> getAllSellers();
    /**
     * 方法名：getSellerByAccount
     * 参数：String account
     * 返回值：Seller
     * 功能：通过账号获取卖家信息
     */
    Seller getSellerByAccount(String account);
    /**
     * 方法名：getUserInfoByAccount
     * 参数：String account
     * 返回值：UserInfo
     * 功能：通过账号获取用户详细信息
     */
    UserInfo getUserInfoByAccount(String account);
    /**
     * 方法名：modifyUserInfoByAccount
     * 参数1：String account
     * 参数2：UserInfo newUserInfo
     * 返回值：boolean
     * 功能：判断用户是否修改信息
     */
    boolean modifyUserInfoByAccount(String account, UserInfo newUser);
    /**
     * 方法名：verifyLogInData
     * 参数：LogIn logInData
     * 返回值：boolean
     * 功能：判断用户是否登录
     */
    boolean verifyLogInData(LogIn logInData);
    /**
     * 方法名：verifyRegisterData
     * 参数：Register register
     * 返回值：Register
     * 功能：判断用户注册信息是否合法
     */
    boolean verifyRegisterData(Register register);
    /**
     * 方法名：verifyPwdModification
     * 参数：PwdModify newPwdModifyObject
     * 返回值：boolean
     * 功能：判断用户
     */
    boolean verifyPwdModification(String account,PwdModify newPwdModifyObject);
    /**
     * 方法名：hasStore
     * 参数：String userAccount
     * 返回值：boolean
     * 功能：判断用户是否有店铺
     */
    boolean hasStore(String userAccount);
    /**
     * 方法名：hasStore
     * 参数：LogIn logInData
     * 返回值：boolean
     * 功能：管理员登陆
     */
    boolean verifyAdminLogInData(LogIn logInData);
    /**
     * 方法名：putStoreIntoDataBase
     * 参数1：String account
     * 参数2：Store newStore
     * 返回值：Buyer
     * 功能：判断用户注册店铺信息
     */
    boolean putStoreIntoDataBase(String account, Store newStore);
    /**
     * 方法名：getStoreByAccount
     * 参数：String account
     * 返回值：Store
     * 功能：通过账号获取店铺信息
     */
    Store getStoreByAccount(String account);
    /**
     * 方法名：modifyStoreByAccount
     * 参数：Store newStore
     * 返回值：boolean
     * 功能：判断修改店铺信息是否合法
     */
    boolean modifyStoreByAccount(String account ,Store newStore);
    /**
     * 方法名：getSellersPropertyNames
     * 参数：无
     * 返回值：List<String>
     * 功能：获取卖家的每一列的列名
     */
    List<String> getSellersPropertyNames();
    /**
     * 方法名：getBuyersPropertyNames
     * 参数：无
     * 返回值：List<String>
     * 功能：获取买家的每一列的列名
     */
    List<String> getBuyersPropertyNames();
    /**
     * 方法名：getAllLogCompanys
     * 参数：无
     * 返回值：LogCompany
     * 功能：获取所有物流公司信息
     */
    List<logCompanys> getAllLogCompanys();
    /**
     * 方法名：getAllShopTypes
     * 参数：无
     * 返回值：LogCompany
     * 功能：获取所有商店类型
     */
    List<shopType> getAllShopTypes();
    /**
     * 方法名：getShopId
     * 参数：account
     * 返回值：shop
     * 功能：获取商店ID
     */
    String getShopId(String account);
}
