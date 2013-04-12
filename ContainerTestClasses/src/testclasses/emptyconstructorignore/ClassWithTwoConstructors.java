package testclasses.emptyconstructorignore;

import testclasses.classloader.simpletest.IOneImplementation;

public class ClassWithTwoConstructors
{
    int x;
    public ClassWithTwoConstructors()
    {
        x = 0;
    }

    public ClassWithTwoConstructors(IOneImplementation oneImplementation)
    {
        x = 1;
    }

    public int getX()
    {
        return x;
    }
}
