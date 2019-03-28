package com.ebp.g4.service.implement;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
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
import com.ebp.g4.dao.interfaces.CommentTypeIntf;
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
import com.ebp.g4.service.interfaces.GoodsServiceIntf;

class GoodsServiceImpl implements GoodsServiceIntf
{
    GoodsIntf goodsDao=DaoFactory.getGoodsDao();
    CommentIntf commentDao=DaoFactory.getCommentDao();
    GoodsTypeIntf goodsTypeDao=DaoFactory.getGoodsTypeDao();
    ShopIntf shopDao=DaoFactory.getShopDao();
    OrderIntf orderDao=DaoFactory.getOrderDao();
    CommentTypeIntf commentTypeDao=DaoFactory.getCommentTypeDao();
    CartIntf cartDao=DaoFactory.getCartDao();
    SellerIntf sellerDao=DaoFactory.getSellerDao();

    @Override
    public CartGoods getCartGoodsByName(String goodsName,String accountID)
    {
        CartGoods cg=new CartGoods();
        
        /*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而
         * 进行CartGoods的提取，并赋值到cg对象中
         */
        Goods cartGoods=goodsDao.selectGoodsByName(goodsName);
        String shopId=cartGoods.getShopid();
        Shop cartShopType=shopDao.selectShopById(shopId);
        String shopName=cartShopType.getName();
        String goodsInfo=cartGoods.getInfo();
        float goodsPrice=Float.parseFloat(ServiceFactory.NumberToString(cartGoods.getPrice()));
        String goodsID=cartGoods.getGoodsid();
        Cart cart=cartDao.selectCartById(accountID,goodsID);
        int goodsNumber=Integer.parseInt(ServiceFactory.NumberToString(cart.getNum()));
        float money=goodsPrice*goodsNumber;
        
        cg.setGoodsName(goodsName);
        cg.setGoodsInfo(goodsInfo);
        cg.setGoodsNumber(goodsNumber);
        cg.setGoodsPrice(goodsPrice);
        cg.setMoney(money);
        cg.setShopName(shopName);
        
        return cg;
    }

    public boolean createCartGoodsByName(String goodsName,String accountID,int goodsNumber)
    {
        Cart cartGood=new Cart();
        cartGood.setAccountid(accountID);
        Goods goods=goodsDao.selectGoodsByName(goodsName);
        String goodsID=goods.getGoodsid();
        cartGood.setGoodsid(goodsID);
        cartGood.setNum(""+goodsNumber);
        
        return cartDao.addCart(cartGood);
    }
    
    @Override
    public List<CartGoods> getAllCartGoods(String accountID)
    {
        List<CartGoods> cgList=new ArrayList<CartGoods>();
        
        /*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而
         * 进行CartGoods的提取，并赋值到cgList对象中
         */
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
            e.setGoodsPrice(Float.parseFloat(ServiceFactory.NumberToString(good.getPrice())));
            e.setGoodsNumber(Integer.parseInt(ServiceFactory.NumberToString(cartGood.getNum())));
            float money=Integer.parseInt(cartGood.getNum())*Float.parseFloat(good.getPrice());
            e.setMoney(money);
            
            cgList.add(e);
        }

