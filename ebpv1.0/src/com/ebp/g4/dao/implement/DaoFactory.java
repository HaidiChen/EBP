package com.ebp.g4.dao.implement;

import com.ebp.g4.dao.interfaces.MenuListIntf;
import com.ebp.g4.dao.interfaces.OrderStatusIntf;
import com.ebp.g4.dao.interfaces.SortingIntf;

public class DaoFactory
{
    public static AdminImpl getAdminDao()
    {
        return new AdminImpl();
    }

    public static CartImpl getCartDao()
    {
        return new CartImpl();
    }

    public static CategoryImpl getCategoryDao()
    {
        return new CategoryImpl();
    }

    public static CommentImpl getCommentDao()
    {
        return new CommentImpl();
    }

    public static CommentTypeImpl getCommentTypeDao()
    {
        return new CommentTypeImpl();
    }

    public static GoodsImpl getGoodsDao()
    {
        return new GoodsImpl();
    }

    public static GoodsTypeImpl getGoodsTypeDao()
    {
        return new GoodsTypeImpl();
    }

    public static LogCompanyImpl getLogCompanyDao()
    {
        return new LogCompanyImpl();
    }

    public static OrderImpl getOrderDao()
    {
        return new OrderImpl();
    }

    public static OrderStatusImpl getOrderStatementDao()
    {
        return new OrderStatusImpl();
    }

    public static PersonalCenterImpl getPersonalDao()
    {
        return new PersonalCenterImpl();
    }

    public static SellerCenterImpl getSellerCenterDao()
    {
        return new SellerCenterImpl();
    }

    public static SellerImpl getSellerDao()
    {
        return new SellerImpl();
    }

    public static ShopImpl getShopDao()
    {
        return new ShopImpl();
    }

    public static UserImpl getUserDao()
    {
        return new UserImpl();
    }

    public static MenuListImpl getMenuListDao()
    {
        // TODO Auto-generated method stub
        return new MenuListImpl();
    }

    public static OrderStatusImpl getOrderStatusDao()
    {
        // TODO Auto-generated method stub
        return new OrderStatusImpl();
    }

    public static SortingImpl getSortListDao()
    {
        // TODO Auto-generated method stub
        return new SortingImpl();
    }
    
    public static ShopTypeImpl getShopTypeDao(){
        return new ShopTypeImpl();
    }
}
