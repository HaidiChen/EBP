package com.ebp.g4.service.implement;

import java.util.ArrayList;
import java.util.List;

import com.ebp.g4.dao.beans.Goods;
import com.ebp.g4.dao.beans.GoodsType;
//import com.ebp.g4.dao.beans.MenuList;
import com.ebp.g4.dao.beans.Order;
//import com.ebp.g4.dao.beans.PersonalCenter;
//import com.ebp.g4.dao.beans.SellerCenter;
import com.ebp.g4.dao.beans.Shop;
import com.ebp.g4.dao.beans.Sorting;
import com.ebp.g4.dao.implement.DaoFactory;
import com.ebp.g4.dao.interfaces.GoodsIntf;
import com.ebp.g4.dao.interfaces.GoodsTypeIntf;
import com.ebp.g4.dao.interfaces.MenuListIntf;
import com.ebp.g4.dao.interfaces.OrderIntf;
import com.ebp.g4.dao.interfaces.PersonalCenterIntf;
import com.ebp.g4.dao.interfaces.SellerCenterIntf;
import com.ebp.g4.dao.interfaces.ShopIntf;
import com.ebp.g4.dao.interfaces.SortingIntf;
import com.ebp.g4.service.beans.Classification;
import com.ebp.g4.service.beans.MainFrameGoods;
//import com.ebp.g4.service.beans.Menus;
import com.ebp.g4.service.beans.Sort;
import com.ebp.g4.service.implement.MainFrameServiceImpl;

import junit.framework.TestCase;

public class MainFrameServiceImplTest extends TestCase
{
    MainFrameServiceImpl mfsi = new MainFrameServiceImpl();

    MenuListIntf menuListDao = DaoFactory.getMenuListDao();

    PersonalCenterIntf pci = DaoFactory.getPersonalDao();

    SellerCenterIntf sci = DaoFactory.getSellerCenterDao();

    GoodsIntf goodsDao = DaoFactory.getGoodsDao();

    GoodsTypeIntf goodsTypeDao = DaoFactory.getGoodsTypeDao();

    ShopIntf shopDao = DaoFactory.getShopDao();

    OrderIntf orderDao = DaoFactory.getOrderDao();

    SortingIntf sortDao = DaoFactory.getSortListDao();

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

    /*public void testGetMenuList()
    {
        Menus menuList = new Menus();

        
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而 进行menu的提取，并赋值到menuList对象中
         
        Map<String, List<String>> m = new HashMap<String, List<String>>();

        // 获得主菜单名称列表
        List<MenuList> ml = menuListDao.selectAllMenu();

        List<String> subms = new ArrayList<>();

        subms.add("登录");
        subms.add("注册");

        m.put(ml.get(0).getName(), subms);

        List<PersonalCenter> pc = pci.selectAllPersonalCenter();
        List<String> pnames = new ArrayList<String>();
        for (PersonalCenter p : pc)
        {
            pnames.add(p.getName());
        }
        m.put(ml.get(1).getName(), pnames);

        List<SellerCenter> sc = sci.selectAllSellerCenter();
        List<String> snames = new ArrayList<String>();
        for (SellerCenter s : sc)
        {
            snames.add(s.getName());
        }
        m.put(ml.get(2).getName(), snames);

        menuList.setMenus(m);
        assertEquals(menuList, mfsi.getMenuList());
    }*/

    public void testGetClassificationList()
    {
        Classification obj = new Classification();

        List<String> names = new ArrayList<String>();

        names.add("c1");
        names.add("c2");
        names.add("c3");
        obj.setNames(names);

        assertEquals(false, mfsi.getClassificationList().equals(obj));
    }

    public void testGetAllGoodsToBeShown()
    {
        // addTestSource();
        List<MainFrameGoods> showGoods = new ArrayList<MainFrameGoods>();
        List<Order> orders = orderDao.selectAllOrder();
        /*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而 进行MainFrameGoods的提取，并赋值到showGoods对象中
         */
        List<Goods> allGoods = goodsDao.selectAllGoods();
        for (Goods goods : allGoods)
        {
            MainFrameGoods mfGoods = new MainFrameGoods();

            mfGoods.setGoodsStock(Integer
                    .parseInt(ServiceFactory.NumberToString(goods.getStock())));
            mfGoods.setGoodsName(goods.getName());

            mfGoods.setGoodsPrice(Float.parseFloat(
                    ServiceFactory.NumberToString(goods.getPrice())));

            int goodsAmount = 0;
            for (Order order : orders)
            {
                if ((/* order.getGoodsid() == null ? "" : */order.getGoodsid())
                        .equals(goods.getGoodsid()))
                {
                    goodsAmount += Integer.parseInt(
                            ServiceFactory.NumberToString(order.getNum()));
                }
            }
            mfGoods.setGoodsSalesAmount(goodsAmount);
            Shop shop = shopDao.selectShopById(goods.getShopid());
            mfGoods.setGoodStoreName(shop.getName());
            GoodsType goodsType = goodsTypeDao
                    .selectGoodsTypeById(goods.getTypeid());
            mfGoods.setGoodsType(goodsType.getName());

            showGoods.add(mfGoods);
        }

        assertEquals(true, mfsi.getAllGoodsToBeShown().toString()
                .equals(showGoods.toString()));

        // deleteTestSource();
    }

