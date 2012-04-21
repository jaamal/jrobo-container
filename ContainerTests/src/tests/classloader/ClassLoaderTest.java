package tests.classloader;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import tests.files.classloader.innerclasstest.OuterClass;
import tests.files.classloader.innerclasstest.OuterClass.InnerClass;
import tests.files.classloader.simpletest.IOneImplementation;
import tests.files.classloader.simpletest.OneImplementation;

import container.JRoboClassLoader;
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
				"bin/tests/files/classloader/privateclasstest/");
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
