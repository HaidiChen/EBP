package com.ebp.g4.service.implement;

import java.util.ArrayList;
import java.util.List;

import com.ebp.g4.dao.beans.Cart;
import com.ebp.g4.dao.beans.Comment;
import com.ebp.g4.dao.beans.Goods;
import com.ebp.g4.dao.beans.GoodsType;
import com.ebp.g4.dao.beans.Order;
import com.ebp.g4.dao.beans.Seller;
import com.ebp.g4.dao.beans.Shop;
import com.ebp.g4.dao.implement.DaoFactory;
import com.ebp.g4.dao.interfaces.CartIntf;
import com.ebp.g4.dao.interfaces.CommentIntf;
import com.ebp.g4.dao.interfaces.GoodsIntf;
import com.ebp.g4.dao.interfaces.GoodsTypeIntf;
import com.ebp.g4.dao.interfaces.OrderIntf;
import com.ebp.g4.dao.interfaces.SellerIntf;
import com.ebp.g4.dao.interfaces.ShopIntf;
import com.ebp.g4.service.beans.CartGoods;
import com.ebp.g4.service.beans.GoodsCmt;
import com.ebp.g4.service.beans.GoodsDetail;
import com.ebp.g4.service.beans.GoodsSales;
import com.ebp.g4.service.beans.MyGoods;
import com.ebp.g4.service.implement.GoodsServiceImpl;

import junit.framework.TestCase;

public class GoodsServiceImplTest extends TestCase
{
    GoodsServiceImpl gsi=new GoodsServiceImpl();
    
    GoodsIntf goodsDao=DaoFactory.getGoodsDao();
    CommentIntf commentDao=DaoFactory.getCommentDao();
    CartIntf cartDao=DaoFactory.getCartDao();
    ShopIntf shopDao=DaoFactory.getShopDao();
    GoodsTypeIntf goodsTypeDao=DaoFactory.getGoodsTypeDao();
    OrderIntf orderDao=DaoFactory.getOrderDao();
    SellerIntf sellerDao=DaoFactory.getSellerDao();

    protected void setUp() throws Exception
    {
        Comment newcmt = new Comment();
        
        newcmt.setId("testcmt");
        newcmt.setGoodsid("g1");
        
        commentDao.addComment(newcmt);        
    }

    protected void tearDown() throws Exception
    {
        commentDao.deleteComment("testcmt");
    }

    public void testGetCartGoodsByName()
    {
        String goodsName="榴莲糖";
        String accountID="u1";
        CartGoods cg=new CartGoods();
        Goods cartGoods=goodsDao.selectGoodsByName(goodsName);
        String shopId=cartGoods.getShopid();
        Shop cartShopType=shopDao.selectShopById(shopId);
        String shopName=cartShopType.getName();
        String goodsInfo=cartGoods.getInfo();
        float goodsPrice=Float.parseFloat(cartGoods.getPrice()==null?"0":cartGoods.getPrice());
        String goodsID=cartGoods.getGoodsid();
        Cart cart=cartDao.selectCartById(accountID,goodsID);
        int goodsNumber=Integer.parseInt(cart.getNum()==null?"0":cart.getNum());
        float money=goodsPrice*goodsNumber;
        
        cg.setGoodsName(goodsName);
        cg.setGoodsInfo(goodsInfo);
        cg.setGoodsNumber(goodsNumber);
        cg.setGoodsPrice(goodsPrice);
        cg.setMoney(money);
        cg.setShopName(shopName);

        assertEquals(true,gsi.getCartGoodsByName(goodsName,accountID).equals(cg));
    }

    public void testCreateCartGoodsByName()
    {
        assertEquals(true,gsi.createCartGoodsByName("1","1",1));
    }
    
