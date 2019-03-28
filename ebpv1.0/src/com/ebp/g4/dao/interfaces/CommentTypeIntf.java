package com.ebp.g4.dao.interfaces;

import java.util.List;

import com.ebp.g4.dao.beans.CommentType;

public interface CommentTypeIntf
{
   
    List<CommentType> selectAllCommentType( );//查找评论类型信息，以列表的形式返回所有信息

}
