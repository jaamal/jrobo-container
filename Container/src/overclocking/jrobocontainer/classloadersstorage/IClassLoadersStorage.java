package overclocking.jrobocontainer.classloadersstorage;

public interface IClassLoadersStorage
{
    <T> Class<T> loadClass(ClassLoader classLoader, String className);
}
