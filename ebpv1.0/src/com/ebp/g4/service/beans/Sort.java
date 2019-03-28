/**
 * 类名：Sorting
 * 对应界面内容：主界面排序下拉框所需的排序名目
 */

package com.ebp.g4.service.beans;

import java.util.List;

public class Sort
{
    private List<String> names;     // 分类的类名列表

    public List<String> getNames()
    {
        return names;
    }

    public void setNames(List<String> names)
    {
        this.names = names;
    }

    @Override
    public boolean equals(Object obj)
    {
        // TODO Auto-generated method stub
        if ((this.names == null ? "" : this.names).equals(((Sort)obj).names)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString()
    {
        return "Sort [names=" + names + "]";
    }
}
