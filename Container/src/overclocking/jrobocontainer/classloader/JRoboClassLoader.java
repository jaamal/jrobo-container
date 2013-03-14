package overclocking.jrobocontainer.classloader;

import overclocking.jrobocontainer.loadingcontext.ILoadingContext;
import overclocking.jrobocontainer.storage.IStorage;

import java.io.File;

public class JRoboClassLoader
{
    private String classPath;
    private EntitiesWalker directoriesWalker;
    private IStorage storage;

    public JRoboClassLoader(IStorage storage, IClassLoaderConfiguration classLoaderConfiguration, ClassLoader mainClassLoader)
    {
        this.classPath = classLoaderConfiguration.getClassPaths();
        this.directoriesWalker = new EntitiesWalker(storage, classLoaderConfiguration, mainClassLoader);
        this.storage = storage;
    }

    public void loadClasses(ILoadingContext loadingContext)
    {
        for (int i = 0; i < 10; i++)
            for (String path : classPath.split(System.getProperty("path.separator")))
            {
                directoriesWalker.addFolder(new File(path), storage, loadingContext);
            }
        for (Class<?> clazz : storage.getAllClasses())
            loadingContext.appendToLog("!Loaded " + clazz.getName() + ", loader is " + clazz.getClassLoader());
        storage.buildExtendedInheritanceGraph();
    }
}
