package com.ebp.g4.dao.interfaces;

import java.util.List;

import com.ebp.g4.dao.beans.SellerCenter;

public interface SellerCenterIntf
{
    List<SellerCenter> selectAllSellerCenter( );//查找卖家中心，以列表的形式返回所有信息

}
