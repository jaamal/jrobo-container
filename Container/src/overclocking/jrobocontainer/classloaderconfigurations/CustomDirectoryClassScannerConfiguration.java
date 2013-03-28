package overclocking.jrobocontainer.classloaderconfigurations;

import overclocking.jrobocontainer.classpathscanning.IClassPathScannerConfiguration;

public class CustomDirectoryClassScannerConfiguration implements IClassPathScannerConfiguration
{
    private String classPath;

    public CustomDirectoryClassScannerConfiguration(String classPath)
    {
        this.classPath = classPath;
    }

    @Override
    public boolean acceptsJar(String jarName)
    {
        return false;
    }

    @Override
    public String getClassPaths()
    {
        return classPath;
    }
}
