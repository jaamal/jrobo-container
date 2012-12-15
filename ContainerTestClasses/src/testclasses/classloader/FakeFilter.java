package testclasses.classloader;

import classloader.IJarsFilter;

public class FakeFilter implements IJarsFilter
{
    @Override
    public boolean acceptJar(String jarName)
    {
        return true;
    }
}
