package tests.classloader;

import java.util.ArrayList;

import org.junit.Test;

import classloader.JRoboClassLoader;

import tests.files.classloader.innerclasstest.OuterClass;
import tests.files.classloader.simpletest.IOneImplementation;
import tests.files.classloader.simpletest.OneImplementation;

import container.SimpleStorage;
import junit.framework.Assert;
import junit.framework.TestCase;

public class ClassLoaderTest extends TestCase {
	private SimpleStorage storage;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		storage = new SimpleStorage();
	}

	@Test
	public void testSimpleJar() {
	}

	@Test
	public void testSimpleFolder() {
		JRoboClassLoader loader = new JRoboClassLoader(storage,
				"bin/tests/files/classloader/simpletest/");
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
				"bin/tests/files/classloader/innerclasstest/");
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
