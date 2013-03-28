package overclocking.jrobocontainer.injectioncontext;

import overclocking.jrobocontainer.classloadersstorage.IClassLoadersStorage;
import overclocking.jrobocontainer.logging.ILog;
import overclocking.jrobocontainer.logging.Log;
import overclocking.jrobocontainer.storages.ClassNode;
import overclocking.jrobocontainer.storages.IClassNodesStorage;

import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: mr146
 * Date: 01.03.13
 * Time: 0:30
 * To change this template use File | Settings | File Templates.
 */
public class InjectionContext implements IInjectionContext
{

    private HashSet<String> processingClasses;
    private ILog log;
    private IClassLoadersStorage classLoadersStorage;
    private IClassNodesStorage classNodesStorage;

    public InjectionContext(IClassLoadersStorage classLoadersStorage, IClassNodesStorage classNodesStorage)
    {
        this.classLoadersStorage = classLoadersStorage;
        this.classNodesStorage = classNodesStorage;
        processingClasses = new HashSet<String>();
        log = new Log();
    }

    @Override
    public boolean isClassProcessing(String clazz)
    {
        return processingClasses.contains(clazz);
    }

    @Override
    public void markClassAsProcessing(String clazz)
    {
        processingClasses.add(clazz);
    }

    @Override
    public void markClassAsNotProcessing(String clazz)
    {
        processingClasses.remove(clazz);
    }

    @Override
    public void beginGet(ClassNode clazz)
    {
        log.beginGet(clazz);
    }

    @Override
    public void endGet(ClassNode clazz)
    {
        log.endGet(clazz);
    }

    @Override
    public void beginCreate(ClassNode clazz)
    {
        log.beginCreate(clazz);
    }

    @Override
    public void endCreate(ClassNode clazz)
    {
        log.endCreate(clazz);
    }

    @Override
    public void beginGetAll(ClassNode clazz)
    {
        log.beginGetAll(clazz);
    }

    @Override
    public void endGetAll(ClassNode clazz)
    {
        log.endGetAll(clazz);
    }

    @Override
    public void reuse(ClassNode clazz)
    {
        log.reuse(clazz);
    }

    @Override
    public String getLog()
    {
        return log.getLog();
    }

    @Override
    public IClassLoadersStorage getClassLoadersStorage()
    {
        return classLoadersStorage;
    }

    @Override
    public IClassNodesStorage getClassNodesStorage()
    {
        return classNodesStorage;
    }
}
