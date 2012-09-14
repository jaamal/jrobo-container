package tests.multithread;

import container.IContainer;
import exceptions.JRoboContainerException;
import tests.files.classloader.simpletest.IOneImplementation;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 14.09.12
 * Time: 16:15
 * To change this template use File | Settings | File Templates.
 */
public class GetterThread extends Thread {

    private IContainer container;
    private IOneImplementation instance;

    public GetterThread(IContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        instance = container.get(IOneImplementation.class);
    }

    public IOneImplementation getInstance() {
        return instance;
    }
}
