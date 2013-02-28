package overclocking.jrobocontainer.injectioncontext;

import overclocking.jrobocontainer.logging.ILog;
import overclocking.jrobocontainer.logging.Log;

import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: mr146
 * Date: 01.03.13
 * Time: 0:30
 * To change this template use File | Settings | File Templates.
 */
public class InjectionContext implements IInjectionContext {

    private HashSet<Class<?>> processingClasses;
    private ILog log;
    public InjectionContext()
    {
        processingClasses = new HashSet<Class<?>>();
        log = new Log();
    }
    @Override
    public boolean isClassProcessing(Class<?> clazz) {
        return processingClasses.contains(clazz);
    }

    @Override
    public void markClassAsProcessing(Class<?> clazz) {
        processingClasses.add(clazz);
    }

    @Override
    public void markClassAsNotProcessing(Class<?> clazz) {
        processingClasses.remove(clazz);
    }

    @Override
    public void beginGet(Class<?> clazz) {
        log.beginGet(clazz);
    }

    @Override
    public void endGet(Class<?> clazz) {
        log.endGet(clazz);
    }

    @Override
    public void beginCreate(Class<?> clazz) {
        log.beginCreate(clazz);
    }

    @Override
    public void endCreate(Class<?> clazz) {
        log.endCreate(clazz);
    }

    @Override
    public void beginGetAll(Class<?> clazz) {
        log.beginGetAll(clazz);
    }

    @Override
    public void endGetAll(Class<?> clazz) {
        log.endGetAll(clazz);
    }

    @Override
    public void reuse(Class<?> clazz) {
        log.reuse(clazz);
    }

    @Override
    public String getLog() {
        return log.getLog();
    }
}
