package overclocking.jrobocontainer.classloader;

public interface IClassLoaderConfiguration
{
    boolean acceptJar(String jarName);

    String getClassPaths();
}
