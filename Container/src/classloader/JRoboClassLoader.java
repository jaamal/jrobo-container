package classloader;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import storage.IStorage;

import java.io.File;

public class JRoboClassLoader
{

    String classPath;
    private IJarsFilter filter;
    EntitiesWalker directoriesWalker;
    Logger logger = LogManager.getLogger(JRoboClassLoader.class);

    public JRoboClassLoader(IStorage storage, String classPath, IJarsFilter entitiesFilter)
    {
        this.classPath = classPath;
        this.filter = entitiesFilter;
        this.directoriesWalker = new EntitiesWalker(storage, entitiesFilter);
    }

    public void loadClasses()
    {
        for (String path : classPath.split(System.getProperty("path.separator")))
        {
            directoriesWalker.addFolder(new File(path));
        }
    }

}
