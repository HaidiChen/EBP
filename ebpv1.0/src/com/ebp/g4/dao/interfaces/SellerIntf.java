package com.ebp.g4.dao.interfaces;

import java.util.List;

import com.ebp.g4.dao.beans.Seller;

public interface SellerIntf
{
    boolean selectSeller(String id);//查询卖家是否存在
    boolean deleteSeller(String id);//删除卖家
    boolean addSeller(Seller seller);//添加卖家
    boolean updateSeller(Seller seller);//更新卖家信息
    Seller selectSellerById(String id);//通过id查找卖家，以对象的形式返回信息
    List<Seller> selectAllSeller( );//查找卖家，以列表的形式返回所有信息

}
