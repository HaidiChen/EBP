package com.ebp.g4.service.implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ebp.g4.dao.beans.Category;
import com.ebp.g4.dao.beans.Goods;
import com.ebp.g4.dao.beans.GoodsType;
import com.ebp.g4.dao.beans.MenuList;
import com.ebp.g4.dao.beans.Order;
import com.ebp.g4.dao.beans.PersonalCenter;
import com.ebp.g4.dao.beans.SellerCenter;
import com.ebp.g4.dao.beans.Shop;
import com.ebp.g4.dao.beans.Sorting;
import com.ebp.g4.dao.implement.DaoFactory;
import com.ebp.g4.dao.interfaces.CategoryIntf;
import com.ebp.g4.dao.interfaces.GoodsIntf;
import com.ebp.g4.dao.interfaces.GoodsTypeIntf;
import com.ebp.g4.dao.interfaces.OrderIntf;
import com.ebp.g4.dao.interfaces.PersonalCenterIntf;
import com.ebp.g4.dao.interfaces.SellerCenterIntf;
import com.ebp.g4.dao.interfaces.ShopIntf;
import com.ebp.g4.dao.interfaces.MenuListIntf;
import com.ebp.g4.dao.interfaces.SortingIntf;
import com.ebp.g4.service.beans.Classification;
import com.ebp.g4.service.beans.MainFrameGoods;
import com.ebp.g4.service.beans.Sort;
import com.ebp.g4.service.beans.Menus;
import com.ebp.g4.service.interfaces.MainFrameServiceIntf;

class MainFrameServiceImpl implements MainFrameServiceIntf
{
    CategoryIntf categoryDao;

    GoodsIntf goodsDao;

    ShopIntf shopDao;

    GoodsTypeIntf goodsTypeDao;

    MenuListIntf menuListDao;

    SortingIntf sortDao;

    PersonalCenterIntf pci;

    SellerCenterIntf sci;

    OrderIntf orderDao;

    MyLogger mylogger;

    public MainFrameServiceImpl()
    {
        categoryDao = DaoFactory.getCategoryDao();

        goodsDao = DaoFactory.getGoodsDao();

        shopDao = DaoFactory.getShopDao();

        goodsTypeDao = DaoFactory.getGoodsTypeDao();

        menuListDao = DaoFactory.getMenuListDao();

        sortDao = DaoFactory.getSortListDao();

        pci = DaoFactory.getPersonalDao();

        sci = DaoFactory.getSellerCenterDao();

        orderDao = DaoFactory.getOrderDao();

        mylogger = new MyLogger(MainFrameServiceImpl.class);
    }

    /*@Override
    public Menus getMenuList()
    {
        Menus menuList = new Menus();

        
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而 进行menu的提取，并赋值到menuList对象中
         
        Map<String, List<String>> m = new HashMap<String, List<String>>();

        // 获得主菜单名称列表
        List<MenuList> ml = menuListDao.selectAllMenu();

        List<String> subms = new ArrayList<>();

        subms.add("登录");
        subms.add("注册");
        // subms.add("退出");

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

        return menuList;
    }*/

    @Override
    public Classification getClassificationList()
    {
        Classification clf = new Classification();

        /*
         * 以下内容负责从DaoFactory中获得xxxDaoIntf从而 进行classification的提取，并赋值到clf对象中
         */

        List<String> names = new ArrayList<String>();

        names.add("全部");

        // 获得所有的分类信息
        List<Category> categories = categoryDao.selectAllCategory();

        // 一个个添加到列表当中去
        for (Category cat : categories)
        {
            names.add(cat.getName());
        }
        clf.setNames(names);
        mylogger.logger.info("已完成分类列表数据的获取");
        return clf;
    }

    @Override
    public List<MainFrameGoods> getAllGoodsToBeShown()
    {
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
                if (order.getGoodsid() != null)
                {
                    if (order.getGoodsid().equals(goods.getGoodsid()))
                    {
                        goodsAmount += Integer.parseInt(
                                ServiceFactory.NumberToString(order.getNum()));
                    }
                }
                else
                {
                }
            }
            mfGoods.setGoodsSalesAmount(goodsAmount);
            Shop shop = shopDao.selectShopById(goods.getShopid());
            mfGoods.setGoodStoreName(shop.getName());
            GoodsType goodsType = goodsTypeDao
                    .selectGoodsTypeById(goods.getTypeid());
            mfGoods.setGoodsType(goodsType.getName());

            showGoods.add(mfGoods);
            mylogger.logger.info("主界面内容获取完成");
        }

        return showGoods;
    }

    @Override
    public Sort getSortingNames()
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
        mylogger.logger.info("搜索关键字列表获取完成");
        return sortItems;
    }

    @Override
    public List<String> getGoodsPropertyNames()
    {
        List<String> itemNames = new ArrayList<String>();

        itemNames.add("商品名称");
        itemNames.add("商品类型");
        itemNames.add("商品价格");
        itemNames.add("商品销量");
        itemNames.add("商品库存");
        itemNames.add("店铺名称");

        return itemNames;
    }

    @Override
    public List<MainFrameGoods> getGoodsByClassification(
            String classificationName)
    {
        List<Goods> allGoods = goodsDao.selectAllGoods();
        List<Order> orders = orderDao.selectAllOrder();
        List<MainFrameGoods> goodsToGo = new ArrayList<MainFrameGoods>();
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
                        if (order.getGoodsid() != null)
                        {
                            if (order.getGoodsid().equals(goods.getGoodsid()))
                            {
                                goodsAmount += Integer.parseInt(ServiceFactory
                                        .NumberToString(order.getNum()));
                            }
                        }
                        else
                        {
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

        return goodsToGo;
    }

    @Override
    public List<MainFrameGoods> getGoodsBySorting(String sortingName,
            String searchContent)
    {
        List<Goods> allGoods = goodsDao.selectAllGoods();
        List<MainFrameGoods> goodsToGO = new ArrayList<MainFrameGoods>();
        List<Order> orders = orderDao.selectAllOrder();
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
                if (order.getGoodsid() != null)
                {
                    if (order.getGoodsid().equals(goods.getGoodsid()))
                    {
                        goodsAmount += Integer.parseInt(
                                ServiceFactory.NumberToString(order.getNum()));
                    }
                }
                else
                {
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
                if (goods.getName() != null)
                {
                    if (goods.getName().contains(searchContent))
                    {
                        goodsToGO.add(mfg);
                    }
                }
                else
                {
                    // do nothing
                }
            }
            else if (sortingName.equals("店铺"))
            {
                if (shop.getName() != null)
                {
                    if (shop.getName().contains(searchContent))
                    {
                        goodsToGO.add(mfg);
                    }
                }
                else
                {
                    // do nothing
                }
            }
        }

        return goodsToGO;
    }

}
