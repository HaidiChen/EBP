/**
 * 接口名：MainFrameServiceIntf
 * 功能：提供主界面相关的方法
 */

package com.ebp.g4.service.interfaces;

import java.util.List;

import com.ebp.g4.service.beans.Classification;
import com.ebp.g4.service.beans.MainFrameGoods;
import com.ebp.g4.service.beans.Menus;
import com.ebp.g4.service.beans.Sort;
import com.ebp.g4.service.implement.ServiceFactory;

public interface MainFrameServiceIntf {
    
    /**
     * 方法名：getMenuList
     * 参数：无
     * 返回值：MenuList
     * 功能：获取菜单栏的菜单信息
     */

	/*Menus getMenuList();*/
	
	/**
     * 方法名：getClassificationList
     * 参数：无
     * 返回值：Classification
     * 功能：获取分类的类别名
     */
	Classification getClassificationList();
	
	/**
     * 方法名：getAllGoodsToBeShown
     * 参数：无
     * 返回值：List<MainFrameGoods>
     * 功能：获取所有主界面需要的商品信息列表
     */
	List<MainFrameGoods> getAllGoodsToBeShown();
	
	/**
     * 方法名：getSortingNames
     * 参数：无
     * 返回值：Sorting
     * 功能：获取排序下拉框的排序名目
     */
	Sort getSortingNames();
	
	/**
     * 方法名：getGoodsPropertyNames
     * 参数：无
     * 返回值：List<String>
     * 功能：获取主界面商品表格的每一列的列名
     */
    List<String> getGoodsPropertyNames();
    
    /**
     * 方法名：getGoodsByClassification
     * 参数：String classificationName
     * 返回值：List<MainFrameGoods>
     * 功能：选择特定分类的商品
     */
    List<MainFrameGoods> getGoodsByClassification(String classificationName);
    
    /**
     * 方法名：getGoodsBySorting
     * 参数：String sortingName, String searchContent
     * 返回值：List<MainFrameGoods>
     * 功能：按sortingName搜索商品
     */
    List<MainFrameGoods> getGoodsBySorting(String sortingName, String searchContent);
}
