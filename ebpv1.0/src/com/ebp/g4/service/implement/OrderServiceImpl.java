package com.ebp.g4.service.implement;

import java.util.ArrayList;
import java.util.List;

import com.ebp.g4.dao.beans.Goods;
import com.ebp.g4.dao.beans.Order;
import com.ebp.g4.dao.beans.OrderStatus;
import com.ebp.g4.dao.beans.Shop;
import com.ebp.g4.dao.beans.User;
import com.ebp.g4.dao.implement.DaoFactory;
import com.ebp.g4.dao.interfaces.GoodsIntf;
import com.ebp.g4.dao.interfaces.OrderIntf;
import com.ebp.g4.dao.interfaces.OrderStatusIntf;
import com.ebp.g4.dao.interfaces.ShopIntf;
import com.ebp.g4.dao.interfaces.UserIntf;
import com.ebp.g4.service.beans.CartGoods;
import com.ebp.g4.service.beans.OrderDetail;
import com.ebp.g4.service.beans.OrderInfo;
import com.ebp.g4.service.beans.SalesOrder;
import com.ebp.g4.service.interfaces.OrderServiceIntf;

class OrderServiceImpl implements OrderServiceIntf
{
    OrderIntf orderDao;

    ShopIntf shopDao;

    GoodsIntf goodsDao;

    OrderStatusIntf statusDao;

    UserIntf userDao;
    
    MyLogger log;
    
    public OrderServiceImpl(){
        orderDao = DaoFactory.getOrderDao();

        shopDao = DaoFactory.getShopDao();

        goodsDao = DaoFactory.getGoodsDao();

        statusDao = DaoFactory.getOrderStatusDao();

        userDao = DaoFactory.getUserDao();
        
        log = new MyLogger(OrderServiceImpl.class);
    }

    @Override
    public OrderDetail getOrderDetailsByOrderNumber(String number)
    {
        OrderDetail od = new OrderDetail();

        /*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而 进行OrderDetail的提取，并赋值到menuList对象中
         */

        Order order = orderDao.selectOrderById(number);

        String shopid = order.getShopid();
        Shop shop = shopDao.selectShopById(shopid);

        String goodsNumber = order.getGoodsid();
        String logisticsName = shop.getLogcompany();
        String orderDate = order.getTime();
        String orderNumber = number;

        String userId = order.getUserId();
        User user = userDao.selectUserById(userId);

        String phone = user.getPhone();
        String receiveAddress = user.getAddress();
        String receiver = user.getNickname();
        String storeName = shop.getName();

        od.setGoodsNumber(goodsNumber);
        od.setLogisticsName(logisticsName);
        od.setOrderDate(orderDate);
        od.setOrderNumber(orderNumber);
        od.setPhone(phone);
        od.setReceiveAddress(receiveAddress);
        od.setReceiver(receiver);
        od.setStoreName(storeName);

        return od;
    }

    @Override
    public boolean updateOrderInfo(String orderNumber, OrderInfo neworderInfo)
    {
        float newShipping = neworderInfo.getCarriage();
        String newGoodsName = neworderInfo.getGoodsName();

        int newnumbers = neworderInfo.getNumbers();
        String newDate = neworderInfo.getOrderDate();
        String newStatus = neworderInfo.getStatus();
        String newStore = neworderInfo.getStore();
        float newPay = neworderInfo.getThePay();

        Goods goods = goodsDao.selectGoodsByName(newGoodsName);
        Shop shop = shopDao.selectShopByName(newStore);

        Order order = orderDao.selectOrderById(orderNumber);
        String newGoodsNumber = goods.getGoodsid();
        order.setGoodsid(newGoodsNumber);
        order.setNum("" + newnumbers);
        String newShopId = shop.getShopid();
        order.setShopid(newShopId);
        order.setTime(newDate);
        order.setTotalprice("" + newPay);
        order.setTransprice("" + newShipping);
        OrderStatus os = statusDao.selectStatusByName(newStatus);
        order.setStateid(os.getId());

        return orderDao.updateOrder(order);
    }

    @Override
    public List<OrderInfo> getAllOrderInfoByAccount(String account)
    {
        List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
        List<Order> orders = orderDao.selectAllOrder();
        /*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而 进行OrderInfo的提取，并赋值到orderInfoList对象中
         */
        for (Order order : orders)
        {
            if (account.equals(order.getUserId()))
            {
                OrderInfo e = new OrderInfo();
                float carriage = Float.parseFloat(
                        ServiceFactory.NumberToString(order.getTransprice()));
                e.setCarriage(carriage);
                Goods goods = goodsDao.selectGoodsById(order.getGoodsid());
                String goodsName = goods.getName();
                e.setGoodsName(goodsName);
                e.setGoodsPrice(Float.parseFloat(
                        ServiceFactory.NumberToString(goods.getPrice())));
                e.setNumbers(Integer.parseInt(
                        ServiceFactory.NumberToString(order.getNum())));
                e.setOrderDate(order.getTime());
                e.setOrderID(order.getOrderid());
                OrderStatus orderStatus = statusDao
                        .selectStatusById(order.getStateid());
                e.setStatus(orderStatus.getName());
                Shop shop = shopDao.selectShopById(order.getShopid());
                e.setStore(shop.getName());

                e.setThePay(Float.parseFloat(
                        ServiceFactory.NumberToString(order.getTotalprice())));

                orderInfoList.add(e);
            }
            else
            {
                // do nothing
            }
        }

        return orderInfoList;
    }

