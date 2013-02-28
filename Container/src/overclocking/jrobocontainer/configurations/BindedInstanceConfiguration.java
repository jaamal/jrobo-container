package overclocking.jrobocontainer.configurations;

import overclocking.jrobocontainer.classloader.Resolver;
import overclocking.jrobocontainer.exceptions.JRoboContainerException;
import overclocking.jrobocontainer.injectioncontext.IInjectionContext;
import overclocking.jrobocontainer.storage.IStorage;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 06.10.12
 * Time: 16:49
 * To change this template use File | Settings | File Templates.
 */
public class BindedInstanceConfiguration extends AbstractConfiguration {
    private final IStorage storage;
    private final Object instance;

    public BindedInstanceConfiguration(IStorage storage, Class<?> abstraction, Object instance) {

        this.storage = storage;
        this.abstraction = abstraction;
        this.instance = instance;
    }

    @Override
    public <T> T get(IInjectionContext injectionContext) throws JRoboContainerException {
        return (T)instance;
    }

    @Override
    public <T> T create(IInjectionContext injectionContext) throws JRoboContainerException {
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
