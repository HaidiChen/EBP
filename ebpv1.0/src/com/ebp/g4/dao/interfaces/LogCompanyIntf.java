package com.ebp.g4.dao.interfaces;

import java.util.List;

import com.ebp.g4.dao.beans.LogCompany;

public interface LogCompanyIntf
{
    List<LogCompany> selectAllLogCompany( );//查找物理公司信息，以列表的形式返回所有信息
    LogCompany selectLogCompany(String id); //通过物流id,返回物流公司信息

}
