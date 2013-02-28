package overclocking.jrobocontainer.configurations;

import overclocking.jrobocontainer.exceptions.JRoboContainerException;
import overclocking.jrobocontainer.injectioncontext.IInjectionContext;
import overclocking.jrobocontainer.storage.IStorage;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 06.10.12
 * Time: 16:57
 * To change this template use File | Settings | File Templates.
 */
public class BindedImplementationConfiguration extends AbstractConfiguration {

    private final IStorage storage;
    private final Class<?> bindedImplementation;

    public BindedImplementationConfiguration(IStorage storage, Class<?> abstraction, Class<?> bindedImplementation) {
        this.storage = storage;
        this.abstraction = abstraction;
        this.bindedImplementation = bindedImplementation;
    }

    @Override
    public <T> T get(IInjectionContext injectionContext) throws JRoboContainerException {
        return storage.getConfiguration(bindedImplementation).get(injectionContext);
    }

    @Override
    public <T> T create(IInjectionContext injectionContext) throws JRoboContainerException {
        return storage.getConfiguration(bindedImplementation).create(injectionContext);
    }
}
