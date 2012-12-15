package TestBases;

import container.Container;
import container.IContainer;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 17.09.12
 * Time: 10:58
 * To change this template use File | Settings | File Templates.
 */
public abstract class JRoboContainerTestBase extends TestCase
{
    @Override
    protected void setUp() throws Exception
    {
        DOMConfigurator.configure("log4j.xml");
        container = new Container();
    }


    protected <T1, T2 extends T1> void checkClassExistence(T1[] implementations, Class<T2> clazz)
    {
        for (T1 implementation : implementations)
            if (implementation.getClass() == clazz)
                return;
        Assert.fail();
    }

    protected <T1, T2 extends T1> void checkInstanceExistence(T1[] implementations, T2 instance)
    {
        for (T1 implementation : implementations)
            if (implementation == instance)
                return;
        Assert.fail();
    }

    protected Logger logger = LogManager.getLogger(JRoboContainerTestBase.class);
    protected IContainer container;
}