    public void testGetAllCartGoods()
    {
        String accountID="u1";
        List<CartGoods> cgList=new ArrayList<CartGoods>();
        List<Cart> cartGoods=cartDao.selectAllCart(accountID);
        for(Cart cartGood:cartGoods){
            CartGoods e=new CartGoods();
            String goodID=cartGood.getGoodsid();
            Goods good=goodsDao.selectGoodsById(goodID);
            String shopID=good.getShopid();
            Shop cartShopType=shopDao.selectShopById(""+shopID);
            String shopName=cartShopType.getName();
            e.setShopName(shopName);
            e.setGoodsName(good.getName());
            e.setGoodsInfo(good.getInfo());
            e.setGoodsPrice(Float.parseFloat(good.getPrice()==null?"0":good.getPrice()));
            e.setGoodsNumber(Integer.parseInt(cartGood.getNum()==null?"0":cartGood.getNum()));
            float money=Integer.parseInt(cartGood.getNum())*Float.parseFloat(good.getPrice());
            e.setMoney(money);
            
            cgList.add(e);
        }
        assertEquals(true,gsi.getAllCartGoods(accountID).toString().equals(cgList.toString()));
    }

    public void testDeleteCartGoodsByName()
    {
        assertEquals(true,gsi.deleteCartGoodsByName("1","1"));
    }

    public void testPutCommentIntoDataBase()
    {
        GoodsCmt gc=new GoodsCmt();
        assertEquals(true,gsi.putCommentIntoDataBase(gc));
    }

    public void testGetAllCommentByGoodsName()
    {
        String goodsName="榴莲糖";
        List<GoodsCmt> gcList=new ArrayList<GoodsCmt>();
        Goods goods1=goodsDao.selectGoodsByName(goodsName);
        String goodsId=goods1.getGoodsid();
        List<Comment> comments=commentDao.selectCommentByGoodsId(goodsId);
        for(Comment comment:comments){
            GoodsCmt e=new GoodsCmt();
            e.setCommentType(comment.getType());
            e.setCommentContent(comment.getContent());
            e.setUserName(comment.getUserid());
            String goodsID=comment.getGoodsid();
            Goods goods=goodsDao.selectGoodsById(goodsID);
            e.setGoodsName(goods.getName());
            e.setCommentDate(comment.getTime());
            
            gcList.add(e);
        }

        assertEquals(true,gsi.getAllCommentByGoodsName(goodsName).toString().equals(gcList.toString()));
    }
    
    public void testGetGoodCommentByGoodsName()
    {
        String goodsName="榴莲糖";
        String goodsType="cmntt2";
        List<GoodsCmt> gcList=new ArrayList<GoodsCmt>();
        Goods goods1=goodsDao.selectGoodsByName(goodsName);
        String goodsId=goods1.getGoodsid();
        List<Comment> comments=commentDao.selectCommentByGoodsId(goodsId);
        for(Comment comment : comments)
        {
            GoodsCmt e=new GoodsCmt();
            if (comment.getType() != null){
                if(comment.getType().equalsIgnoreCase(goodsType)){
                e.setCommentType(goodsType);
                e.setCommentContent(comment.getContent());
                e.setUserName(comment.getUserid());
                String goodsID=comment.getGoodsid();
                Goods goods=goodsDao.selectGoodsById(goodsID);
                e.setGoodsName(goods.getName());
                e.setCommentDate(comment.getTime());
                gcList.add(e);
                }
            }
            else {
                
            }
        }
        assertEquals(true,gsi.getGoodCommentByGoodsName(goodsName,goodsType).toString().equals(gcList.toString()));
        assertEquals(false,gsi.getGoodCommentByGoodsName("",goodsType).toString().equals(gcList.toString()));
        assertEquals(false,gsi.getGoodCommentByGoodsName(goodsName,"").toString().equals(gcList.toString()));

    }

    public void testGetGoodsDetailByGoodsName()
    {
        String goodsName="榴莲糖";
        GoodsDetail gd=new GoodsDetail();
        Goods goods=goodsDao.selectGoodsByName(goodsName);
        
        String goodsName1=goods.getName();
        float price=Float.parseFloat(goods.getPrice()==null?"0":goods.getPrice());
        int goodsNumber=Integer.parseInt(goods.getStock()==null?"0":goods.getStock());
        String goodsInfo=goods.getInfo();      
        Object pitcure=goods.getPhoto();
        String goodsID=goods.getGoodsid();
        int sellingNumbers=0;
        List<Order> orders=orderDao.selectAllOrderByGoodsId(goodsID);
        for(Order order:orders)
        {
            int i=Integer.parseInt(order.getNum());
            sellingNumbers+=i;
        } 
        gd.setGoodsName(goodsName1);
        gd.setGoodsInfo(goodsInfo);
        gd.setGoodsNumbers(goodsNumber);
        gd.setGoodsPrice(price);
        gd.setNumbers(1);
        gd.setPitcure(pitcure);
        gd.setSellingNumbers(sellingNumbers);
        
        assertEquals(true,gsi.getGoodsDetailByGoodsName(goodsName).equals(gd));
    }

