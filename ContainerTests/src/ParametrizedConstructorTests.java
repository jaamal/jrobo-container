import cyclicaldependecy.CycleA;
import cyclicaldependecy.CycleD;
import cyclicaldependecy.CycleE;
import exceptions.AmbiguousConstructorException;
import exceptions.CyclicalDependencyException;
import exceptions.JRoboContainerException;
import junit.framework.Assert;
import multipleconstructor.MultipleConstructor;
import multipleconstructor.MultipleConstructorIncorrect;
import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;
import parametrizedconstructor.IParamLevel1;
import parametrizedconstructor.ParamLevel1;
import parametrizedconstructor.ParamLevel2A;
import parametrizedconstructor.ParamLevel2B;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 17.09.12
 * Time: 12:06
 * To change this template use File | Settings | File Templates.
 */
public class ParametrizedConstructorTests extends JRoboContainerTestBase {
    @Test
    public void testParamLevels() throws JRoboContainerException {
        IParamLevel1 result = container.get(IParamLevel1.class);
        Assert.assertTrue(result instanceof ParamLevel1);
        ParamLevel1 casted = (ParamLevel1)result;
        Assert.assertTrue(casted.paramLevel2A instanceof ParamLevel2A);
        Assert.assertTrue(casted.paramLevel2B instanceof ParamLevel2B);
    }

    @Test
    public void testAnnotationSuccess() throws JRoboContainerException {
        container.get(MultipleConstructor.class);
    }

    @Test
    public void testMultipleAnnotated(){
        try
        {
            container.get(MultipleConstructorIncorrect.class);
            Assert.fail();
        }
        catch(AmbiguousConstructorException ex)
        {
        }
        catch(Exception ex)
        {
            Assert.fail();
        }
    }

    @Test(timeout = 1000)
    public void testCyclicalDependency() throws JRoboContainerException
    {
        try
        {
            container.get(CycleA.class);
            Assert.fail();
        }
        catch(CyclicalDependencyException ex)
        {
            logger.info("catched");
        }
        try
        {
            container.get(CycleD.class);
            Assert.fail();
        }
        catch(CyclicalDependencyException ex)
        {
            logger.info("catched");
        }
        try
        {
            container.get(CycleE.class);
            Assert.fail();
        }
        catch(CyclicalDependencyException ex)
        {
            logger.info("catched");
        }
    }
}
