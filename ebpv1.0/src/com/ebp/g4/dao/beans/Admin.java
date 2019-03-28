package com.ebp.g4.dao.beans;

public class Admin
{
    public String id;  //管理员账号
    public String password; //管理员密码
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    @Override
    public String toString()
    {
        return "Admin [id=" + id + ", password=" + password + "]";
    }
    

}
