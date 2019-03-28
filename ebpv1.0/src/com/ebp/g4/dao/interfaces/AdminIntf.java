package com.ebp.g4.dao.interfaces;
import java.util.List;


import com.ebp.g4.dao.beans.Admin;


public interface AdminIntf
{
    boolean selectAdmin(String id);//查询管理员是否存在
    boolean deleteAdmin(String id);//删除管理员
    boolean addAdmin( Admin admin);//添加管理员
    boolean updateAdmin(Admin admin );//更新管理员信息
    Admin selectAdminById(String id);//通过ID查找管理员，以对象的形式返回信息
    List<Admin> selectAllAdmin( );//查找管理员，以列表形式返回所有信息

}
