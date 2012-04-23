package tests.container;
import org.junit.Assert;
import org.junit.Test;

import tests.files.classloader.simpletest.IOneImplementation;
import tests.files.classloader.simpletest.OneImplementation;
import tests.files.gettingandcreating.IIncrementer;


import container.Container;
import exceptions.JRoboContainerException;
import files.multipleimplementation.IMultipleImplementation;

import junit.framework.TestCase;


public class OneImplementationTest extends TestCase {
	
	private Container container;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		container = new Container();
	}
	@Test
	public void testSimple() throws JRoboContainerException
	{
		IOneImplementation result = container.get(IOneImplementation.class);
		Assert.assertTrue(result instanceof OneImplementation);
	}
	
	@Test
	public void testGetting() throws JRoboContainerException
	{
		IIncrementer result1 = container.get(IIncrementer.class);
		Assert.assertTrue(result1.inc() == 1);
		IIncrementer result2 = container.get(IIncrementer.class);
		Assert.assertTrue(result2.inc() == 2);
	}
	
	@Test
	public void testCreating() throws JRoboContainerException
	{
		IIncrementer result1 = container.create(IIncrementer.class);
		Assert.assertTrue(result1.inc() == 1);
		IIncrementer result2 = container.create(IIncrementer.class);
		Assert.assertTrue(result2.inc() == 1);
	}
	
	@Test
	public void testCreatingAndGetting() throws JRoboContainerException
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
	public void testException() throws JRoboContainerException
	{
		try
		{
			container.get(IMultipleImplementation.class);
			Assert.fail();
		}
		catch(JRoboContainerException ex)
		{
		}
	}
}
