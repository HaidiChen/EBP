package com.ebp.g4.service.implement;

import junit.framework.TestCase;

public class ServiceFactoryTest extends TestCase
{

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testGetGoodsService()
    {
        ServiceFactory.getGoodsService();
    }

    public void testGetMainFrameService()
    {
        ServiceFactory.getMainFrameService();
    }

    public void testGetOrderService()
    {
        ServiceFactory.getOrderService();
    }

    public void testGetUserService()
    {
        ServiceFactory.getUserService();
    }

}
