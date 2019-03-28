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
import com.ebp.g4.service.implement.OrderServiceImpl;

import junit.framework.TestCase;

public class OrderServiceImplTest extends TestCase
{
    OrderServiceImpl osi = new OrderServiceImpl();

    OrderIntf orderDao = DaoFactory.getOrderDao();

    ShopIntf shopDao = DaoFactory.getShopDao();

    GoodsIntf goodsDao = DaoFactory.getGoodsDao();

    OrderStatusIntf statusDao = DaoFactory.getOrderStatusDao();

    UserIntf userDao = DaoFactory.getUserDao();

    @Override
    protected void setUp() throws Exception
    {
        Goods goods = new Goods();

        goods.setGoodsid("g0");
        goods.setName("testgoods");
        goods.setTypeid("gt1");

        goodsDao.addGoods(goods);

        Order order = new Order();

        order.setGoodsid("g0");
        order.setOrderid("o0");
        order.setUserId("u1");

        orderDao.addOrder(order);
    }

    @Override
    protected void tearDown() throws Exception
    {
        goodsDao.deleteGoods("g0");
        orderDao.deleteOrder("o0");
    }

    /*
     * private void addTestSource() { Goods goods = new Goods();
     * 
     * goods.setGoodsid("g0"); goods.setName("testgoods");
     * goods.setTypeid("gt1");
     * 
     * goodsDao.addGoods(goods);
     * 
     * Order order = new Order();
     * 
     * order.setGoodsid("g0"); order.setOrderid("o0"); order.setUserId("u1");
     * 
     * orderDao.addOrder(order); }
     * 
     * private void deleteTestSource() { goodsDao.deleteGoods("g0");
     * orderDao.deleteOrder("o0"); }
     */

    public void testGetOrderDetailsByOrderNumber()
    {
        // addTestSource();

        OrderDetail od = new OrderDetail();

        /*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而 进行OrderDetail的提取，并赋值到menuList对象中
         */

        String number = "o1";
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
        assertEquals(true, osi.getOrderDetailsByOrderNumber("o1").equals(od));

        // deleteTestSource();
    }

    public void testUpdateOrderInfo()
    {
        OrderInfo neworderInfo = new OrderInfo();
        assertEquals(true, osi.updateOrderInfo("123", neworderInfo));
    }

    public void testGetAllOrderInfoByAccount()
    {
        // addTestSource();

        List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
        List<Order> orders = orderDao.selectAllOrder();

        String account = "u1";
        /*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而 进行OrderInfo的提取，并赋值到orderInfoList对象中
         */
        for (Order order : orders)
        {
            if ((/* account == null ? "" : */account).equals(order.getUserId()))
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

        assertEquals(true, osi.getAllOrderInfoByAccount("u1").toString()
                .equals(orderInfoList.toString()));

        // deleteTestSource();
    }

    public void testGetSalesOrderInfoByOrderNumber()
    {
        // addTestSource();
        SalesOrder so = new SalesOrder();
        String number = "o1";

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

        assertEquals(true, osi.getSalesOrderInfoByOrderNumber("o1").equals(so));
        assertEquals(false,
                osi.getSalesOrderInfoByOrderNumber("o0").equals(so));

        // deleteTestSource();
    }

    public void testGetSalesOrderInfoListByDate()
    {
        // addTestSource();
        List<SalesOrder> soList = new ArrayList<SalesOrder>();
        String date = "2018-6-26";

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

        assertEquals(true,
                osi.getSalesOrderInfoListByDate(date).equals(soList));

        // deleteTestSource();
    }

    public void testGetAllSalesOrderInfoByStoreName()
    {
        // addTestSource();

        List<SalesOrder> soList = new ArrayList<SalesOrder>();

        /*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而 进行SalesOrder的提取，并赋值到soList对象中
         */
        String storeName = "妞妞零食店";
        List<Order> orders = orderDao.selectAllOrder();
        for (Order order : orders)
        {
            Shop shop = shopDao.selectShopByName(storeName);
            if ((/* order.getShopid() == null ? "" : */order.getShopid())
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
        assertEquals(true, osi.getAllSalesOrderInfoByStoreName("妞妞零食店")
                .toString().equals(soList.toString()));

        // deleteTestSource();
    }

    public void testCreateOrder()
    {
        Order order = new Order();
        CartGoods goods = new CartGoods();
        goods.setGoodsInfo("hello");
        goods.setGoodsName("榴莲糖");
        goods.setGoodsNumber(2);
        goods.setGoodsPrice(30f);
        goods.setMoney(60);
        goods.setShopName("ho");
        int max = orderDao.selectAllOrder().size();
        Goods newgoods = goodsDao.selectGoodsByName(goods.getGoodsName());
        order.setGoodsid(newgoods.getGoodsid());
        String orderId = "o" + Integer.toString(max + 1);
        order.setOrderid(orderId);
        String account = "u1";
        order.setUserId(account);
        order.setStateid("os1");
        Shop shop = shopDao.selectShopByName(goods.getShopName());
        order.setShopid(shop.getShopid());
        order.setNum("" + goods.getGoodsNumber());
        String time = "2018-6-23";
        order.setTime(time);
        order.setTransprice(newgoods.getTranprice());
        order.setTotalprice("" + (goods.getMoney()
                + Float.parseFloat(newgoods.getTranprice())));

        String stock = "" + (Integer.parseInt(newgoods.getStock())
                - goods.getGoodsNumber());

        newgoods.setStock(stock);
        goodsDao.updateGoods(newgoods);

        assertEquals(true, osi.createOrder(account, goods, time));

        orderDao.deleteOrder(orderId);
    }

    public void testGetOrderInfoPropertyNames()
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

        assertEquals(true, osi.getOrderInfoPropertyNames().equals(itemNames));
    }

    public void testGetSalesOrderPropertyNames()
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

        assertEquals(true, osi.getSalesOrderPropertyNames().equals(itemNames));
    }

    public void testUpdateSalesOrderStatus()
    {
        assertEquals(true, osi.updateSalesOrderStatus("o1", "已发货"));
    }

}
