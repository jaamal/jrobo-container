package overclocking.jrobocontainer.classloadersstorage;

public interface IClassLoadersStorage
{
    Class<?> getClassLoaderFor(String className);
}
