package overclocking.jrobocontainer.classloader;

import overclocking.jrobocontainer.loadingcontext.ILoadingContext;
import overclocking.jrobocontainer.storage.IStorage;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarFileLoader
{

    private IStorage storage;
    private IClassLoaderConfiguration entitiesFilter;
    private InnerClassLoader classLoader;

    public JarFileLoader(IStorage storage, IClassLoaderConfiguration entitiesFilter)
    {
        this.storage = storage;
        this.entitiesFilter = entitiesFilter;
        classLoader = new InnerClassLoader(null);
    }

    public void load(File file, IStorage storage, ILoadingContext loadingContext)
    {
        try
        {
            if (entitiesFilter.acceptJar(file.getName()))
            {
                JarFile jarFile = new JarFile(file);
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements())
                {
                    loadJarEntry(jarFile, entries.nextElement(), loadingContext);
                }
            }
        }
        catch (IOException e)
        {
        }

    }

    private void loadJarEntry(JarFile file, JarEntry entry, ILoadingContext loadingContext)
    {
        if (entry.getName().endsWith(".class"))
        {
            classLoader.getClassByPath(file.getName(), loadingContext);
        }
    }

}
