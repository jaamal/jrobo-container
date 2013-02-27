package testclasses.classloader;

import classloader.IClassLoaderConfiguration;

public class FakeFilter implements IClassLoaderConfiguration
{
    private String classPaths;

    public FakeFilter(String classPaths)
    {
        this.classPaths = classPaths;
    }

    @Override
    public boolean acceptJar(String jarName)
    {
        return true;
    }

    @Override
    public String getClassPaths()
    {
        return classPaths;
    }
}
