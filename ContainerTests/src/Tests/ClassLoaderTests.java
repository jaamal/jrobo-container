package Tests;

import TestBases.JRoboContainerTestBase;
import classloader.IJarsFilter;
import classloader.JRoboClassLoader;
import junit.framework.Assert;
import org.junit.Test;
import testclasses.classloader.FakeFilter;
import testclasses.classloader.innerclasstest.OuterClass;
import testclasses.classloader.simpletest.IOneImplementation;
import testclasses.classloader.simpletest.OneImplementation;
import testclasses.fakestorages.SimpleStorage;

import java.util.ArrayList;

public class ClassLoaderTests extends JRoboContainerTestBase
{
    private SimpleStorage storage;
    private IJarsFilter fakeFilter;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        storage = new SimpleStorage();
        fakeFilter = new FakeFilter();
    }

    @Test
    public void testSimpleFolder()
    {
        JRoboClassLoader loader = new JRoboClassLoader(storage,
                "out/production/ContainerTestClasses/testclasses/classloader/simpletest/", fakeFilter);
        loader.loadClasses();
        ArrayList<Class<?>> actual = storage.getLoadedClasses();
        ArrayList<Class<?>> expected = new ArrayList<Class<?>>();
        expected.add(IOneImplementation.class);
        expected.add(OneImplementation.class);
        Assert.assertTrue(arrayListsIdentical(actual, expected));
    }

    @Test
    public void testFolderWithInnerClass()
    {
        JRoboClassLoader loader = new JRoboClassLoader(storage,
                "out/production/ContainerTestClasses/testclasses/classloader/innerclasstest/", fakeFilter);
        loader.loadClasses();
        ArrayList<Class<?>> actual = storage.getLoadedClasses();
        ArrayList<Class<?>> expected = new ArrayList<Class<?>>();
        expected.add(OuterClass.class);
        Assert.assertTrue(arrayListsIdentical(actual, expected));
    }

    private boolean arrayListsIdentical(ArrayList<Class<?>> actual,
                                        ArrayList<Class<?>> expected)
    {
        return actual.containsAll(expected) && expected.containsAll(actual);
    }
}