package overclocking.jrobocontainer.container;

import overclocking.jrobocontainer.classloader.IClassLoaderConfiguration;
import overclocking.jrobocontainer.classloader.JRoboClassLoader;
import overclocking.jrobocontainer.classloaderconfigurations.DefaultClassLoaderConfiguration;
import overclocking.jrobocontainer.configurations.BoundImplementationConfiguration;
import overclocking.jrobocontainer.configurations.BoundInstanceConfiguration;
import overclocking.jrobocontainer.injectioncontext.IInjectionContext;
import overclocking.jrobocontainer.injectioncontext.InjectionContext;
import overclocking.jrobocontainer.loadingcontext.LoadingContext;
import overclocking.jrobocontainer.storage.IStorage;
import overclocking.jrobocontainer.storage.Storage;

public class Container implements IContainer
{

    private JRoboClassLoader classLoader;
    private IStorage storage;
    private IInjectionContext lastInjectionContext;
    private LoadingContext loadingContext;
    private final Object lockObject = new Object();
    private IClassLoaderConfiguration classLoaderConfiguration;
    boolean initialized = false;

    public Container()
    {
        this(new DefaultClassLoaderConfiguration());
    }

    public Container(IClassLoaderConfiguration classLoaderConfiguration)
    {
        this.classLoaderConfiguration = classLoaderConfiguration;
    }

    private void initialize(ClassLoader mainClassLoader)
    {
        storage = new Storage();
        classLoader = new JRoboClassLoader(storage, classLoaderConfiguration, mainClassLoader);
        loadingContext = new LoadingContext(mainClassLoader);
        classLoader.loadClasses(loadingContext);
    }

    @Override
    public <T> T get(Class<T> requiredAbstraction)
    {
        synchronized (lockObject)
        {
            if(!initialized)
            {
                initialized = true;
                initialize(requiredAbstraction.getClassLoader());
            }
        }
        lastInjectionContext = new InjectionContext();
        return storage.getConfiguration(requiredAbstraction).get(lastInjectionContext);
    }

    @Override
    public <T> T create(Class<T> requiredAbstraction)
    {
        synchronized (lockObject)
        {
            if(!initialized)
            {
                initialized = true;
                initialize(requiredAbstraction.getClassLoader());
            }
        }
        lastInjectionContext = new InjectionContext();
        return storage.getConfiguration(requiredAbstraction).create(lastInjectionContext);
    }

    @Override
    public <T1, T2 extends T1> void bindInstance(Class<T1> abstraction, T2 instance)
    {
        synchronized (lockObject)
        {
            if(!initialized)
            {
                initialized = true;
                initialize(abstraction.getClassLoader());
            }
        }
        storage.setConfiguration(abstraction, new BoundInstanceConfiguration(storage, abstraction, instance));
    }

    @Override
    public <T1, T2 extends T1> void bindImplementation(Class<T1> abstraction, Class<T2> boundImplementation)
    {
        synchronized (lockObject)
        {
            if(!initialized)
            {
                initialized = true;
                initialize(abstraction.getClassLoader());
            }
        }
        storage.setConfiguration(abstraction, new BoundImplementationConfiguration(storage, abstraction, boundImplementation));
    }

    @Override
    public <T> T[] getAll(Class<T> requiredAbstraction)
    {
        synchronized (lockObject)
        {
            if(!initialized)
            {
                initialized = true;
                initialize(requiredAbstraction.getClassLoader());
            }
        }
        lastInjectionContext = new InjectionContext();
        return storage.getConfiguration(requiredAbstraction).getAll(lastInjectionContext);
    }

    @Override
    public String getClassesLoadingLog()
    {
        synchronized (lockObject)
        {
            if(!initialized)
            {
                initialized = true;
                initialize(null);
            }
        }
        return loadingContext.getLog();
    }

    @Override
    public String getLastLog()
    {
        synchronized (lockObject)
        {
            if(!initialized)
            {
                initialized = true;
                initialize(null);
            }
        }
        return lastInjectionContext.getLog();
    }
}
