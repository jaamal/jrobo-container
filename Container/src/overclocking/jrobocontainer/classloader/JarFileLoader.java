package overclocking.jrobocontainer.classloader;

import overclocking.jrobocontainer.storage.IStorage;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarFileLoader
{

    IStorage storage;
    private IClassLoaderConfiguration entitiesFilter;

    public JarFileLoader(IStorage storage, IClassLoaderConfiguration entitiesFilter)
    {
        this.storage = storage;
        this.entitiesFilter = entitiesFilter;
    }

    public void load(File file)
    {
        try
        {
            if (entitiesFilter.acceptJar(file.getName()))
            {
                JarFile jarFile = new JarFile(file);
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements())
                {
                    loadJarEntry(jarFile, entries.nextElement());
                }
            }
        }
        catch (IOException e)
        {
        }

    }

    private void loadJarEntry(JarFile file, JarEntry entry)
    {
        try
        {
            if (entry.getName().endsWith(".class"))
            {
                Class<?> clazz = new InnerClassLoader().readClass(file
                        .getInputStream(entry));
                if (clazz != null)
                    storage.addClass(clazz);
                else
                {
                }
            }
        }
        catch (IOException e)
        {
        }
    }

}
