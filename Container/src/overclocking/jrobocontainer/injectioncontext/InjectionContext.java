package overclocking.jrobocontainer.injectioncontext;

import overclocking.jrobocontainer.logging.IInjectionLog;
import overclocking.jrobocontainer.logging.InjectionLog;
import overclocking.jrobocontainer.storages.ClassNode;

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
    private IInjectionLog log;

    public InjectionContext()
    {
        processingClasses = new HashSet<String>();
        log = new InjectionLog();
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
}