        return cgList;
    }

    @Override
    public boolean deleteCartGoodsByName(String goodsName,String accountID)
    {
        Goods goods=goodsDao.selectGoodsByName(goodsName);
        String goodsID=goods.getGoodsid();

            return cartDao.deleteCart(accountID, goodsID);
    }

    @Override
    public boolean putCommentIntoDataBase(GoodsCmt newGoodsCmt)
    {
        // TODO Auto-generated method stub
        Comment e=new Comment();
        String commentType=newGoodsCmt.getCommentType();
        String commentContent=newGoodsCmt.getCommentContent();
        String userName=newGoodsCmt.getUserName();
        String goodsName=newGoodsCmt.getGoodsName();
        Goods goods=goodsDao.selectGoodsByName(goodsName);
        String goodsID=goods.getGoodsid();
        String commentDate;
        Date date=new Date();
        commentDate=(new SimpleDateFormat("yyyy-MM-dd")).format(date);  
        e.setContent(commentContent);
        e.setGoodsid(goodsID);
        e.setTime(commentDate);
        e.setType(commentType);
        e.setUserid(userName);
        
        return commentDao.addComment(e);
    }

    @Override
    public List<GoodsCmt> getAllCommentByGoodsName(String goodsName)
    {
        List<GoodsCmt> gcList=new ArrayList<GoodsCmt>();
        Goods goods1=goodsDao.selectGoodsByName(goodsName);
        String goodsId=goods1.getGoodsid();
        List<Comment> comments=commentDao.selectCommentByGoodsId(goodsId);
        
        /*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而
         * 进行GoodsCmt的提取，并赋值到gcList对象中
         */
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
        
        return gcList;
    }

    public List<GoodsCmt> getGoodCommentByGoodsName(String goodsName,String goodsType)
    {
        List<GoodsCmt> gcList=new ArrayList<GoodsCmt>();
        Goods goods1=goodsDao.selectGoodsByName(goodsName);
        String goodsId=goods1.getGoodsid();
        List<Comment> comments=commentDao.selectCommentByGoodsId(goodsId);
        for(Comment comment : comments)
        {
            GoodsCmt e=new GoodsCmt();
            if (comment.getType() != ""){
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
        }
        return gcList;
    }
    
    @Override
    public GoodsDetail getGoodsDetailByGoodsName(String goodsName)
    {
        GoodsDetail gd=new GoodsDetail();
        Goods goods=goodsDao.selectGoodsByName(goodsName);
        
        String goodsName1=goods.getName();
        float price=Float.parseFloat(ServiceFactory.NumberToString(goods.getPrice()));
        int goodsNumber=Integer.parseInt(ServiceFactory.NumberToString(goods.getStock()));
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
        /*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而
         * 进行GoodsDetail的提取，并赋值到gd对象中
         */
      
        return gd;
    }

    @Override
    public List<GoodsSales> getAllGoodsSalesInfo(String accountID)
    {
        List<GoodsSales> gsList=new ArrayList<GoodsSales>();
        List<Order> orders=orderDao.selectAllOrder();
        /*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而
         * 进行GoodsSales的提取，并赋值到gsList对象中
         */
        
        for(Order order:orders){
            GoodsSales e=new GoodsSales();
            Seller seller=sellerDao.selectSellerById(accountID);
            String shopID=/*seller.getShopid()==null?"":*/seller.getShopid();
            String shopId=/*order.getShopid()==null?"":*/order.getShopid();
            if(shopID.equalsIgnoreCase(shopId))
            {
            String goodsID=order.getGoodsid();
            Goods goods=goodsDao.selectGoodsById(goodsID);
            e.setGoodsName(goods.getName());
            String goodsTypeID=goods.getTypeid();
            GoodsType goodsType=goodsTypeDao.selectGoodsTypeById(goodsTypeID);
            e.setGoodsType(goodsType.getName());
            e.setGoodsPrice(Float.parseFloat(ServiceFactory.NumberToString(goods.getPrice())));
            e.setGoodsShipping(Float.parseFloat(ServiceFactory.NumberToString(order.getTransprice())));
            e.setOrderNumber(order.getOrderid());
            int goodsSales=0;
            int i=Integer.parseInt(order.getNum());
            goodsSales+=i;
            e.setTotalRevenue((float)goodsSales); 
            e.setOrderDate(order.getTime());
            
            gsList.add(e);
            }
        }
        return gsList;
    }

    @Override
    public int getCommentAmountByGoodsName(String goodsName)
    {
        // TODO Auto-generated method stub
        Goods goods=goodsDao.selectGoodsByName(goodsName);
        String goodsID=goods.getGoodsid();
        List<Comment> comments=commentDao.selectCommentByGoodsId(goodsID);
        int i=comments.size();
        
        return i;
    }

    @Override
    public boolean putMyGoodsIntoDataBase(MyGoods newMyGoods,String accountID)
    {
        // TODO Auto-generated method stub
        Goods e=new Goods();
        String goodsName=newMyGoods.getGoodsName();
        String goodsType=newMyGoods.getGoodsType();
        float goodsPrice=newMyGoods.getGoodsPrice();
        long goodsStock=newMyGoods.getGoodsStock();
        e.setName(goodsName);
        e.setPrice(""+goodsPrice);
        GoodsType goodsType1=goodsTypeDao.selectGoodsTypeByName(goodsType);
        e.setTypeid(goodsType1.getId());
        e.setStock(""+goodsStock);
        e.setInfo(newMyGoods.getGoodsInfo());
        e.setTranprice(""+newMyGoods.getGoodsCarriage());
        e.setPhoto(""+newMyGoods.getPicture());
        Seller sell=sellerDao.selectSellerById(accountID);
        String shopID=sell.getShopid();//haha
        e.setShopid(shopID);
        String commentDate;
        Date date=new Date();
        commentDate=(new SimpleDateFormat("yyyy-MM-dd")).format(date);
        e.setTime(commentDate);
        
        return goodsDao.addGoods(e);
    }

    @Override
    public MyGoods getMyGoodsByGoodsName(String goodsName)
    {
        MyGoods mg=new MyGoods();
        
        /*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而
         * 进行MyGoods的提取，并赋值到mg对象中
         */
        Goods myGoods=goodsDao.selectGoodsByName(goodsName);
        String typeId=myGoods.getTypeid();
        GoodsType myGoodsType=goodsTypeDao.selectGoodsTypeById(typeId);
        String goodsType=myGoodsType.getName();
        float goodsPrice=Float.parseFloat(ServiceFactory.NumberToString(myGoods.getPrice()));
        Goods goods=goodsDao.selectGoodsByName(goodsName);
        String goodsID=goods.getGoodsid();
        int goodsSales=0;
        float goodsShipping=Float.parseFloat(ServiceFactory.NumberToString(myGoods.getTranprice()));
        List<Order> orders=orderDao.selectAllOrderByGoodsId(goodsID);
        for(Order order:orders)
        {
            int i=Integer.parseInt(order.getNum());
            goodsSales+=i;
        }        
        long goodsStock=Long.parseLong(ServiceFactory.NumberToString(myGoods.getStock()));
        mg.setGoodsShelfTime(goods.getTime());
        
        mg.setGoodsInfo(goods.getInfo());
        mg.setGoodsName(goodsName);
        mg.setGoodsPrice(goodsPrice);
        mg.setGoodsSales(goodsSales);
        mg.setGoodsStock(goodsStock);
        mg.setGoodsType(goodsType);
        mg.setGoodsCarriage(goodsShipping);
        mg.setPicture(myGoods.getPhoto());

        return mg;
    }
    
    public boolean updateMyGoodsByGoodsName(MyGoods newMyGoods,String accountID)
    {
        String goodsType=newMyGoods.getGoodsType();
        GoodsType myGoodsType=goodsTypeDao.selectGoodsTypeByName(goodsType);
        Goods goods=goodsDao.selectGoodsByName(newMyGoods.getGoodsName());
        goods.setName(newMyGoods.getGoodsName());
        goods.setPrice(""+newMyGoods.getGoodsPrice());
        goods.setTypeid(myGoodsType.getId());
        goods.setPhoto(""+newMyGoods.getPicture());
        goods.setStock(""+newMyGoods.getGoodsStock());
        goods.setInfo(newMyGoods.getGoodsInfo());
        Seller sell=sellerDao.selectSellerById(accountID);
        String shopID=sell.getShopid();//haha
        goods.setShopid(shopID);
        goods.setTime(newMyGoods.getGoodsShelfTime());
        goods.setTranprice(""+newMyGoods.getGoodsCarriage());
        return goodsDao.updateGoods(goods); 
    }

    @Override
    public boolean deleteMyGoodsByGoodsName(String goodsName)
    {
        // TODO Auto-generated method stub
        Goods goods=goodsDao.selectGoodsByName(goodsName);
        String goodsID=goods.getGoodsid();
        return goodsDao.deleteGoods(goodsID);
    }

    @Override
    public List<MyGoods> getAllMyGoods(String accountID)
    {
        List<MyGoods> mgList=new ArrayList<MyGoods>();
        List<Goods> goods=goodsDao.selectAllGoods();
        
        /*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而
         * 进行MyGoods的提取，并赋值到mgList对象中
         */
        for(Goods good:goods){
            Seller seller=sellerDao.selectSellerById(accountID);
            String shopID=/*seller.getShopid()==null?"":*/seller.getShopid();
            String shopId=/*good.getShopid()==null?"":*/good.getShopid();
            if(shopID.equalsIgnoreCase(shopId))
            {
            MyGoods e=new MyGoods();
            e.setGoodsName(good.getName());
            String goodsTypeID=good.getTypeid();            
            GoodsType goodsType=goodsTypeDao.selectGoodsTypeById(goodsTypeID);
            e.setGoodsType(goodsType.getName());
            e.setGoodsPrice(Float.parseFloat(ServiceFactory.NumberToString(good.getPrice())));
            int goodsSales=0;
            float goodsShipping =Float.parseFloat(ServiceFactory.NumberToString(good.getTranprice()));; 
            List<Order> orders=orderDao.selectAllOrderByGoodsId(good.getGoodsid());
            for(Order order:orders)
            {
                int i=Integer.parseInt(order.getNum());
                goodsSales+=i;
            }  
            e.setGoodsCarriage(goodsShipping);
            e.setGoodsSales(goodsSales);
            e.setGoodsStock(Long.parseLong(ServiceFactory.NumberToString(good.getStock())));
            e.setGoodsShelfTime(good.getTime());
            
            mgList.add(e);
            }
        }

        return mgList;
    }
    
    public List<GoodsType> getGoodsType()
    {
        List<GoodsType> gt=goodsTypeDao.selectAllGoodsType();
        return gt;
    }

    
    public List<String> getGoodsCommentPropertyNames()
    {
        List<String> itemNames = new ArrayList<String>();
        
        itemNames.add("用户名");
        itemNames.add("评论");
        itemNames.add("时间");
        
        return itemNames;
    }
    
    public List<String> getCartPropertyNames()
    {
        List<String> itemNames = new ArrayList<String>();
        
        itemNames.add("店铺");
        itemNames.add("商品名称");
        itemNames.add("商品信息");
        itemNames.add("单价");
        itemNames.add("数量");
        itemNames.add("金额");
        
        return itemNames;
    }
    
    public List<String> getMyGoodsPropertyNames()
    {
        List<String> itemNames = new ArrayList<String>();
        
        itemNames.add("名称");
        itemNames.add("类别");
        itemNames.add("价格");
        itemNames.add("运费");
        itemNames.add("销售");
        itemNames.add("库存");
        itemNames.add("上架时间");
        
        return itemNames;
    }
    
    public List<String> getGoodsSalesPropertyNames()
    {
        List<String> itemNames = new ArrayList<String>();
        
        itemNames.add("名称");
        itemNames.add("类别");
        itemNames.add("价格");
        itemNames.add("运费");
        itemNames.add("订单号");
        itemNames.add("交易时间");
        itemNames.add("总销售量");
        
        return itemNames;
    }
    
    public List<String> getChangeMyGoodsPropertyNames()
    {
        List<String> itemNames = new ArrayList<String>();
        
        itemNames.add("名称");
        itemNames.add("类别");
        itemNames.add("价格");
        itemNames.add("运费");
        itemNames.add("图片");
        itemNames.add("库存");
        itemNames.add("宝贝介绍");
        
        return itemNames;
    }

}
