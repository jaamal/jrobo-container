package tests.classloader;

import classloader.IPathsFilter;
import classloader.JRoboClassLoader;
import container.SimpleStorage;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import tests.files.classloader.innerclasstest.OuterClass;
import tests.files.classloader.simpletest.IOneImplementation;
import tests.files.classloader.simpletest.OneImplementation;

import java.io.File;
import java.util.ArrayList;

public class ClassLoaderTest extends TestCase {
	private SimpleStorage storage;
    private IPathsFilter fakeFilter;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		storage = new SimpleStorage();
        fakeFilter = new FakeFilter();
	}

	@Test
	public void testSimpleJar() {
        File tst = new File("out/production/ContainerTests/tests/files/classloader/simpletest");
        System.out.println(tst.getAbsolutePath());
        for(File s : tst.listFiles())
            System.out.println(s.getAbsolutePath());
	}

	@Test
	public void testSimpleFolder() {
		JRoboClassLoader loader = new JRoboClassLoader(storage,
				"out/production/ContainerTests/tests/files/classloader/simpletest/", fakeFilter);
		loader.loadClasses();
		ArrayList<Class<?>> actual = storage.getLoadedClasses();
		ArrayList<Class<?>> expected = new ArrayList<Class<?>>();
		expected.add(IOneImplementation.class);
		expected.add(OneImplementation.class);
		Assert.assertTrue(arrayListsIdentical(actual, expected));
	}

	@Test
	public void testJarWithInnerClass() {
	}

	@Test
	public void testFolderWithInnerClass() {
		JRoboClassLoader loader = new JRoboClassLoader(storage,
				"out/production/ContainerTests/tests/files/classloader/innerclasstest/", fakeFilter);
		loader.loadClasses();
		ArrayList<Class<?>> actual = storage.getLoadedClasses();
		ArrayList<Class<?>> expected = new ArrayList<Class<?>>();
		expected.add(OuterClass.class);
		Assert.assertTrue(arrayListsIdentical(actual, expected));
	}

	private boolean arrayListsIdentical(ArrayList<Class<?>> actual,
			ArrayList<Class<?>> expected) {
		return actual.containsAll(expected) && expected.containsAll(actual);
	}
}

