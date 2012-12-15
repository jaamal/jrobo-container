package testclasses.classloader;

import classloader.IEntitiesFilter;

public class FakeFilter implements IEntitiesFilter
{

    @Override
    public boolean acceptJar(String jarName)
    {
        return true;
    }

    @Override
    public boolean acceptClass(String className)
    {
        return true;
    }
}
