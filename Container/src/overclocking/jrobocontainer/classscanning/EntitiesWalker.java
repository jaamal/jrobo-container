package overclocking.jrobocontainer.classscanning;

import overclocking.jrobocontainer.loadingcontext.ILoadingContext;
import overclocking.jrobocontainer.storages.IStorage;

import java.io.File;

public class EntitiesWalker
{
    private ClassFileScanner classFileLoader;
    private JarFileScanner jarFileLoader;

    public EntitiesWalker(IStorage storage, IClassPathScannerConfiguration jarsFilter)
    {
        classFileLoader = new ClassFileScanner(storage);
        jarFileLoader = new JarFileScanner(storage, jarsFilter);
    }

    public void addFolder(File currentLocation, ILoadingContext loadingContext)
    {
        if (currentLocation.isFile())
            addFile(currentLocation, loadingContext);
        else
        {
            loadingContext.appendToLog("Now in " + currentLocation.getAbsolutePath());
            for (File nextLocation : currentLocation.listFiles())
            {
                addFolder(nextLocation, loadingContext);
            }
        }
    }

    private void addFile(File file, ILoadingContext loadingContext)
    {
        loadingContext.appendToLog("Trying to add " + file.getAbsolutePath());
        if (file.getName().endsWith(".class"))
            classFileLoader.load(file, loadingContext);
        if (file.getName().endsWith(".jar"))
            jarFileLoader.load(file, loadingContext);
    }
}
