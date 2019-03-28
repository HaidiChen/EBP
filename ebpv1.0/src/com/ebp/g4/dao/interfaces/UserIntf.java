package com.ebp.g4.dao.interfaces;

import java.util.List;

import com.ebp.g4.dao.beans.User;

public interface UserIntf
{
    boolean selectUser(String id);//查询用户是否存在
    boolean deleteUser(String id);//删除用户
    boolean addUser(User user);//添加用户
    boolean updateUser(User user);//更新用户信息
    User selectUserById(String id);//通过id查找用户，以对象的形式返回信息
    User selectUserByNickname(String nickname);//通过id查找用户，以对象的形式返回信息
    List<User> selectAllUser( );//查找用户，以列表的形式返回所有信息

}
