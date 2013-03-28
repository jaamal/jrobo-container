package overclocking.jrobocontainer.classloadersstorage;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.util.HashSet;

public class ClassLoadersStorage implements IClassLoadersStorage
{
    private ClassLoader[] classLoaders;
    private Instrumentation instrumentation;

    public ClassLoadersStorage(ClassLoader[] classLoaders)
    {
        this.classLoaders = classLoaders;
    }

    public ClassLoadersStorage(Instrumentation instrumentation)
    {
        this.instrumentation = instrumentation;
        classLoaders = getClassLoadersFromInstrumentation();
    }

    @Override
    public Class<?> getClassLoaderFor(String className)
    {
        Class<?> result = tryGetClassLoaderFor(className);
        if(result == null)
        {
            if(instrumentation != null)
            {
                classLoaders = getClassLoadersFromInstrumentation();
                return tryGetClassLoaderFor(className);
            }
        }
        return result;
    }

    private Class<?> tryGetClassLoaderFor(String className)
    {
        Method method = null;
        try {
            ClassLoader xxxx = getClass().getClassLoader();
            method = ClassLoader.class.getDeclaredMethod("findLoadedClass0", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        method.setAccessible(true);
        for(ClassLoader classLoader : classLoaders)
        {
            if(classLoader == null)
                continue;
            try
            {
                Class<?> result = classLoader.loadClass(className);
                if(result != null)
                    return result;
            }catch (ClassNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        System.out.println("asdfasdfasdf");
        return null;
    }

    private ClassLoader[] getClassLoadersFromInstrumentation()
    {
        Class[] classes = instrumentation.getAllLoadedClasses();
        HashSet<ClassLoader> result = new HashSet<ClassLoader>();
        for(Class clazz : classes)
            result.add(clazz.getClassLoader());
        return result.toArray(new ClassLoader[result.size()]);
    }
}
