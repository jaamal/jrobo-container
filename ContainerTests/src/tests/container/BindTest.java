package tests.container;

import container.Container;
import junit.framework.TestCase;
import org.junit.Test;


public class BindTest extends TestCase {

    private Container container;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        container = new Container(new NoSystemsFilter());
    }

    @Test
    public void testBind()
    {

    }

}


