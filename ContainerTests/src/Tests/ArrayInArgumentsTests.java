package Tests;

import TestBases.JRoboContainerTestBase;
import overclocking.jrobocontainer.exceptions.JRoboContainerException;
import junit.framework.Assert;
import org.junit.Test;
import testclasses.getall.*;

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
