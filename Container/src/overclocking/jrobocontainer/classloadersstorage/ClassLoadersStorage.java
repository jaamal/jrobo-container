package overclocking.jrobocontainer.classloadersstorage;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
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
    }

    @Override
    public ClassLoader getClassLoaderFor(String className)
    {
        ClassLoader result = tryGetClassLoaderFor(className);
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

    private ClassLoader tryGetClassLoaderFor(String className)
    {
        for(ClassLoader classLoader : classLoaders)
        {
            try
            {
                Method method = ClassLoader.class.getDeclaredMethod("findClass", String.class);
                method.setAccessible(true);
                if(method.invoke(classLoader, className) != null)
                    return classLoader;
            }
            catch (NoSuchMethodException e)
            {
                e.printStackTrace();
            }
            catch (InvocationTargetException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
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
