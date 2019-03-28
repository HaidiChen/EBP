/**
 * 类名：GoodsCmt
 * 对应界面内容：商品评价
 */

package com.ebp.g4.service.beans;

public class GoodsCmt
{
    private String commentType;     // 评价类型（好，差）

    private String commentContent;  // 评价内容

    private String userName;        // 评价的用户

    private String commentDate;       // 评价时间

    private String goodsName;       // 评价的商品名称

    @Override
    public boolean equals(Object obj)
    {
        GoodsCmt gc=(GoodsCmt)obj;
        if(     this.commentContent.equalsIgnoreCase(gc.commentContent)&&
                this.commentDate==gc.commentDate&&
                this.commentType.equalsIgnoreCase(gc.commentType)&&
                this.goodsName.equalsIgnoreCase(gc.goodsName)&&
                this.userName.equalsIgnoreCase(gc.userName)
                )
        {
            return true;
        }
        else{
            return false;
        }
        // TODO Auto-generated method stub
    }
    
    

    @Override
    public String toString()
    {
        return "GoodsCmt [commentType=" + commentType + ", commentContent="
                + commentContent + ", userName=" + userName + ", commentDate="
                + commentDate + ", goodsName=" + goodsName + "]";
    }



    public String getGoodsName()
    {
        return goodsName;
    }

    public void setGoodsName(String goodsName)
    {
        this.goodsName = goodsName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getCommentDate()
    {
        return commentDate;
    }

    public void setCommentDate(String commentDate)
    {
        this.commentDate = commentDate;
    }

    public String getCommentType()
    {
        return commentType;
    }

    public void setCommentType(String commentType)
    {
        this.commentType = commentType;
    }

    public String getCommentContent()
    {
        return commentContent;
    }

    public void setCommentContent(String commentContent)
    {
        this.commentContent = commentContent;
    }

}
