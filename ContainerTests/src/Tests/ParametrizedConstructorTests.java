package Tests;

import TestBases.JRoboContainerTestBase;
import junit.framework.Assert;
import org.junit.Test;
import overclocking.jrobocontainer.exceptions.AmbiguousAnnotatedConstructorException;
import overclocking.jrobocontainer.exceptions.CyclicalDependencyException;
import testclasses.cyclicaldependecy.CycleA;
import testclasses.cyclicaldependecy.CycleD;
import testclasses.cyclicaldependecy.CycleE;
import testclasses.multipleconstructor.MultipleConstructor;
import testclasses.multipleconstructor.MultipleConstructorIncorrect;
import testclasses.parametrizedconstructor.IParamLevel1;
import testclasses.parametrizedconstructor.ParamLevel1;
import testclasses.parametrizedconstructor.ParamLevel2A;
import testclasses.parametrizedconstructor.ParamLevel2B;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 17.09.12
 * Time: 12:06
 * To change this template use File | Settings | File Templates.
 */
public class ParametrizedConstructorTests extends JRoboContainerTestBase
{
    @Test
    public void testParamLevels()
    {
        IParamLevel1 result = container.get(IParamLevel1.class);
        Assert.assertTrue(result instanceof ParamLevel1);
        ParamLevel1 casted = (ParamLevel1) result;
        Assert.assertTrue(casted.paramLevel2A instanceof ParamLevel2A);
        Assert.assertTrue(casted.paramLevel2B instanceof ParamLevel2B);
    }

    @Test
    public void testAnnotationSuccess()
    {
        container.get(MultipleConstructor.class);
    }

    @Test
    public void testMultipleAnnotated()
    {
        try
        {
            container.get(MultipleConstructorIncorrect.class);
            Assert.fail();
        }
        catch (AmbiguousAnnotatedConstructorException ex)
        {
        }
        catch (Exception ex)
        {
            Assert.fail();
        }
    }

    @Test(timeout = 1000)
    public void testCyclicalDependency()
    {
        //System.out.println(container.getClassesLoadingLog());
        try
        {
            container.get(CycleA.class);
            Assert.fail();
        }
        catch (CyclicalDependencyException ex)
        {
        }
        try
        {
            container.get(CycleD.class);
            Assert.fail();
        }
        catch (CyclicalDependencyException ex)
        {
        }
        try
        {
            container.get(CycleE.class);
            Assert.fail();
        }
        catch (CyclicalDependencyException ex)
        {
        }
    }
}
