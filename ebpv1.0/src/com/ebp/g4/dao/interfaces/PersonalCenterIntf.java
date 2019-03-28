package com.ebp.g4.dao.interfaces;

import java.util.List;

import com.ebp.g4.dao.beans.PersonalCenter;

public interface PersonalCenterIntf
{
    List<PersonalCenter> selectAllPersonalCenter( );//查找个人中心，以列表的形式返回所有信息

}
