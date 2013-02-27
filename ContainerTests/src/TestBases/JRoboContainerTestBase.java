package TestBases;

import overclocking.jrobocontainer.container.Container;
import overclocking.jrobocontainer.container.IContainer;
import junit.framework.Assert;
import junit.framework.TestCase;

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
    protected IContainer container;
}
