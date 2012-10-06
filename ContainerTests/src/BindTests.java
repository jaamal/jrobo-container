import container.Container;
import exceptions.JRoboContainerException;
import filters.NoSystemsFilter;
import junit.framework.Assert;
import junit.framework.TestCase;
import multipleimplementation.IMultipleImplementation;
import multipleimplementation.MultipleImplementationFirst;
import multipleimplementation.MultipleImplementationSecond;
import org.junit.Test;


public class BindTests extends JRoboContainerTestBase {

    @Test
    public void testBind() throws JRoboContainerException {
        MultipleImplementationFirst impl1 = new MultipleImplementationFirst();
        MultipleImplementationSecond impl2 = new MultipleImplementationSecond();
        container.bindInstance(IMultipleImplementation.class, impl1);
        IMultipleImplementation testImpl = container.get(IMultipleImplementation.class);
        Assert.assertTrue(testImpl instanceof MultipleImplementationFirst);
        container.bindInstance(IMultipleImplementation.class, impl2);
        testImpl = container.get(IMultipleImplementation.class);
        Assert.assertTrue(testImpl instanceof MultipleImplementationSecond);
    }

}