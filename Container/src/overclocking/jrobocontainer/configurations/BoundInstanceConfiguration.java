package overclocking.jrobocontainer.configurations;

import overclocking.jrobocontainer.classscanning.Resolver;
import overclocking.jrobocontainer.exceptions.JRoboContainerException;
import overclocking.jrobocontainer.injectioncontext.IInjectionContext;
import overclocking.jrobocontainer.storages.ClassNode;
import overclocking.jrobocontainer.storages.IStorage;

public class BoundInstanceConfiguration extends AbstractConfiguration {
    private final IStorage storage;
    private final Object instance;

    public BoundInstanceConfiguration(IStorage storage, ClassNode abstraction, Object instance) {

        this.storage = storage;
        this.abstraction = abstraction;
        this.instance = instance;
    }

    public <T> T innerGet(IInjectionContext injectionContext){
        injectionContext.reuse(abstraction);
        return (T)instance;
    }

    public <T> T innerCreate(IInjectionContext injectionContext) throws JRoboContainerException {
        try {
            ClassNode resolvedClass = Resolver.resolveClass(abstraction, storage.getImplementations(abstraction));
            return (T)getInstance(resolvedClass, injectionContext);
        } catch (JRoboContainerException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new JRoboContainerException("Failed to create " + abstraction.getClassName(), injectionContext, ex);
        }
    }
}
