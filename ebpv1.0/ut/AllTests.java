import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase
{

    public static Test suite()
    {
 
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTest(com.ebp.g4.service.implement.AllTests.suite());
        suite.addTest(com.ebp.g4.service.implement.AllTests.suite());
        //$JUnit-END$
        return suite;
    }

}