package classloader;

import storage.IStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ClassFileLoader
{

    IStorage storage;
    private IEntitiesFilter entitiesFilter;

    public ClassFileLoader(IStorage storage, IEntitiesFilter entitiesFilter)
    {
        this.storage = storage;
        this.entitiesFilter = entitiesFilter;
    }

    public void load(File file)
    {
        try
        {
            Class<?> clazz = new InnerClassLoader().readClass(new FileInputStream(file));
            if (clazz != null && entitiesFilter.acceptClass(clazz.getCanonicalName()))
                storage.addClass(clazz);
        }
        catch (FileNotFoundException e)
        {
        }
    }

}
