package overclocking.jrobocontainer.loadingcontext;

import overclocking.jrobocontainer.logging.ClassesLoadingLog;
import overclocking.jrobocontainer.logging.IClassesLoadingLog;

import java.util.ArrayList;

public class LoadingContext implements ILoadingContext
{
    ArrayList<Class<?>> classes;
    ArrayList<byte[]> byteCodes;
    IClassesLoadingLog log;
    private ClassLoader mainClassLoader;

    public LoadingContext(ClassLoader mainClassLoader)
    {
        this.mainClassLoader = mainClassLoader;
        classes = new ArrayList<Class<?>>();
        log = new ClassesLoadingLog();
        byteCodes = new ArrayList<byte[]>();
    }

    public ClassLoader getMainClassLoader()
    {
        return mainClassLoader;
    }

    @Override
    public <T> void addClass(Class<T> clazz)
    {
        classes.add(clazz);
        log.incrementCounter();
    }

    @Override
    public void addByteCode(byte[] byteCode)
    {
        byteCodes.add(byteCode);
    }

    @Override
    public ArrayList<byte[]> getByteCodes()
    {
        return byteCodes;
    }

    @Override
    public void appendToLog(String message)
    {
        log.append(message);
    }

    @Override
    public String getLog()
    {
        return log.getLog();
    }

    @Override
    public ArrayList<Class<?>> getClasses()
    {
        return classes;
    }
}
