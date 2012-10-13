import exceptions.JRoboContainerException;
import getall.*;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 13.10.12
 * Time: 12:09
 * To change this template use File | Settings | File Templates.
 */
public class GetAllTests extends JRoboContainerTestBase
{
    @Test
    public void testGetAll() throws JRoboContainerException
    {
        IFourImplementations implementations[] = container.getAll(IFourImplementations.class);
        Assert.assertEquals(implementations.length, 4);
        checkClassExistence(implementations, FirstImplementation.class);
        checkClassExistence(implementations, SecondImplementation.class);
        checkClassExistence(implementations, ThirdImplementation.class);
        checkClassExistence(implementations, FourthImplementation.class);
        IFourImplementations nextImplementations[] = container.getAll(IFourImplementations.class);
        for (IFourImplementations implementation : implementations)
            checkInstanceExistence(nextImplementations, implementation);
    }

    private <T1, T2 extends T1> void checkClassExistence(T1[] implementations, Class<T2> clazz)
    {
        for (T1 implementation : implementations)
            if (implementation.getClass() == clazz)
                return;
        Assert.fail();
    }

    private <T1, T2 extends T1> void checkInstanceExistence(T1[] implementations, T2 instance)
    {
        for (T1 implementation : implementations)
            if (implementation == instance)
                return;
        Assert.fail();
    }
}
