package overclocking.jrobocontainer.classscanning;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import overclocking.jrobocontainer.loadingcontext.ILoadingContext;
import overclocking.jrobocontainer.storages.IStorage;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarFileScanner
{

    private IStorage storage;
    private IClassPathScannerConfiguration classPathScannerConfiguration;

    public JarFileScanner(IStorage storage, IClassPathScannerConfiguration entitiesFilter)
    {
        this.storage = storage;
        this.classPathScannerConfiguration = entitiesFilter;
    }

    public void load(File file, ILoadingContext loadingContext)
    {
        try
        {
            if (classPathScannerConfiguration.acceptsJar(file.getName()))
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
        if(!entry.getName().endsWith(".class"))
            return;
        ClassParser classParser = new ClassParser(file.getName(), entry.getName());
        try
        {
            JavaClass clazz = classParser.parse();
            storage.addClass(clazz, loadingContext);

        }
        catch(Exception ex)
        {
            loadingContext.appendToLog("Failed to read " + entry.getName() + " in " + file.getName() + ": " + ex.getMessage());
        }
    }

}
