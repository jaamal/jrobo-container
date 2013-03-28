package overclocking.jrobocontainer.classloaderconfigurations;

import overclocking.jrobocontainer.classpathscanning.IClassPathScannerConfiguration;

public class DefaultClassScannerConfiguration implements IClassPathScannerConfiguration
{
    @Override
    public boolean acceptsJar(String jarName)
    {
        return false;
    }

    @Override
    public String getClassPaths()
    {
        return System.getProperty("java.class.path");
    }
}
