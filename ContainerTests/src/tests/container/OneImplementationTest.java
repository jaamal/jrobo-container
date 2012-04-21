package tests.container;
import org.junit.Assert;
import org.junit.Test;

import tests.files.classloader.simpletest.IOneImplementation;
import tests.files.classloader.simpletest.OneImplementation;


import container.Container;
import exceptions.JRoboContainerException;

import junit.framework.TestCase;


public class OneImplementationTest extends TestCase {
	
	private Container container;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		container = new Container();
	}
	@Test
	public void test() throws JRoboContainerException
	{
		IOneImplementation result = container.get(IOneImplementation.class);
		Assert.assertTrue(result instanceof OneImplementation);
	}
}
