package overclocking.jrobocontainer.injectioncontext;

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
    public InjectionContext()
    {
        processingClasses = new HashSet<Class<?>>();
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
}
