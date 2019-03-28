/**
 * 类名：MenuList
 * 对应界面内容：主界面的菜单栏所需的菜单名称及子菜单名
 */

package com.ebp.g4.service.beans;

import java.util.List;
import java.util.Map;

public class Menus
{
    // 菜单名，String为主菜单名，String[]为主菜单下的子菜单名列表
    private Map<String, List<String>> menus;

    public Map<String, List<String>> getMenus()
    {
        return menus;
    }

    public void setMenus(Map<String, List<String>> menus)
    {
        this.menus = menus;
    }

    @Override
    public String toString()
    {
        return "Menus [menus=" + menus + "]";
    }

    @Override
    public boolean equals(Object obj)
    {
        Menus mu = (Menus)obj;
        // TODO Auto-generated method stub
        if ((this.menus == null ? "" : this.menus).equals(mu.menus)){
            return true;
        }
        else {
            return false;
        }
    }
}
