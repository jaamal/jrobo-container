package overclocking.jrobocontainer.container;

import overclocking.jrobocontainer.classloaderconfigurations.DefaultClassScannerConfiguration;
import overclocking.jrobocontainer.classloadersstorage.ClassLoadersStorage;
import overclocking.jrobocontainer.classloadersstorage.IClassLoadersStorage;
import overclocking.jrobocontainer.classscanning.ClassPathScanner;
import overclocking.jrobocontainer.classscanning.IClassPathScannerConfiguration;
import overclocking.jrobocontainer.configurations.BoundImplementationConfiguration;
import overclocking.jrobocontainer.configurations.BoundInstanceConfiguration;
import overclocking.jrobocontainer.injectioncontext.IInjectionContext;
import overclocking.jrobocontainer.injectioncontext.InjectionContext;
import overclocking.jrobocontainer.loadingcontext.LoadingContext;
import overclocking.jrobocontainer.storages.*;

import java.lang.instrument.Instrumentation;

public class Container implements IContainer
{

    private IStorage storage;
    private IInjectionContext lastInjectionContext;
    private LoadingContext loadingContext;
    private IClassLoadersStorage classLoadersStorage;
    private IClassNodesStorage classNodesStorage;

    public Container(ClassLoader[] classLoaders)
    {
        this(new DefaultClassScannerConfiguration(), new ClassLoadersStorage(classLoaders));
    }

    public Container(Instrumentation instrumentation)
    {
        this(new DefaultClassScannerConfiguration(), new ClassLoadersStorage(instrumentation));
    }

    public Container(IClassPathScannerConfiguration classPathScannerConfiguration, ClassLoader[] classLoaders)
    {
        this(classPathScannerConfiguration, new ClassLoadersStorage(classLoaders));
    }

    public Container(IClassPathScannerConfiguration classPathScannerConfiguration, Instrumentation instrumentation)
    {
        this(classPathScannerConfiguration, new ClassLoadersStorage(instrumentation));
    }

    private Container(IClassPathScannerConfiguration classPathScannerConfiguration, IClassLoadersStorage classLoadersStorage)
    {
        this.classLoadersStorage = classLoadersStorage;
        classNodesStorage = new ClassNodesStorage();
        storage = new Storage(classNodesStorage);
        ClassPathScanner classPathScanner = new ClassPathScanner(storage, classPathScannerConfiguration);
        loadingContext = new LoadingContext();
        classPathScanner.loadClasses(loadingContext);
    }

    @Override
    public <T> T get(Class<T> requiredAbstraction)
    {
        lastInjectionContext = new InjectionContext(classLoadersStorage, classNodesStorage);
        return storage.getConfiguration(classNodesStorage.getClassNode(requiredAbstraction)).get(lastInjectionContext);
    }

    @Override
    public <T> T create(Class<T> requiredAbstraction)
    {
        lastInjectionContext = new InjectionContext(classLoadersStorage, classNodesStorage);
        return storage.getConfiguration(classNodesStorage.getClassNode(requiredAbstraction)).create(lastInjectionContext);
    }

    @Override
    public <T1, T2 extends T1> void bindInstance(Class<T1> abstraction, T2 instance)
    {
        ClassNode node = classNodesStorage.getClassNode(abstraction);
        storage.setConfiguration(node, new BoundInstanceConfiguration(storage, node, instance));
    }

    @Override
    public <T1, T2 extends T1> void bindImplementation(Class<T1> abstraction, Class<T2> boundImplementation)
    {
        ClassNode abstractionNode = classNodesStorage.getClassNode(abstraction);
        ClassNode implNode = classNodesStorage.getClassNode(boundImplementation);
        storage.setConfiguration(abstractionNode, new BoundImplementationConfiguration(storage, abstractionNode, implNode));
    }

    @Override
    public <T> T[] getAll(Class<T> requiredAbstraction)
    {
        lastInjectionContext = new InjectionContext(classLoadersStorage, classNodesStorage);
        ClassNode abstractionNode = classNodesStorage.getClassNode(requiredAbstraction);
        return storage.getConfiguration(abstractionNode).getAll(lastInjectionContext);
    }

    @Override
    public String getClassesLoadingLog()
    {
        return loadingContext.getLog();
    }

    @Override
    public String getLastLog()
    {
        return lastInjectionContext.getLog();
    }
}
