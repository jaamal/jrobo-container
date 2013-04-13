package Tests;

import TestBases.JRoboContainerTestBase;
import junit.framework.Assert;
import org.junit.Test;
import overclocking.jrobocontainer.container.AbstractionInstancePair;
import testclasses.classloader.simpletest.IOneImplementation;
import testclasses.classloader.simpletest.OneImplementation;
import testclasses.primitivesinconstructor.IDifferentTypesInConstructor;
import testclasses.primitivesinconstructor.IIntInConstructor;
import testclasses.primitivesinconstructor.ITwoIntsInConstructor;

public class ParametrizedCreateTests extends JRoboContainerTestBase
{
    @Test
    public void testCreateWithInt()
    {
        IIntInConstructor result = container.create(IIntInConstructor.class, new AbstractionInstancePair(int.class, 5));
        Assert.assertEquals(result.getInt(), 5);
        result = container.create(IIntInConstructor.class, new AbstractionInstancePair(int.class, 146));
        Assert.assertEquals(result.getInt(), 146);
    }

    @Test
    public void testCreateWithTwoInts()
    {
        ITwoIntsInConstructor result = container.create(ITwoIntsInConstructor.class, new AbstractionInstancePair(int.class, 7), new AbstractionInstancePair(int.class, 8));
        Assert.assertEquals(result.getX(), 7);
        Assert.assertEquals(result.getY(), 8);
    }

    @Test
    public void testCreateWithManyDifferentTypes()
    {
        AbstractionInstancePair oneImplPair = new AbstractionInstancePair(OneImplementation.class, container.create(IOneImplementation.class));
        AbstractionInstancePair firstIntPair = new AbstractionInstancePair(int.class, 7);
        AbstractionInstancePair secondIntPair = new AbstractionInstancePair(int.class, 146);
        AbstractionInstancePair firstStringPair = new AbstractionInstancePair(String.class, "firstString");
        AbstractionInstancePair secondStringPair = new AbstractionInstancePair(String.class, "secondString");
        IDifferentTypesInConstructor result = container.create(IDifferentTypesInConstructor.class, oneImplPair, firstIntPair, secondIntPair, firstStringPair, secondStringPair);
        Assert.assertSame(result.getOneImplementation(), oneImplPair.getInstance());
        Assert.assertEquals(result.getFirstInt(), firstIntPair.getInstance());
        Assert.assertEquals(result.getSecondInt(), secondIntPair.getInstance());
        Assert.assertEquals(result.getFirstString(), firstStringPair.getInstance());
        Assert.assertEquals(result.getSecondString(), secondStringPair.getInstance());
        result = container.create(IDifferentTypesInConstructor.class, firstStringPair, secondStringPair, oneImplPair, firstIntPair, secondIntPair);
        Assert.assertSame(result.getOneImplementation(), oneImplPair.getInstance());
        Assert.assertEquals(result.getFirstInt(), firstIntPair.getInstance());
        Assert.assertEquals(result.getSecondInt(), secondIntPair.getInstance());
        Assert.assertEquals(result.getFirstString(), firstStringPair.getInstance());
        Assert.assertEquals(result.getSecondString(), secondStringPair.getInstance());
        result = container.create(IDifferentTypesInConstructor.class, oneImplPair, firstIntPair, firstStringPair, secondStringPair, secondIntPair);
        Assert.assertSame(result.getOneImplementation(), oneImplPair.getInstance());
        Assert.assertEquals(result.getFirstInt(), firstIntPair.getInstance());
        Assert.assertEquals(result.getSecondInt(), secondIntPair.getInstance());
        Assert.assertEquals(result.getFirstString(), firstStringPair.getInstance());
        Assert.assertEquals(result.getSecondString(), secondStringPair.getInstance());
    }
}
