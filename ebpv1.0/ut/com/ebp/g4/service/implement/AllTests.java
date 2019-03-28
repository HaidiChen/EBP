package com.ebp.g4.service.implement;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        // $JUnit-BEGIN$
        suite.addTestSuite(GoodsServiceImplTest.class);
        suite.addTestSuite(MainFrameServiceImplTest.class);
        suite.addTestSuite(OrderServiceImplTest.class);
        suite.addTestSuite(ServiceFactoryTest.class);
        suite.addTestSuite(UserServiceImplTest.class);
        // $JUnit-END$
        return suite;
    }

}
