package overclocking.jrobocontainer.classloader;

import overclocking.jrobocontainer.logging.IClassesLoadingLog;
import overclocking.jrobocontainer.storage.IStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ClassFileLoader
{

    IStorage storage;

    public ClassFileLoader(IStorage storage)
    {
        this.storage = storage;
    }

    public void load(File file, IClassesLoadingLog log)
    {
        try
        {
            Class<?> clazz = new InnerClassLoader().readClass(new FileInputStream(file), log);
            if (clazz != null)
            {
                storage.addClass(clazz);
                log.incrementCounter();
            }
        }
        catch (FileNotFoundException e)
        {
            log.append("Failed to load " + file.getAbsolutePath() + ": " + e.getMessage());
        }
    }

}
