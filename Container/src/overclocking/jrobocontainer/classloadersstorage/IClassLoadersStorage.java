package overclocking.jrobocontainer.classloadersstorage;

public interface IClassLoadersStorage
{
    ClassLoader getClassLoaderFor(String className);
}
