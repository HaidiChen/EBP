/**
 * 类名：Classification
 * 对应界面内容：主界面的分类列表需要的类别名
 */

package com.ebp.g4.service.beans;

import java.util.List;

public class Classification
{
    private List<String> names; // 分类名目列表

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
        if (this.names.equals(((Classification)obj).names)){
            return true;
        }
        else {
            return false;
        }
    }
}
