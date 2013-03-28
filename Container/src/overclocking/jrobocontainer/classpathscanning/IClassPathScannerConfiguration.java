package overclocking.jrobocontainer.classpathscanning;

public interface IClassPathScannerConfiguration
{
    boolean acceptsJar(String jarName);

    String getClassPaths();
}
