package overclocking.jrobocontainer.configurations;

import overclocking.jrobocontainer.classloader.Resolver;
import overclocking.jrobocontainer.exceptions.JRoboContainerException;
import overclocking.jrobocontainer.injectioncontext.IInjectionContext;
import overclocking.jrobocontainer.storage.IStorage;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 06.10.12
 * Time: 13:38
 * To change this template use File | Settings | File Templates.
 */
public class AutoConfiguration extends AbstractConfiguration {

    private Object instance;

    public AutoConfiguration(IStorage storage, Class<?> abstraction) {
        this.storage = storage;
        this.abstraction = abstraction;
        instance = null;
    }

    @SuppressWarnings("unchecked")
    public <T> T innerGet(IInjectionContext injectionContext){
        try {
            if(instance != null)
            {
                return (T)instance;
            }
            Class<?> resolvedClass = Resolver.resolveClass(abstraction, storage.getImplementations(abstraction));
            synchronized (storage.getSynchronizeObject(resolvedClass)) {
                if(resolvedClass == abstraction)
                    instance = getInstance(resolvedClass, injectionContext);
                else
                    instance = storage.getConfiguration(resolvedClass).get(injectionContext);
            }
            return (T)instance;
        } catch (JRoboContainerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new JRoboContainerException("Failed to get " + abstraction.getName(), ex);
        }
    }

    public <T> T innerCreate(IInjectionContext injectionContext) {
        try {
            Class<?> resolvedClass = Resolver.resolveClass(abstraction, storage.getImplementations(abstraction));
            return (T)getInstance(resolvedClass, injectionContext);
        } catch (JRoboContainerException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new JRoboContainerException("Failed to create " + abstraction.getName(), ex);
        }
    }
}
