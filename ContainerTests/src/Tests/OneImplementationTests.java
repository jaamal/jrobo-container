package Tests;

import TestBases.JRoboContainerTestBase;
import testclasses.classloader.simpletest.IOneImplementation;
import testclasses.classloader.simpletest.OneImplementation;
import overclocking.jrobocontainer.exceptions.JRoboContainerException;
import testclasses.gettingcreating.IIncrementer;
import junit.framework.Assert;
import testclasses.multipleimplementation.IMultipleImplementation;
import org.junit.Test;
import testclasses.singleimplementation.*;


public class OneImplementationTests extends JRoboContainerTestBase
{

    @Test
    public void testGetsSameObject() throws JRoboContainerException
    {
        System.out.println(container.getClassesLoadingLog());
        IOneImplementation result1 = container.get(IOneImplementation.class);
        IOneImplementation result2 = container.get(IOneImplementation.class);
        Assert.assertSame(result1, result2);
    }

    @Test
    public void testSimple() throws JRoboContainerException
    {
        IOneImplementation result = container.get(IOneImplementation.class);
        Assert.assertTrue(result instanceof OneImplementation);
    }

    @Test
    public void testGetting() throws JRoboContainerException
    {
        IIncrementer result1 = container.get(IIncrementer.class);
        Assert.assertTrue(result1.inc() == 1);
        IIncrementer result2 = container.get(IIncrementer.class);
        Assert.assertTrue(result2.inc() == 2);
    }

    @Test
    public void testCreating() throws JRoboContainerException
    {
        IIncrementer result1 = container.create(IIncrementer.class);
        Assert.assertTrue(result1.inc() == 1);
        IIncrementer result2 = container.create(IIncrementer.class);
        Assert.assertTrue(result2.inc() == 1);
    }

    @Test
    public void testCreatingAndGetting() throws JRoboContainerException
    {
        IIncrementer result1 = container.create(IIncrementer.class);
        Assert.assertTrue(result1.inc() == 1);
        IIncrementer result2 = container.get(IIncrementer.class);
        Assert.assertTrue(result2.inc() == 1);
        IIncrementer result3 = container.create(IIncrementer.class);
        Assert.assertTrue(result3.inc() == 1);
        IIncrementer result4 = container.get(IIncrementer.class);
        Assert.assertTrue(result4.inc() == 2);
    }

    @Test
    public void testException() throws JRoboContainerException
    {
        try
        {
            container.get(IMultipleImplementation.class);
            Assert.fail();
        }
        catch (JRoboContainerException ex)
        {
        }
    }

    @Test
    public void testInterfacesLevelsCreate() throws JRoboContainerException
    {
        Object result = container.create(ClassWithTwoInterfaces.class);
        Assert.assertTrue(result instanceof ClassWithTwoInterfaces);
        result = container.create(LowerInterface.class);
        Assert.assertTrue(result instanceof ClassWithTwoInterfaces);
        result = container.create(UpperInterface.class);
        Assert.assertTrue(result instanceof ClassWithTwoInterfaces);
    }


    @Test
    public void testMultiInterfaceCreate() throws JRoboContainerException
    {
        MultiInterface1 a = container.create(MultiInterface1.class);
        MultiInterface2 b = container.create(MultiInterface2.class);
        Assert.assertTrue(a != b);
    }

    @Test
    public void testMultiInterfaceGet() throws JRoboContainerException
    {
        MultiInterface1 a = container.get(MultiInterface1.class);
        MultiInterface2 b = container.get(MultiInterface2.class);
        Assert.assertTrue(a == b);
    }

    @Test
    public void testInterfacesLevelsGet() throws JRoboContainerException
    {
        Object result = container.get(ClassWithTwoInterfaces.class);
        Assert.assertTrue(result instanceof ClassWithTwoInterfaces);
        ((ClassWithTwoInterfaces) result).increment();
        result = container.get(LowerInterface.class);
        Assert.assertTrue(result instanceof ClassWithTwoInterfaces);
        ((ClassWithTwoInterfaces) result).increment();
        result = container.get(UpperInterface.class);
        Assert.assertTrue(result instanceof ClassWithTwoInterfaces);
        ((ClassWithTwoInterfaces) result).increment();
        Assert.assertEquals(((ClassWithTwoInterfaces) result).value, 3);
    }

    @Test
    public void testAbstractClasses() throws JRoboContainerException
    {
        container.create(AbstractClass.class);
    }
}