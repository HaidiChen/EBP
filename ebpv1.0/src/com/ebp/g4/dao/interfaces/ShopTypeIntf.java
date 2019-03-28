package com.ebp.g4.dao.interfaces;

import java.util.List;

import com.ebp.g4.dao.beans.ShopType;

public interface ShopTypeIntf
{
  List<ShopType> selectAllShopType();   //查询所有的店铺类型
  ShopType selectShopTypeById(String id);  //通过类型id查询店铺信息
  
}
