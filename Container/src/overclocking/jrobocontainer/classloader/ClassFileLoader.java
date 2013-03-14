package overclocking.jrobocontainer.classloader;

import overclocking.jrobocontainer.loadingcontext.ILoadingContext;
import overclocking.jrobocontainer.storage.IStorage;

import java.io.File;

public class ClassFileLoader
{

    IStorage storage;
    InnerClassLoader classLoader;

    public ClassFileLoader(IStorage storage, ClassLoader mainClassLoader)
    {
        this.storage = storage;
        classLoader = new InnerClassLoader(mainClassLoader);
    }

    public void load(File file, IStorage storage, ILoadingContext loadingContext)
    {
        Class<?> result = classLoader.getClassByPath(file.getAbsolutePath(), loadingContext);
        if (result != null)
        {
            storage.addClass(result, loadingContext);
            loadingContext.addClass(result);
        }
    }

}
