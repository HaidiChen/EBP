package com.ebp.g4.dao.interfaces;

import java.util.List;

import com.ebp.g4.dao.beans.Category;

public interface CategoryIntf
{
    
    List<Category> selectAllCategory( );//查找菜单，以列表的形式返回所有信息

}
