package overclocking.jrobocontainer.configurations;

import overclocking.jrobocontainer.injectioncontext.IInjectionContext;
import overclocking.jrobocontainer.storages.ClassNode;
import overclocking.jrobocontainer.storages.IStorage;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 06.10.12
 * Time: 16:57
 * To change this template use File | Settings | File Templates.
 */
public class BoundImplementationConfiguration extends AbstractConfiguration {

    private final IStorage storage;
    private final ClassNode boundImplementation;

    public BoundImplementationConfiguration(IStorage storage, ClassNode abstraction, ClassNode boundImplementation) {
        this.storage = storage;
        this.abstraction = abstraction;
        this.boundImplementation = boundImplementation;
    }

    @Override
    public <T> T innerGet(IInjectionContext injectionContext) {
        return storage.getConfiguration(boundImplementation).get(injectionContext);
    }

    @Override
    public <T> T innerCreate(IInjectionContext injectionContext) {
        return storage.getConfiguration(boundImplementation).create(injectionContext);
    }
}
