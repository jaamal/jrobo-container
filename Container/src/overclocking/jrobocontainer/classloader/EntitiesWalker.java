package overclocking.jrobocontainer.classloader;

import overclocking.jrobocontainer.loadingcontext.ILoadingContext;
import overclocking.jrobocontainer.storage.IStorage;

import java.io.File;

public class EntitiesWalker
{
    private ClassFileLoader classFileLoader;
    private JarFileLoader jarFileLoader;

    public EntitiesWalker(IStorage storage, IClassLoaderConfiguration jarsFilter, ClassLoader mainClassLoader)
    {
        classFileLoader = new ClassFileLoader(storage, mainClassLoader);
        jarFileLoader = new JarFileLoader(storage, jarsFilter);
    }

    public void addFolder(File currentLocation, IStorage storage, ILoadingContext loadingContext)
    {
        if (currentLocation.isFile())
            addFile(currentLocation, storage, loadingContext);
        else
        {
            loadingContext.appendToLog("Now in " + currentLocation.getAbsolutePath());
            for (File nextLocation : currentLocation.listFiles())
            {
                addFolder(nextLocation, storage, loadingContext);
            }
        }
    }

    private void addFile(File file, IStorage storage, ILoadingContext loadingContext)
    {
        loadingContext.appendToLog("Trying to add " + file.getAbsolutePath());
        if (file.getName().endsWith(".class"))
            classFileLoader.load(file, storage, loadingContext);
        if (file.getName().endsWith(".jar"))
            jarFileLoader.load(file, storage, loadingContext);
    }
}
