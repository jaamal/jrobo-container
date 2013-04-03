package Tests;

import TestBases.JRoboContainerTestBase;
import junit.framework.Assert;
import org.junit.Test;
import overclocking.jrobocontainer.exceptions.JroboContainerException;
import testclasses.getall.*;


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
    public void testGetAll() throws JroboContainerException
    {
        IFourImplementations implementations[] = container.getAll(IFourImplementations.class);
        System.out.println(container.getLastLog());
        Assert.assertEquals(implementations.length, 4);
        checkClassExistence(implementations, FirstImplementation.class);
        checkClassExistence(implementations, SecondImplementation.class);
        checkClassExistence(implementations, ThirdImplementation.class);
        checkClassExistence(implementations, FourthImplementation.class);
        IFourImplementations nextImplementations[] = container.getAll(IFourImplementations.class);
        System.out.println(container.getLastLog());
        for (IFourImplementations implementation : implementations)
            checkInstanceExistence(nextImplementations, implementation);
    }
}
