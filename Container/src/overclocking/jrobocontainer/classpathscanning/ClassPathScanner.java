package overclocking.jrobocontainer.classpathscanning;

import overclocking.jrobocontainer.loadingcontext.ILoadingContext;
import overclocking.jrobocontainer.storages.IStorage;

import java.io.File;

public class ClassPathScanner
{
    private String classPath;
    private EntitiesWalker directoriesWalker;
    private IStorage storage;

    public ClassPathScanner(IStorage storage, IClassPathScannerConfiguration classPathScannerConfiguration)
    {
        this.classPath = classPathScannerConfiguration.getClassPaths();
        this.directoriesWalker = new EntitiesWalker(storage, classPathScannerConfiguration);
        this.storage = storage;
    }

    public void loadClasses(ILoadingContext loadingContext)
    {

        for (String path : classPath.split(System.getProperty("path.separator")))
        {
            directoriesWalker.addFolder(new File(path), loadingContext);
        }
        storage.buildExtendedInheritanceGraph();
    }
}
