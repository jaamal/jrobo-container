package overclocking.jrobocontainer.container;

import overclocking.jrobocontainer.classloader.IClassLoaderConfiguration;
import overclocking.jrobocontainer.classloader.JRoboClassLoader;
import overclocking.jrobocontainer.classloaderconfigurations.DefaultClassLoaderConfiguration;
import overclocking.jrobocontainer.configurations.BoundImplementationConfiguration;
import overclocking.jrobocontainer.configurations.BoundInstanceConfiguration;
import overclocking.jrobocontainer.injectioncontext.IInjectionContext;
import overclocking.jrobocontainer.injectioncontext.InjectionContext;
import overclocking.jrobocontainer.storage.IStorage;
import overclocking.jrobocontainer.storage.Storage;

public class Container implements IContainer
{

    private JRoboClassLoader classLoader;
    private IStorage storage;

    public Container()
    {
        this(new DefaultClassLoaderConfiguration());
    }

    public Container(IClassLoaderConfiguration classLoaderConfiguration)
    {
        storage = new Storage();
        classLoader = new JRoboClassLoader(storage, classLoaderConfiguration);
        classLoader.loadClasses();
        storage.buildExtendedInheritanceGraph();
    }

    @Override
    public <T> T get(Class<T> requiredAbstraction)
    {
        IInjectionContext injectionContext = new InjectionContext();
        return storage.getConfiguration(requiredAbstraction).get(injectionContext);
    }

    @Override
    public <T> T create(Class<T> requiredAbstraction)
    {
        IInjectionContext injectionContext = new InjectionContext();
        return storage.getConfiguration(requiredAbstraction).create(injectionContext);
    }

    @Override
    public <T1, T2 extends T1> void bindInstance(Class<T1> abstraction, T2 instance)
    {
        storage.setConfiguration(abstraction, new BoundInstanceConfiguration(storage, abstraction, instance));
    }

    @Override
    public <T1, T2 extends T1> void bindImplementation(Class<T1> abstraction, Class<T2> boundImplementation)
    {
        storage.setConfiguration(abstraction, new BoundImplementationConfiguration(storage, abstraction, boundImplementation));
    }

    @Override
    public <T> T[] getAll(Class<T> requiredAbstraction)
    {
        IInjectionContext injectionContext = new InjectionContext();
        return storage.getConfiguration(requiredAbstraction).getAll(injectionContext);
    }
}
