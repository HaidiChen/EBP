package com.ebp.g4.dao.implement;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
{ AdminImplTest.class, CartImplTest.class, CategoryImplTest.class,
        CommentImplTest.class, CommentTypeImplTest.class, GoodsImplTest.class,
        GoodsTypeImplTest.class, LogCompanyImplTest.class,
        MenuListImplTest.class, OrderImplTest.class, OrderStatusImplTest.class,
        PersonalCenterImplTest.class, SellerCenterImplTest.class,
        SellerImplTest.class, ShopImplTest.class, SortingImplTest.class,
        UserImplTest.class,ShopTypeImplTest.class })
public class AllTests
{

}
