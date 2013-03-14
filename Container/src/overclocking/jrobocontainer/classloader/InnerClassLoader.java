package overclocking.jrobocontainer.classloader;

import overclocking.jrobocontainer.loadingcontext.ILoadingContext;

import java.io.FileInputStream;
import java.io.InputStream;

public class InnerClassLoader extends ClassLoader
{
    private static ClassLoader detectParent()
    {
        ClassLoader result = InnerClassLoader.class.getClassLoader();
        if (result == null)
        {
            result = ClassLoader.getSystemClassLoader();
        }
        return result;
    }

    public InnerClassLoader(ClassLoader mainClassLoader)
    {
        super(mainClassLoader == null ? ClassLoader.getSystemClassLoader() : mainClassLoader);
    }

    private <T> Class<T> getClassByName(String name, ILoadingContext loadingContext)
    {
        loadingContext.appendToLog("InnerClassLoader's classloader is " + detectParent().getParent());
        try
        {
            Class<T> result = (Class<T>) loadClass(name);
            loadingContext.appendToLog(name + " loaded successfully");
            return result;
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
            loadingContext.appendToLog("Failed to load " + name + ": " + e.getMessage());
            return null;
        }
    }

    public <T> Class<T> getClassByPath(String path, ILoadingContext loadingContext)
    {
        String[] pathParts = path.split("\\\\");
        String current = "";
        Class<T> result;
        for (int i = pathParts.length - 1; i >= 0; i--)
        {
            if (!current.isEmpty())
                current = "." + current;
            current = pathParts[i] + current;
            result = getClassByName(current.substring(0, current.length() - 6), loadingContext);
            if (result != null)
                return result;
        }
        loadingContext.appendToLog("Failed to load class by path " + path + " by system loader");
        byte[] bytes = readAllBytes(path);
        if (bytes == null)
            return null;
        try
        {
            result = (Class<T>) defineClass(null, bytes, 0, bytes.length);
            System.out.println(result + " defined");
        }
        catch (NoClassDefFoundError e)
        {
            return null;
        }
        return result;
    }

    private byte[] readAllBytes(String path)
    {
        try
        {
            InputStream inputStream = new FileInputStream(path);
            byte[] result = new byte[inputStream.available()];
            inputStream.read(result);
            return result;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
