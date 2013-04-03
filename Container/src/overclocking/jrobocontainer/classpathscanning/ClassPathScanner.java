package overclocking.jrobocontainer.classpathscanning;

import overclocking.jrobocontainer.logging.ILoadingLog;
import overclocking.jrobocontainer.storages.IStorage;

import java.io.File;

public class ClassPathScanner
{
    private IClassPathScannerConfiguration classPathScannerConfiguration;
    private EntitiesScanner directoriesScanner;
    private IStorage storage;

    public ClassPathScanner(IStorage storage, IClassPathScannerConfiguration classPathScannerConfiguration)
    {
        this.classPathScannerConfiguration = classPathScannerConfiguration;
        this.directoriesScanner = new EntitiesScanner(storage, classPathScannerConfiguration);
        this.storage = storage;
    }

    public void loadClasses(ILoadingLog log)
    {
        String classPath = classPathScannerConfiguration.getClassPaths();
        for (String path : classPath.split(System.getProperty("path.separator")))
        {
            File directory = new File(path);
            if(directory != null && directory.isDirectory())
            {
                log.beginScanDirectory(path);
                directoriesScanner.addDirectory(new File(path), log);
                log.endScanDirectory();
            }
        }
        storage.buildExtendedInheritanceGraph();
    }
}
