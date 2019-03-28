package com.ebp.g4.dao.interfaces;

import java.util.List;

import com.ebp.g4.dao.beans.Comment;

public interface CommentIntf
{
    boolean selectComment(String id);//查询评论是否存在
    boolean deleteComment(String id);//删除评论
    boolean addComment(Comment comment);//添加评论
    boolean updateComment(Comment comment);//更新评论信息
    Comment selectCommentById(String id);//通过id查找评论，以对象的形式返回信息
    List<Comment> selectAllComment( );//查找评论，以列表的形式返回所有信息
    List<Comment> selectCommentByGoodsId(String goodsid);//通过goodsid查找评论，以对象的形式返回信息
}
