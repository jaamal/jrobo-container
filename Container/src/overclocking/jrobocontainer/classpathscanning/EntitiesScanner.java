package overclocking.jrobocontainer.classpathscanning;

import overclocking.jrobocontainer.logging.ILoadingLog;
import overclocking.jrobocontainer.storages.IStorage;

import java.io.File;

public class EntitiesScanner
{
    private ClassFileScanner classFileLoader;
    private JarFileScanner jarFileLoader;

    public EntitiesScanner(IStorage storage, IClassPathScannerConfiguration jarsFilter)
    {
        classFileLoader = new ClassFileScanner(storage);
        jarFileLoader = new JarFileScanner(storage, jarsFilter);
    }

    public void addDirectory(File currentLocation, ILoadingLog log)
    {
        if (currentLocation.isFile())
            addFile(currentLocation, log);
        else
        {
            log.beginScanDirectory(currentLocation.getName());
            for (File nextLocation : currentLocation.listFiles())
            {
                addDirectory(nextLocation, log);
            }
            log.endScanDirectory();
        }
    }

    private void addFile(File file, ILoadingLog log)
    {
        if (file.getName().endsWith(".class"))
            classFileLoader.scanFile(file, log);
        if (file.getName().endsWith(".jar"))
            jarFileLoader.load(file, log);
    }
}
