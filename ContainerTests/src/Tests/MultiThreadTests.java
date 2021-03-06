package Tests;

import TestBases.JRoboContainerTestBase;
import testclasses.classloader.simpletest.IOneImplementation;
import junit.framework.Assert;
import testclasses.multithread.GetterThread;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 14.09.12
 * Time: 16:11
 * To change this template use File | Settings | File Templates.
 */
public class MultiThreadTests extends JRoboContainerTestBase
{

    public void testSimpleMultiThread() throws InterruptedException {
        int threadsCount = 10;
        GetterThread[] threads = new GetterThread[threadsCount];
        for (int i = 0; i < threadsCount; i++)
            threads[i] = new GetterThread(container);
        for (int i = 0; i < threadsCount; i++)
            threads[i].start();
        for (int i = 0; i < threadsCount; i++)
            threads[i].join();
        IOneImplementation[] instances = new IOneImplementation[threadsCount];
        for (int i = 0; i < threadsCount; i++) {
            instances[i] = threads[i].getInstance();
            Assert.assertNotNull(instances[i]);
        }
        for (int i = 0; i < threadsCount - 1; i++)
            Assert.assertSame(instances[i], instances[i + 1]);
    }
}