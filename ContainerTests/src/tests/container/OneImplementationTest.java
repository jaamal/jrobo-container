package tests.container;
import org.junit.Assert;
import org.junit.Test;

import tests.files.classloader.simpletest.IOneImplementation;
import tests.files.classloader.simpletest.OneImplementation;
import tests.files.gettingandcreating.IForGetAndCreateTesting;


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
		IForGetAndCreateTesting result1 = container.get(IForGetAndCreateTesting.class);
		Assert.assertTrue(result1.inc() == 1);
		IForGetAndCreateTesting result2 = container.get(IForGetAndCreateTesting.class);
		Assert.assertTrue(result2.inc() == 2);
	}
	
	@Test
	public void testCreating() throws JRoboContainerException
	{
		IForGetAndCreateTesting result1 = container.create(IForGetAndCreateTesting.class);
		Assert.assertTrue(result1.inc() == 1);
		IForGetAndCreateTesting result2 = container.create(IForGetAndCreateTesting.class);
		Assert.assertTrue(result2.inc() == 1);
	}
	
	@Test
	public void testCreatingAndGetting() throws JRoboContainerException
	{
		IForGetAndCreateTesting result1 = container.create(IForGetAndCreateTesting.class);
		Assert.assertTrue(result1.inc() == 1);
		IForGetAndCreateTesting result2 = container.get(IForGetAndCreateTesting.class);
		Assert.assertTrue(result2.inc() == 1);
		IForGetAndCreateTesting result3 = container.create(IForGetAndCreateTesting.class);
		Assert.assertTrue(result3.inc() == 1);
		IForGetAndCreateTesting result4 = container.get(IForGetAndCreateTesting.class);
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
