package overclocking.jrobocontainer.configurations;

import overclocking.jrobocontainer.classpathscanning.Resolver;
import overclocking.jrobocontainer.exceptions.JRoboContainerException;
import overclocking.jrobocontainer.injectioncontext.IInjectionContext;
import overclocking.jrobocontainer.storages.ClassNode;
import overclocking.jrobocontainer.storages.IStorage;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 06.10.12
 * Time: 13:38
 * To change this template use File | Settings | File Templates.
 */
public class AutoConfiguration extends AbstractConfiguration {

    private Object instance;

    public AutoConfiguration(IStorage storage, String abstractionId) {
        this.storage = storage;
        this.abstractionId = abstractionId;
        instance = null;
    }

    @SuppressWarnings("unchecked")
    public <T> T innerGet(IInjectionContext injectionContext, ClassLoader classLoader){

        ClassNode abstraction = injectionContext.getClassNodesStorage().getClassNodeById(abstractionId);
        try {
            if(instance != null)
            {
                injectionContext.reuse(abstraction);
                return (T)instance;
            }
            injectionContext.beginCreate(abstraction);
            String resolvedClassId = Resolver.resolveClass(abstractionId, storage.getImplementations(abstractionId), injectionContext.getClassNodesStorage());
            synchronized (storage.getSynchronizeObject(resolvedClassId)) {
                if(resolvedClassId.equals(abstractionId))
                {
                    instance = getInstance(resolvedClassId, injectionContext, classLoader);
                }
                else
                {
                    instance = storage.getConfiguration(resolvedClassId).get(injectionContext, classLoader);
                }
            }
            injectionContext.endCreate(abstraction);
            return (T)instance;
        } catch (JRoboContainerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new JRoboContainerException("Failed to get " + abstraction.getClassName(), injectionContext, ex);
        }
    }

    public <T> T innerCreate(IInjectionContext injectionContext, ClassLoader classLoader) {
        ClassNode abstraction = injectionContext.getClassNodesStorage().getClassNodeById(abstractionId);
        try {
            String resolvedClassId = Resolver.resolveClass(abstractionId, storage.getImplementations(abstractionId), injectionContext.getClassNodesStorage());
            return (T)getInstance(resolvedClassId, injectionContext, classLoader);
        } catch (JRoboContainerException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new JRoboContainerException("Failed to create " + abstraction.getClassName(), ex);
        }
    }
}