    @Override
    public SalesOrder getSalesOrderInfoByOrderNumber(String number)
    {
        SalesOrder so = new SalesOrder();

        /*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而 进行SalesOrder的提取，并赋值到so对象中
         */
        Order order = orderDao.selectOrderById(number);
        Goods goods = goodsDao.selectGoodsById(order.getGoodsid());
        OrderStatus os = statusDao.selectStatusById(order.getStateid());

        so.setGoodsAmount(Integer
                .parseInt(ServiceFactory.NumberToString(order.getNum())));

        so.setGoodsName(goods.getName());

        so.setGoodsPayFee(Float.parseFloat(
                ServiceFactory.NumberToString(order.getTotalprice())));

        so.setGoodsPrice(Float
                .parseFloat(ServiceFactory.NumberToString(goods.getPrice())));

        so.setGoodsShipping(Float.parseFloat(
                ServiceFactory.NumberToString(order.getTransprice())));
        so.setOrderDate(order.getTime());
        so.setOrderNumber(number);

        so.setOrderStatus(os.getName());

        return so;
    }

    @Override
    public List<SalesOrder> getSalesOrderInfoListByDate(String date)
    {
        List<SalesOrder> soList = new ArrayList<SalesOrder>();

        /*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而 进行SalesOrder的提取，并赋值到soList对象中
         */
        List<Order> orders = orderDao.selectAllOrderByDate(date);
        for (Order order : orders)
        {
            SalesOrder so = new SalesOrder();
            so.setOrderDate(order.getTime());
            so.setOrderNumber(order.getOrderid());
            OrderStatus os = statusDao.selectStatusById(order.getStateid());
            so.setOrderStatus(os.getName());

            Goods goods = goodsDao.selectGoodsById(order.getGoodsid());
            so.setGoodsAmount(Integer
                    .parseInt(ServiceFactory.NumberToString(order.getNum())));
            so.setGoodsName(goods.getName());
            so.setGoodsPayFee(Float.parseFloat(
                    ServiceFactory.NumberToString(order.getTotalprice())));
            so.setGoodsShipping(Float.parseFloat(
                    ServiceFactory.NumberToString(order.getTransprice())));
            so.setGoodsPrice(Float.parseFloat(
                    ServiceFactory.NumberToString(goods.getPrice())));
            soList.add(so);
        }

        return soList;
    }

    @Override
    public List<SalesOrder> getAllSalesOrderInfoByStoreName(String storeName)
    {
        List<SalesOrder> soList = new ArrayList<SalesOrder>();

        /*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而 进行SalesOrder的提取，并赋值到soList对象中
         */
        List<Order> orders = orderDao.selectAllOrder();
        for (Order order : orders)
        {
            Shop shop = shopDao.selectShopByName(storeName);
            if ((order.getShopid())
                    .equals(shop.getShopid()))
            {
                SalesOrder so = new SalesOrder();
                so.setOrderDate(order.getTime());
                so.setOrderNumber(order.getOrderid());
                OrderStatus os = statusDao.selectStatusById(order.getStateid());
                so.setOrderStatus(os.getName());

                Goods goods = goodsDao.selectGoodsById(order.getGoodsid());
                so.setGoodsAmount(Integer.parseInt(
                        ServiceFactory.NumberToString(order.getNum())));
                so.setGoodsName(goods.getName());
                so.setGoodsPayFee(Float.parseFloat(
                        ServiceFactory.NumberToString(order.getTotalprice())));
                so.setGoodsShipping(Float.parseFloat(
                        ServiceFactory.NumberToString(order.getTransprice())));

                so.setGoodsPrice(Float.parseFloat(
                        ServiceFactory.NumberToString(goods.getPrice())));
                soList.add(so);
            }
            else
            {
                // do nothing
            }
        }

        return soList;
    }

    @Override
    public List<String> getOrderInfoPropertyNames()
    {
        List<String> itemNames = new ArrayList<String>();

        itemNames.add("订单编号");
        itemNames.add("商品单价");
        itemNames.add("数量");
        itemNames.add("商品名称");
        itemNames.add("店铺名称");
        itemNames.add("交易时间");
        itemNames.add("运费");
        itemNames.add("付款");
        itemNames.add("交易状态");

        return itemNames;
    }

    @Override
    public List<String> getSalesOrderPropertyNames()
    {
        List<String> itemNames = new ArrayList<String>();

        itemNames.add("交易时间");
        itemNames.add("订单编号");
        itemNames.add("商品名称");
        itemNames.add("单价");
        itemNames.add("数量");
        itemNames.add("运费");
        itemNames.add("付款");
        itemNames.add("交易状态");

        return itemNames;
    }

    @Override
    public boolean createOrder(String account, CartGoods goods, String time)
    {
        Order order = new Order();

        int max = orderDao.selectAllOrder().size();
        Goods newgoods = goodsDao.selectGoodsByName(goods.getGoodsName());
        order.setGoodsid(newgoods.getGoodsid());
        String orderId = "o" + Integer.toString(max + 1);
        order.setOrderid(orderId);
        order.setUserId(account);
        order.setStateid("os1");
        Shop shop = shopDao.selectShopByName(goods.getShopName());
        order.setShopid(shop.getShopid());
        order.setNum("" + goods.getGoodsNumber());
        order.setTime(time);
        order.setTransprice(newgoods.getTranprice());
        order.setTotalprice("" + (goods.getMoney() + Float.parseFloat(
                ServiceFactory.NumberToString(newgoods.getTranprice()))));

        String stock = "" + (Integer.parseInt(newgoods.getStock())
                - goods.getGoodsNumber());

        newgoods.setStock(stock);
        goodsDao.updateGoods(newgoods);

        return orderDao.addOrder(order);
    }

    @Override
    public boolean updateSalesOrderStatus(String orderNumber, String newStatus)
    {
        Order order = orderDao.selectOrderById(orderNumber);
        OrderStatus os = statusDao.selectStatusByName(newStatus);
        order.setStateid(os.getId());

        return orderDao.updateOrder(order);
    }

}
