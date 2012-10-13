import exceptions.JRoboContainerException;
import getall.*;
import junit.framework.Assert;
import org.junit.Test;

public class ArrayInArgumentsTests extends JRoboContainerTestBase
{
    @Test
    public void testArrayInArguments() throws JRoboContainerException
    {
        IArrayInArguments instance = container.get(IArrayInArguments.class);
        Assert.assertNotNull(instance);
        IFourImplementations[] implementations = instance.getImplementations();
        Assert.assertEquals(implementations.length, 4);
        checkClassExistence(implementations, FirstImplementation.class);
        checkClassExistence(implementations, SecondImplementation.class);
        checkClassExistence(implementations, ThirdImplementation.class);
        checkClassExistence(implementations, FourthImplementation.class);
        IFourImplementations nextImplementations[] = container.getAll(IFourImplementations.class);
        for (IFourImplementations implementation : implementations)
            checkInstanceExistence(nextImplementations, implementation);
    }
}
