package overclocking.jrobocontainer.classloaderconfigurations;

import overclocking.jrobocontainer.classloader.IClassLoaderConfiguration;

public class CustomDirectoryClassLoaderConfiguration implements IClassLoaderConfiguration
{
    private String classPath;

    public CustomDirectoryClassLoaderConfiguration(String classPath)
    {
        this.classPath = classPath;
    }

    @Override
    public boolean acceptJar(String jarName)
    {
        return false;
    }

    @Override
    public String getClassPaths()
    {
        return classPath;
    }
}
