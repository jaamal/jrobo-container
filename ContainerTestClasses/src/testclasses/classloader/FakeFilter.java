package testclasses.classloader;

import overclocking.jrobocontainer.classscanning.IClassPathScannerConfiguration;

public class FakeFilter implements IClassPathScannerConfiguration
{
    private String classPaths;

    public FakeFilter(String classPaths)
    {
        this.classPaths = classPaths;
    }

    @Override
    public boolean acceptsJar(String jarName)
    {
        return true;
    }

    @Override
    public String getClassPaths()
    {
        return classPaths;
    }
}
