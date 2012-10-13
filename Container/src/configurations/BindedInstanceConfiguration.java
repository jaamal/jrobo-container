package configurations;

import classloader.Resolver;
import exceptions.JRoboContainerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import storage.IStorage;

import java.util.HashSet;

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
    public <T> T get(HashSet<Class<?>> usedClasses) throws JRoboContainerException {
        return (T)instance;
    }

    @Override
    public <T> T create(HashSet<Class<?>> usedClasses) throws JRoboContainerException {
        try {
            logger.info("Creating " + abstraction.getName());
            Class<?> resolvedClass = Resolver.resolveClass(abstraction, storage.getImplementations(abstraction));
            return (T)getInstance(resolvedClass, usedClasses);
        } catch (JRoboContainerException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new JRoboContainerException("Failed to create " + abstraction.getName(), ex);
        }
    }
    Logger logger = LogManager.getLogger(BindedInstanceConfiguration.class);
}
