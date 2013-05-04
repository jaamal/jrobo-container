package Tests;

import TestBases.JRoboContainerTestBase;
import junit.framework.Assert;
import org.junit.Test;
import testclasses.factories.IIntsFactory;
import testclasses.factories.IStringsFactory;
import testclasses.factories.IntsClass;
import testclasses.factories.StringsClass;

public class FactoriesTests extends JRoboContainerTestBase
{
    @Test
    public void testStringFactory()
    {
        IStringsFactory factory = container.get(IStringsFactory.class);
        String string1 = "asdf";
        StringsClass result = factory.create(string1);
        Assert.assertEquals(result.getString(), string1);
    }

    @Test
    public void testSixStringsFactory()
    {
        IStringsFactory factory = container.get(IStringsFactory.class);
        String[] strings = new String[] {"a", "b", "c", "d", "e", "f"};
        StringsClass result = factory.create(strings[0], strings[1], strings[2], strings[3], strings[4], strings[5]);
        String[] actual = result.getStrings();
        Assert.assertEquals(actual.length, strings.length);
        for(int i = 0; i < actual.length; i++)
            Assert.assertEquals(actual[i], strings[i]);
    }

    @Test
    public void testIntFactory()
    {
        IIntsFactory factory = container.get(IIntsFactory.class);
        int x = 146;
        IntsClass result = factory.create(x);
        Assert.assertEquals(result.getX(), x);
    }
}
