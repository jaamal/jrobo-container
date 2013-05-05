package Tests;

import TestBases.JRoboContainerTestBase;
import junit.framework.Assert;
import org.junit.Test;
import testclasses.factories.*;

import java.util.ArrayList;

public class FactoriesTests extends JRoboContainerTestBase
{
    @Test
    public void testStringFactory()
    {
        IStringFactory factory = container.get(IStringFactory.class);
        String string1 = "146";
        StringClass result = factory.create(string1);
        Assert.assertEquals(result.getString(), string1);
    }

    @Test
    public void testIntFactory()
    {
        IIntPrimitiveFactory factory = container.get(IIntPrimitiveFactory.class);
        int x = 146;
        IntPrimitiveClass result = factory.create(x);
        Assert.assertEquals(result.getX(), x);
    }

    @Test
    public void testShortFactory()
    {
        IShortPrimitiveFactory factory = container.get(IShortPrimitiveFactory.class);
        short x = -73;
        ShortPrimitiveClass result = factory.create(x);
        Assert.assertEquals(result.getX(), x);
    }

    @Test
    public void testLongFactory()
    {
        ILongPrimitiveFactory factory = container.get(ILongPrimitiveFactory.class);
        long x = -(long)10000000 * 10000000;
        System.out.println(x);
        LongPrimitiveClass result = factory.create(x);
        Assert.assertEquals(result.getX(), x);
    }

    @Test
    public void testByteFactory()
    {
        IBytePrimitiveFactory factory = container.get(IBytePrimitiveFactory.class);
        byte x = 88;
        BytePrimitiveClass result = factory.create(x);
        Assert.assertEquals(result.getX(), x);
    }

    @Test
    public void testCharFactory()
    {
        ICharPrimitiveFactory factory = container.get(ICharPrimitiveFactory.class);
        char x = 'a';
        CharPrimitiveClass result = factory.create(x);
        Assert.assertEquals(result.getX(), x);
    }

    @Test
    public void testBooleanFactory()
    {
        IBooleanPrimitiveFactory factory = container.get(IBooleanPrimitiveFactory.class);
        boolean x = true;
        BooleanPrimitiveClass result = factory.create(x);
        Assert.assertEquals(result.getX(), x);
    }

    @Test
    public void testDoubleFactory()
    {
        IDoublePrimitiveFactory factory = container.get(IDoublePrimitiveFactory.class);
        double x = 3.14;
        DoublePrimitiveClass result = factory.create(x);
        Assert.assertEquals(result.getX(), x);
    }

    @Test
    public void testFloatFactory()
    {
        IFloatPrimitiveFactory factory = container.get(IFloatPrimitiveFactory.class);
        float x = (float)14.147;
        FloatPrimitiveClass result = factory.create(x);
        Assert.assertEquals(result.getX(), x);
    }


    @Test
    public void testStringArrayFactory()
    {
        IStringArrayFactory factory = container.get(IStringArrayFactory.class);
        String[] strings = new String[]{"a", "basdf", "asdfasdfkj", "bachelor", "degree"};
        StringArrayClass resultClass = factory.create(strings);
        String[] result = resultClass.getStrings();
        Assert.assertEquals(result.length, strings.length);
        for(int i = 0; i < result.length; i++)
            Assert.assertEquals(result[i], strings[i]);
    }

    @Test
    public void testStringCollectionFactory()
    {
        IStringCollectionFactory factory = container.get(IStringCollectionFactory.class);
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        StringCollectionClass resultClass = factory.create(strings);
        ArrayList<String> result = resultClass.getStrings();
        Assert.assertEquals(result.size(), strings.size());
        for(int i = 0; i < result.size(); i++)
            Assert.assertEquals(result.get(i), strings.get(i));
    }

    @Test
    public void testIntPrimitiveArrayFactory()
    {
        IIntPrimitiveArrayFactory factory = container.get(IIntPrimitiveArrayFactory.class);
        int[] ints = new int[50];
        for(int i = 0; i < ints.length; i++)
            ints[i] = i * i;
        IntPrimitiveArrayClass resultClass = factory.create(ints);
        int[] result = resultClass.getInts();
        Assert.assertEquals(result.length, ints.length);
        for(int i = 0; i < result.length; i++)
            Assert.assertEquals(result[i], ints[i]);
    }

    @Test
    public void testIntPrimitive2DArrayFactory()
    {
        IIntPrimitive2DArrayFactory factory = container.get(IIntPrimitive2DArrayFactory.class);
        int[][] ints = new int[5][6];
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 6; j++)
                ints[i][j] = i * 6 + j;
        IntPrimitive2DArrayClass resultClass = factory.create(ints);
        int[][] result = resultClass.getInts();
        Assert.assertEquals(result.length, ints.length);
        for(int i = 0; i < result.length; i++)
        {
            Assert.assertEquals(result[i].length, ints[i].length);
            for(int j = 0; j < result[i].length; j++)
                Assert.assertEquals(result[i][j], ints[i][j]);
        }
    }

    @Test
    public void testSameFactories()
    {
        IStringFactory factory1 = container.get(IStringFactory.class);
        IStringFactory factory2 = container.get(IStringFactory.class);
        Assert.assertSame(factory1, factory2);
    }
}