    public void testGetAllGoodsSalesInfo()
    {
        String accountID="u2";
        List<GoodsSales> gsList=new ArrayList<GoodsSales>();
        List<Order> orders=orderDao.selectAllOrder();
        for(Order order:orders){
            GoodsSales e=new GoodsSales();
            Seller seller=sellerDao.selectSellerById(accountID);
            String shopID=seller.getShopid()==null?"":seller.getShopid();
            String shopId=order.getShopid()==null?"":order.getShopid();
            if(shopID.equalsIgnoreCase(shopId))
            {
            String goodsID=order.getGoodsid();
            Goods goods=goodsDao.selectGoodsById(goodsID);
            e.setGoodsName(goods.getName());
            String goodsTypeID=goods.getTypeid();
            GoodsType goodsType=goodsTypeDao.selectGoodsTypeById(goodsTypeID);
            e.setGoodsType(goodsType.getName());
            e.setGoodsPrice(Float.parseFloat(goods.getPrice()==null?"0":goods.getPrice()));
            e.setGoodsShipping(Float.parseFloat(order.getTransprice()==null?"0":order.getTransprice()));
            e.setOrderNumber(order.getOrderid());
            int goodsSales=0;
            int i=Integer.parseInt(order.getNum());
            goodsSales+=i;
            e.setTotalRevenue((float)goodsSales); 
            e.setOrderDate(order.getTime());
            
            gsList.add(e);
            }
        }
        assertEquals(true,gsi.getAllGoodsSalesInfo(accountID).toString().equals(gsList.toString()));
    }

    public void testGetCommentAmountByGoodsName()
    {
        String goodsName="大衣";
        Goods goods=goodsDao.selectGoodsByName(goodsName);
        String goodsID=goods.getGoodsid();
        List<Comment> comments=commentDao.selectCommentByGoodsId(goodsID);
        int i=comments.size();
        
        assertEquals(i,gsi.getCommentAmountByGoodsName(goodsName));
    }

    public void testPutMyGoodsIntoDataBase()
    {
        MyGoods myGoods=new MyGoods();
        boolean p=true;
        assertEquals(p,gsi.putMyGoodsIntoDataBase(myGoods,"1"));
    }

    public void testGetMyGoodsByGoodsName()
    {
        String goodsName="榴莲糖";
        MyGoods mg=new MyGoods();
        Goods myGoods=goodsDao.selectGoodsByName(goodsName);
        String typeId=myGoods.getTypeid();
        GoodsType myGoodsType=goodsTypeDao.selectGoodsTypeById(""+typeId);
        String goodsType=myGoodsType.getName();
        float goodsPrice=Float.parseFloat(myGoods.getPrice()==null?"0":myGoods.getPrice());
        Goods goods=goodsDao.selectGoodsByName(goodsName);
        String goodsID=goods.getGoodsid();
        int goodsSales=0;
        float goodsShipping=Float.parseFloat(myGoods.getTranprice()==null?"0":myGoods.getTranprice());
        List<Order> orders=orderDao.selectAllOrderByGoodsId(goodsID);
        for(Order order:orders)
        {
            int i=Integer.parseInt(order.getNum());
            goodsSales+=i;
        }        
        long goodsStock=Long.parseLong(myGoods.getStock()==null?"0":myGoods.getStock());
        mg.setGoodsShelfTime(goods.getTime());
        
        mg.setGoodsInfo(goods.getInfo());
        mg.setGoodsName(goodsName);
        mg.setGoodsPrice(goodsPrice);
        mg.setGoodsSales(goodsSales);
        mg.setGoodsStock(goodsStock);
        mg.setGoodsType(goodsType);
        mg.setGoodsCarriage(goodsShipping);
        mg.setPicture(myGoods.getPhoto());
        assertEquals(true,gsi.getMyGoodsByGoodsName(goodsName).equals(mg));
    }
    
