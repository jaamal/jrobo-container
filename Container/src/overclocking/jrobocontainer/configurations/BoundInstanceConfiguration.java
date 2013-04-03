package overclocking.jrobocontainer.configurations;

import overclocking.jrobocontainer.classpathscanning.Resolver;
import overclocking.jrobocontainer.exceptions.JRoboContainerException;
import overclocking.jrobocontainer.injectioncontext.IInjectionContext;
import overclocking.jrobocontainer.storages.ClassNode;
import overclocking.jrobocontainer.storages.IStorage;

public class BoundInstanceConfiguration extends AbstractConfiguration {
    private final IStorage storage;
    private final Object instance;

    public BoundInstanceConfiguration(IStorage storage, String abstractionId, Object instance) {

        this.storage = storage;
        this.abstractionId = abstractionId;
        this.instance = instance;
    }

    public <T> T innerGet(IInjectionContext injectionContext, ClassLoader classlo
    ){
        ClassNode abstraction = injectionContext.getClassNodesStorage().getClassNodeById(abstractionId);
        injectionContext.reuse(abstraction);
        return (T)instance;
    }

    public <T> T innerCreate(IInjectionContext injectionContext, ClassLoader classLoader) throws JRoboContainerException {
        try {
            String resolvedClass = Resolver.resolveClass(abstractionId, storage.getImplementations(abstractionId), injectionContext.getClassNodesStorage());
            return (T)getInstance(resolvedClass, injectionContext, classLoader);
        } catch (JRoboContainerException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            ClassNode abstraction = injectionContext.getClassNodesStorage().getClassNodeById(abstractionId);
            throw new JRoboContainerException("Failed to create " + abstraction.getClassName(), injectionContext, ex);
        }
    }
}
