package overclocking.jrobocontainer.classloaderconfigurations;

import overclocking.jrobocontainer.classloader.IClassLoaderConfiguration;

public class DefaultClassLoaderConfiguration implements IClassLoaderConfiguration
{
    @Override
    public boolean acceptJar(String jarName)
    {
        return false;
    }

    @Override
    public String getClassPaths()
    {
        return System.getProperty("java.class.path");
    }
}
