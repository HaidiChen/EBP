package com.ebp.g4.dao.beans;

public class User
{
    private String id;//用户编号
    private String password;//用户密码
    private String nickname;//用户昵称
	private String photo;//用户头像
    private String address;//用户地址
    private String phone;//用户联系电话
    private String email;//用户邮箱
    public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
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
    public String getPhoto()
    {
        return photo;
    }
    public void setPhoto(String photo)
    {
        this.photo = photo;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getPhone()
    {
        return phone;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    @Override
    public String toString()
    {
        return "User [id=" + id + ", password=" + password + ", photo=" + photo
                + ", address=" + address + ", phone=" + phone + ", email="
                + email + "]";
    }
    
}
