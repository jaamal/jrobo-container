package Tests;

import TestBases.JRoboContainerTestBase;
import junit.framework.Assert;
import org.junit.Test;
import overclocking.jrobocontainer.classloader.IClassLoaderConfiguration;
import overclocking.jrobocontainer.classloader.JRoboClassLoader;
import overclocking.jrobocontainer.logging.ClassesLoadingLog;
import testclasses.classloader.FakeFilter;
import testclasses.classloader.innerclasstest.OuterClass;
import testclasses.classloader.simpletest.IOneImplementation;
import testclasses.classloader.simpletest.OneImplementation;
import testclasses.fakestorages.SimpleStorage;

import java.util.ArrayList;

public class ClassLoaderTests extends JRoboContainerTestBase
{
    private SimpleStorage storage;
    private IClassLoaderConfiguration fakeFilter;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        storage = new SimpleStorage();
    }

    @Test
    public void testSimpleFolder()
    {
        JRoboClassLoader loader = new JRoboClassLoader(storage, new FakeFilter("out/production/ContainerTestClasses/testclasses/classloader/simpletest/"));
        loader.loadClasses(new ClassesLoadingLog());
        ArrayList<Class<?>> actual = storage.getLoadedClasses();
        ArrayList<Class<?>> expected = new ArrayList<Class<?>>();
        expected.add(IOneImplementation.class);
        expected.add(OneImplementation.class);
        Assert.assertTrue(arrayListsIdentical(actual, expected));
    }

    @Test
    public void testFolderWithInnerClass()
    {
        JRoboClassLoader loader = new JRoboClassLoader(storage, new FakeFilter("out/production/ContainerTestClasses/testclasses/classloader/innerclasstest/"));
        loader.loadClasses(new ClassesLoadingLog());
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