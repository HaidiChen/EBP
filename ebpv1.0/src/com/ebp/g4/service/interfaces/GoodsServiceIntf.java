/**
 * 接口名：GoodsServiceIntf
 * 功能：提供和商品宝贝相关的方法
 */

package com.ebp.g4.service.interfaces;

import java.util.List;

import com.ebp.g4.dao.beans.GoodsType;
import com.ebp.g4.service.beans.CartGoods;
import com.ebp.g4.service.beans.GoodsCmt;
import com.ebp.g4.service.beans.GoodsDetail;
import com.ebp.g4.service.beans.GoodsSales;
import com.ebp.g4.service.beans.MyGoods;

public interface GoodsServiceIntf
{
    /**
     * 方法名：createCartGoodsByName
     * 参数1：String goodsName(商品名称)
     * 参数2:String accountID(用户编号)
     * 参数3：int goodsNumber（商品数量）
     * 返回值：CartGoods
     * 功能：生成购物车
     */    
    boolean createCartGoodsByName(String goodsName,String accountID,int goodsNumber);
    
    /**
     * 方法名：getCartGoodsByName
     * 参数1：String goodsName(商品名称)
     * 参数2:String accountID(用户账号)
     * 返回值：CartGoods
     * 功能：获取购物车内商品详情
     */    
    CartGoods getCartGoodsByName(String goodsName,String accountID);
    
    /**
     * 方法名：getAllCartGoods
     * 参数1： 参数1:String accountID(用户账号)
     * 返回值：List<CartGoods>
     * 功能：获取购物车内所有商品
     */    
    List<CartGoods> getAllCartGoods(String accountID);
    
    /**
     * 方法名：deleteCartGoodsByName
     * 参数1：String goodsName(商品名称)
     * 参数2:String accountID(用户账号)
     * 返回值：boolean
     * 功能：删除购物车内所选商品
     */
    boolean deleteCartGoodsByName(String goodsName,String accountID);
    
    /**
     * 方法名：putCommentIntoDataBase
     * 参数1：GoodsCmt newGoodsCmt(商品评论)
     * 返回值：boolean
     * 功能：将商品评论写入数据库
     */
    boolean putCommentIntoDataBase(GoodsCmt newGoodsCmt);
    
    /**
     * 方法名：getGoodCommentByGoodsName
     * 参数1：String goodsName(商品名称)
     * 参数2：goodsType（商品类型）
     * 返回值：List<GoodsCmt>
     * 功能：获取某商品的好评或差评
     */
    List<GoodsCmt> getGoodCommentByGoodsName(String goodsName,String goodsType);
    
    /**
     * 方法名：getAllCommentByGoodsName
     * 参数1：String goodsName(商品名称)
     * 返回值：List<GoodsCmt>
     * 功能：获取某商品的全部评论
     */
    List<GoodsCmt> getAllCommentByGoodsName(String goodsName);
    /**
     * 方法名：getGoodsDetailByGoodsName
     * 参数1：String goodsName(商品名称)
     * 返回值：GoodsDetail
     * 功能：获取某商品详情
     */
    GoodsDetail getGoodsDetailByGoodsName(String goodsName);
    
    /**
     * 方法名：getAllGoodsSalesInfo
     * 参数1：String accountID(用户编号）
     * 返回值：List<GoodsSales>
     * 功能：获取所有商品销售情况
     */
    List<GoodsSales> getAllGoodsSalesInfo(String accountID);
    
    /**
     * 方法名：getCommentAmountByGoodsName
     * 参数1：String goodsName(商品名称）
     * 返回值：int
     * 功能：获取某商品的评论数
     */
    int getCommentAmountByGoodsName(String goodsName);
    
    /**
     * 方法名：putMyGoodsIntoDataBase
     * 参数1：MyGoods newMyGoods(店铺上架商品详情）
     * 参数2：String accountID（用户编号）
     * 返回值：boolean
     * 功能：判断商品是否成功上架，写入数据库
     */
    boolean putMyGoodsIntoDataBase(MyGoods newMyGoods,String accountID);
    
    /**
     * 方法名：getMyGoodsByGoodsName
     * 参数1：String goodsName(商品名称）
     * 返回值：MyGoods
     * 功能：店铺获取自家商品详情
     */
    MyGoods getMyGoodsByGoodsName(String goodsName);
    
    /**
     * 方法名：updateMyGoodsByGoodsName
     * 参数1：MyGoods newMyGoods(店铺上架商品详情）
     * 参数2：String accountID（用户编号）
     * 返回值：boolean
     * 功能：修改商品详情
     */
    boolean updateMyGoodsByGoodsName(MyGoods newMyGoods,String accountID);
    
    /**
     * 方法名：deleteMyGoodsByGoodsName
     * 参数1：String goodsName(商品名称）
     * 返回值：boolean
     * 功能：店铺删除自家某件商品
     */
    boolean deleteMyGoodsByGoodsName(String goodsName);
    
    /**
     * 方法名：getAllMyGoods
     * 参数1：String accountID（用户编号）
     * 返回值：List<MyGoods>
     * 功能：店铺获取自家所有商品名单
     */
    List<MyGoods> getAllMyGoods(String accountID);
    
    /**
     * 方法名：getGoodsCommentPropertyNames
     * 参数：无
     * 返回值：List<String>
     * 功能：获取商品评论表格的每一列的列名
     */
    List<String> getGoodsCommentPropertyNames();
    
    /**
     * 方法名：getGoodsType
     * 参数：无
     * 返回值：List<GoodsType>
     * 功能：获取商品类型列表
     */
    List<GoodsType> getGoodsType();
    
    /**
     * 方法名：getCartPropertyNames
     * 参数：无
     * 返回值：List<String>
     * 功能：获取购物车表格的每一列的列名
     */
    List<String> getCartPropertyNames();
    
    /**
     * 方法名：getMyGoodsPropertyNames
     * 参数：无
     * 返回值：List<String>
     * 功能：获取我的宝贝表格的每一列的列名
     */
    List<String> getMyGoodsPropertyNames();
    
    /**
     * 方法名：getGoodsSalesPropertyNames
     * 参数：无
     * 返回值：List<String>
     * 功能：获取商品销售情况表格的每一列的列名
     */
    List<String> getGoodsSalesPropertyNames();
    
    /**
     * 方法名：getChangeMyGoodsPropertyNames
     * 参数：无
     * 返回值：List<String>
     * 功能：获取修改宝贝信息列表的每一行的行名
     */
    List<String> getChangeMyGoodsPropertyNames();
}
