package tests.container;

import container.Container;
import exceptions.JRoboContainerException;
import files.multipleimplementation.IMultipleImplementation;
import files.multipleimplementation.MultipleImplementationFirst;
import files.multipleimplementation.MultipleImplementationSecond;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;


public class BindTest extends TestCase {

    private Container container;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        container = new Container(new NoSystemsFilter());
    }

    @Test
    public void testBind() throws JRoboContainerException {
        MultipleImplementationFirst impl1 = new MultipleImplementationFirst();
        MultipleImplementationSecond impl2 = new MultipleImplementationSecond();
        container.bind(IMultipleImplementation.class, impl1);
        IMultipleImplementation testImpl = container.get(IMultipleImplementation.class);
        Assert.assertTrue(testImpl instanceof MultipleImplementationFirst);
        container.bind(IMultipleImplementation.class, impl2);
        testImpl = container.get(IMultipleImplementation.class);
        Assert.assertTrue(testImpl instanceof MultipleImplementationSecond);
    }

}

