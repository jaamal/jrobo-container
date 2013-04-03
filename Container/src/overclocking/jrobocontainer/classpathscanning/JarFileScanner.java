package overclocking.jrobocontainer.classpathscanning;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import overclocking.jrobocontainer.logging.ILoadingLog;
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

    public void scanJarFile(File file, ILoadingLog log)
    {
        String fileName = file.getName();
        log.beginScanFile(fileName);
        if (classPathScannerConfiguration.acceptsJar(file.getName()))
        {
            try
            {
                JarFile jarFile = new JarFile(file);
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements())
                {
                    scanJarEntry(jarFile, entries.nextElement(), log);
                }
            }
            catch (IOException e)
            {
                log.scanFileFail(e.getMessage());
            }
            finally
            {
                log.endScanFile();
            }
        }
        else
        {
            log.skipJarFile();
            log.endScanFile();
        }

    }

    private void scanJarEntry(JarFile file, JarEntry entry, ILoadingLog log)
    {
        String entryName = entry.getName();
        if(!entryName.endsWith(".class"))
        {
            return;
        }
        log.beginScanJarEntry(entryName);
        ClassParser classParser = new ClassParser(file.getName(), entryName);
        try
        {
            JavaClass javaClass = classParser.parse();
            storage.addClass(javaClass, log);
            log.scanEntrySuccess(javaClass.getClassName());
        }
        catch(Exception ex)
        {
            log.scanEntryFail(ex.getMessage());
        }
        finally
        {
            log.endScanJarEntry();
        }
    }

}
