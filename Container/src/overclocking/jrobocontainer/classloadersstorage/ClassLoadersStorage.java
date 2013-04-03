package overclocking.jrobocontainer.classloadersstorage;

public class ClassLoadersStorage implements IClassLoadersStorage
{
    @Override
    public <T> Class<T> loadClass(ClassLoader classLoader, String className)     {
        try
        {
            return (Class<T>)classLoader.loadClass(className);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }
}
