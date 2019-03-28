package com.ebp.g4.dao.interfaces;

import java.util.List;

import com.ebp.g4.dao.beans.GoodsType;

public interface GoodsTypeIntf
{
    List<GoodsType> selectAllGoodsType( );//查找商品种类信息，以列表的形式返回所有信息

    GoodsType selectGoodsTypeById(String typeid); //通过typeid返回type对象
    
    GoodsType selectGoodsTypeByName(String name); //通过name返回type对象

}
