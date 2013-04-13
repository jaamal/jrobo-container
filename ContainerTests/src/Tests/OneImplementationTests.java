package Tests;

import TestBases.JRoboContainerTestBase;
import junit.framework.Assert;
import org.junit.Test;
import overclocking.jrobocontainer.container.IContainer;
import overclocking.jrobocontainer.exceptions.JroboContainerException;
import testclasses.classloader.simpletest.IOneImplementation;
import testclasses.classloader.simpletest.OneImplementation;
import testclasses.gettingcreating.IIncrementer;
import testclasses.multipleimplementation.IMultipleImplementation;
import testclasses.primitives.IntInConstructor;
import testclasses.singleimplementation.*;
import testclasses.staticfields.IClassWithStaticFields;


public class OneImplementationTests extends JRoboContainerTestBase
{
    @Test
    public void testGetContainer()
    {
        IContainer result = container.get(IContainer.class);
        Assert.assertSame(result, container);
    }

    @Test
    public void testGetsSameObject()
    {
        //System.out.println(container.getClassesLoadingLog());
        IOneImplementation result1 = container.get(IOneImplementation.class);
        IOneImplementation result2 = container.get(IOneImplementation.class);
        Assert.assertSame(result1, result2);
    }
    @Test
    public void testStaticFields()
    {
        IClassWithStaticFields result = container.get(IClassWithStaticFields.class);
        Assert.assertEquals(result.getX(), 146 * 146);
    }

    @Test
    public void testIntInConstructor()
    {
        IntInConstructor result = container.get(IntInConstructor.class);
    }

    @Test
    public void testSimple() throws JroboContainerException
    {
        IOneImplementation result = container.get(IOneImplementation.class);
        Assert.assertTrue(result instanceof OneImplementation);
    }

    @Test
    public void testGetting() throws JroboContainerException
    {
        IIncrementer result1 = container.get(IIncrementer.class);
        Assert.assertTrue(result1.inc() == 1);
        IIncrementer result2 = container.get(IIncrementer.class);
        Assert.assertTrue(result2.inc() == 2);
    }

    @Test
    public void testCreating() throws JroboContainerException
    {
        IIncrementer result1 = container.create(IIncrementer.class);
        Assert.assertTrue(result1.inc() == 1);
        IIncrementer result2 = container.create(IIncrementer.class);
        Assert.assertTrue(result2.inc() == 1);
    }

    @Test
    public void testCreatingAndGetting() throws JroboContainerException
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
    public void testException() throws JroboContainerException
    {
        try
        {
            container.get(IMultipleImplementation.class);
            Assert.fail();
        }
        catch (JroboContainerException ex)
        {
        }
    }

    @Test
    public void testInterfacesLevelsCreate() throws JroboContainerException
    {
        Object result = container.create(ClassWithTwoInterfaces.class);
        Assert.assertTrue(result instanceof ClassWithTwoInterfaces);
        result = container.create(LowerInterface.class);
        Assert.assertTrue(result instanceof ClassWithTwoInterfaces);
        result = container.create(UpperInterface.class);
        Assert.assertTrue(result instanceof ClassWithTwoInterfaces);
    }


    @Test
    public void testMultiInterfaceCreate() throws JroboContainerException
    {
        MultiInterface1 a = container.create(MultiInterface1.class);
        MultiInterface2 b = container.create(MultiInterface2.class);
        Assert.assertTrue(a != b);
    }

    @Test
    public void testMultiInterfaceGet() throws JroboContainerException
    {
        MultiInterface1 a = container.get(MultiInterface1.class);
        MultiInterface2 b = container.get(MultiInterface2.class);
        Assert.assertTrue(a == b);
    }

    @Test
    public void testInterfacesLevelsGet() throws JroboContainerException
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
    public void testAbstractClasses() throws JroboContainerException
    {
        container.create(AbstractClass.class);
    }
}