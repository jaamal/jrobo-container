package overclocking.jrobocontainer.configurations;

import overclocking.jrobocontainer.injectioncontext.IInjectionContext;
import overclocking.jrobocontainer.storages.IClassNodesStorage;
import overclocking.jrobocontainer.storages.IStorage;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 06.10.12
 * Time: 16:57
 * To change this template use File | Settings | File Templates.
 */
public class BoundImplementationConfiguration extends AbstractConfiguration {
    private String boundImplementationId;

    public BoundImplementationConfiguration(IStorage storage, IClassNodesStorage classNodesStorage, String abstractionId, String boundImplementationId) {
        super(storage, classNodesStorage);
        this.boundImplementationId = boundImplementationId;
        this.abstractionId = abstractionId;
    }

    @Override
    public <T> T innerGet(IInjectionContext injectionContext, ClassLoader classLoader) {
        return storage.getConfiguration(boundImplementationId).get(injectionContext, classLoader);
    }

    @Override
    public <T> T innerCreate(IInjectionContext injectionContext, ClassLoader classLoader) {
        return storage.getConfiguration(boundImplementationId).create(injectionContext, classLoader);
    }
}