    public void testGetSortingNames()
    {
        Sort sortItems = new Sort();

        List<Sorting> sortings = sortDao.selectAllSortings();
        List<String> names = new ArrayList<String>();

        /*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而 进行sorting的提取，并赋值到sortItems对象中
         */

        for (Sorting sorting : sortings)
        {
            names.add(sorting.getName());
        }
        sortItems.setNames(names);

        assertEquals(true, mfsi.getSortingNames().equals(sortItems));
    }

    public void testGetGoodsPropertyNames()
    {
        List<String> itemNames = new ArrayList<String>();

        itemNames.add("商品名称");
        itemNames.add("商品类型");
        itemNames.add("商品价格");
        itemNames.add("商品销量");
        itemNames.add("商品库存");
        itemNames.add("店铺名称");

        assertEquals(itemNames, mfsi.getGoodsPropertyNames());
    }

    public void testGetGoodsByClassification()
    {
        // addTestSource();
        List<Goods> allGoods = goodsDao.selectAllGoods();
        List<Order> orders = orderDao.selectAllOrder();
        List<MainFrameGoods> goodsToGo = new ArrayList<MainFrameGoods>();
        String classificationName = "食品";
        for (Goods goods : allGoods)
        {
            GoodsType goodsType = goodsTypeDao
                    .selectGoodsTypeById(goods.getTypeid());
            if (goodsType.getId() != null)
            {
                if (goodsType.getCid().equals(classificationName))
                {
                    MainFrameGoods mfg = new MainFrameGoods();

                    mfg.setGoodsStock(Integer.parseInt(
                            ServiceFactory.NumberToString(goods.getStock())));
                    mfg.setGoodsName(goods.getName());

                    mfg.setGoodsPrice(Float.parseFloat(
                            ServiceFactory.NumberToString(goods.getPrice())));
                    int goodsAmount = 0;
                    for (Order order : orders)
                    {
                        if ((/* order.getGoodsid() == null ? "" : */order
                                .getGoodsid()).equals(goods.getGoodsid()))
                        {
                            goodsAmount += Integer.parseInt(ServiceFactory
                                    .NumberToString(order.getNum()));
                        }
                    }
                    mfg.setGoodsSalesAmount(goodsAmount);
                    Shop shop = shopDao.selectShopById(goods.getShopid());
                    mfg.setGoodStoreName(shop.getName());
                    mfg.setGoodsType(goodsType.getName());

                    goodsToGo.add(mfg);
                }
            }
            else
            {

            }
        }

        assertEquals(true, mfsi.getGoodsByClassification("食品").toString()
                .equals(goodsToGo.toString()));
        assertEquals(false, mfsi.getGoodsByClassification("辣鸡").toString()
                .equals(goodsToGo.toString()));

        // deleteTestSource();
    }

    public void testGetGoodsBySorting()
    {
        // addTestSource();
        List<Goods> allGoods = goodsDao.selectAllGoods();
        List<Order> orders = orderDao.selectAllOrder();
        List<MainFrameGoods> goodsToGO = new ArrayList<MainFrameGoods>();
        String sortingName = "店铺";
        String searchContent = "妞妞零食店";
        for (Goods goods : allGoods)
        {
            MainFrameGoods mfg = new MainFrameGoods();

            mfg.setGoodsStock(Integer
                    .parseInt(ServiceFactory.NumberToString(goods.getStock())));
            mfg.setGoodsName(goods.getName());

            mfg.setGoodsPrice(Float.parseFloat(
                    ServiceFactory.NumberToString(goods.getPrice())));
            int goodsAmount = 0;
            for (Order order : orders)
            {
                if ((/* order.getGoodsid() == null ? "" : */order.getGoodsid())
                        .equals(goods.getGoodsid()))
                {
                    goodsAmount += Integer.parseInt(
                            ServiceFactory.NumberToString(order.getNum()));
                }
            }
            mfg.setGoodsSalesAmount(goodsAmount);
            Shop shop = shopDao.selectShopById(goods.getShopid());
            mfg.setGoodStoreName(shop.getName());
            GoodsType goodsType = goodsTypeDao
                    .selectGoodsTypeById(goods.getTypeid());
            String goodsTypeName = goodsType.getName();
            mfg.setGoodsType(goodsTypeName);
            if (sortingName.equals("商品"))
            {
                if ((/* goods.getName() == null ? "" : */goods.getName())
                        .contains(searchContent))
                {
                    goodsToGO.add(mfg);
                }
            }
            else if (sortingName.equals("店铺"))
            {
                if ((shop.getName() == null ? "" : shop.getName())
                        .contains(searchContent))
                {
                    goodsToGO.add(mfg);
                }
            }
        }

        assertEquals(true, mfsi.getGoodsBySorting(sortingName, searchContent)
                .toString().equals(goodsToGO.toString()));
        assertEquals(false, mfsi.getGoodsBySorting("商品", "榴莲糖").toString()
                .equals(goodsToGO.toString()));
        assertEquals(false, mfsi.getGoodsBySorting("hel", "hel").toString()
                .equals(goodsToGO.toString()));

        // deleteTestSource();
    }
}