    public void testUpdateMyGoodsByGoodsName()
    {
        MyGoods mg=new MyGoods();
        assertEquals(true,gsi.updateMyGoodsByGoodsName(mg,"1"));
    }

    public void testDeleteMyGoodsByGoodsName()
    {
        assertEquals(true,gsi.deleteMyGoodsByGoodsName("1"));
    }

    public void testGetAllMyGoods()
    {
        String accountID="u2";
        List<MyGoods> mgList=new ArrayList<MyGoods>();
        List<Goods> goods=goodsDao.selectAllGoods();
        for(Goods good:goods){
            Seller seller=sellerDao.selectSellerById(accountID);
            String shopID=seller.getShopid()==null?"":seller.getShopid();
            String shopId=good.getShopid()==null?"":good.getShopid();
            if(shopID.equalsIgnoreCase(shopId))
            {
            MyGoods e=new MyGoods();
            e.setGoodsName(good.getName());
            String goodsTypeID=good.getTypeid();            
            GoodsType goodsType=goodsTypeDao.selectGoodsTypeById(goodsTypeID);
            e.setGoodsType(goodsType.getName());
            e.setGoodsPrice(Float.parseFloat(good.getPrice()==null?"0":good.getPrice()));
            int goodsSales=0;
            float goodsShipping =Float.parseFloat(good.getTranprice()==null?"0":good.getTranprice());; 
            List<Order> orders=orderDao.selectAllOrderByGoodsId(good.getGoodsid());
            for(Order order:orders)
            {
                int i=Integer.parseInt(order.getNum());
                goodsSales+=i;
            }  
            e.setGoodsCarriage(goodsShipping);
            e.setGoodsSales(goodsSales);
            e.setGoodsStock(Long.parseLong(good.getStock()==null?"0":good.getStock()));
            e.setGoodsShelfTime(good.getTime());
            
            mgList.add(e);
            }
        }
        assertEquals(true,gsi.getAllMyGoods(accountID).toString().equals(mgList.toString()));
    }
    
    public void testGetGoodsType()
    {
        List<GoodsType> gt=goodsTypeDao.selectAllGoodsType();
        assertEquals(true,gsi.getGoodsType().toString().equals(gt.toString()));
    }
    
    public void testGetGoodsCommentPropertyNames()
    {
        List<String> itemNames = new ArrayList<String>();
        
        itemNames.add("用户名");
        itemNames.add("评论");
        itemNames.add("时间");
        assertEquals(true,gsi.getGoodsCommentPropertyNames().equals(itemNames));
    }
    
    public void testGetCartPropertyNames()
    {
        List<String> itemNames = new ArrayList<String>();
        
        itemNames.add("店铺");
        itemNames.add("商品名称");
        itemNames.add("商品信息");
        itemNames.add("单价");
        itemNames.add("数量");
        itemNames.add("金额");
        assertEquals(true,gsi.getCartPropertyNames().equals(itemNames));
    }
    
    public void testGetMyGoodsPropertyNames()
    {
        List<String> itemNames = new ArrayList<String>();
        
        itemNames.add("名称");
        itemNames.add("类别");
        itemNames.add("价格");
        itemNames.add("运费");
        itemNames.add("销售");
        itemNames.add("库存");
        itemNames.add("上架时间");
        assertEquals(true,gsi.getMyGoodsPropertyNames().equals(itemNames));
    }
    
    public void testGetGoodsSalesPropertyNames()
    {
        List<String> itemNames = new ArrayList<String>();
        
        itemNames.add("名称");
        itemNames.add("类别");
        itemNames.add("价格");
        itemNames.add("运费");
        itemNames.add("订单号");
        itemNames.add("交易时间");
        itemNames.add("总销售量");
        
        assertEquals(true,gsi.getGoodsSalesPropertyNames().equals(itemNames));
    }
    
    public void testGetChangeMyGoodsPropertyNames()
    {
        List<String> itemNames = new ArrayList<String>();
        
        itemNames.add("名称");
        itemNames.add("类别");
        itemNames.add("价格");
        itemNames.add("运费");
        itemNames.add("图片");
        itemNames.add("库存");
        itemNames.add("宝贝介绍");
        assertEquals(true,gsi.getChangeMyGoodsPropertyNames().equals(itemNames));
    }

}
