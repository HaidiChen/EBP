package com.ebp.g4.service.implement;

import com.ebp.g4.service.interfaces.GoodsServiceIntf;
import com.ebp.g4.service.interfaces.MainFrameServiceIntf;
import com.ebp.g4.service.interfaces.OrderServiceIntf;
import com.ebp.g4.service.interfaces.UserServiceIntf;

public class ServiceFactory
{

    public static GoodsServiceIntf getGoodsService()
    {
        return new GoodsServiceImpl();
    }

    public static MainFrameServiceIntf getMainFrameService()
    {
        return new MainFrameServiceImpl();
    }

    public static OrderServiceIntf getOrderService()
    {
        return new OrderServiceImpl();
    }

    public static UserServiceIntf getUserService()
    {
        return new UserServiceImpl();
    }

    public static String NumberToString(String result)
    {
        String finalResult = "0";
        if (result != null)
        {
            if (result.equals(""))
            {

            }
            else
            {
                finalResult = result;
            }
        }
        else
        {

        }
        return finalResult;
    }
}
