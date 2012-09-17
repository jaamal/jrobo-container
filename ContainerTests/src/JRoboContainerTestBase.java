import container.Container;
import container.IContainer;
import filters.NoSystemsFilter;
import junit.framework.TestCase;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.Console;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 17.09.12
 * Time: 10:58
 * To change this template use File | Settings | File Templates.
 */
public abstract class JRoboContainerTestBase extends TestCase {
    @Override
    protected void setUp() throws Exception {
        DOMConfigurator.configure("log4j.xml");
        container = new Container(new NoSystemsFilter());
    }

    Logger logger = LogManager.getLogger(JRoboContainerTestBase.class);
    protected IContainer container;
}
