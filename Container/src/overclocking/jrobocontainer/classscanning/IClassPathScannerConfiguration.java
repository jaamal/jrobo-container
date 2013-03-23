package overclocking.jrobocontainer.classscanning;

public interface IClassPathScannerConfiguration
{
    boolean acceptsJar(String jarName);

    String getClassPaths();
}
