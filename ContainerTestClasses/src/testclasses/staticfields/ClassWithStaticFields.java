package testclasses.staticfields;

import testclasses.classloader.simpletest.IOneImplementation;

/**
 * Created with IntelliJ IDEA.
 * User: mr146
 * Date: 28.03.13
 * Time: 23:03
 * To change this template use File | Settings | File Templates.
 */
public class ClassWithStaticFields implements IClassWithStaticFields{
    private int x = 146 * 146;
    public ClassWithStaticFields(IOneImplementation oneImplementation)
    {
        System.out.println("asdf");
    }
    public int getX()
    {
        return x;
    }
}
