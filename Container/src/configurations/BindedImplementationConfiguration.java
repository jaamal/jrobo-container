package configurations;

import exceptions.JRoboContainerException;
import storage.IStorage;
import sun.reflect.generics.scope.ClassScope;

import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 06.10.12
 * Time: 16:57
 * To change this template use File | Settings | File Templates.
 */
public class BindedImplementationConfiguration implements IConfiguration {

    private final IStorage storage;
    private final Class<?> abstraction;
    private final Class<?> bindedImplementation;

    public BindedImplementationConfiguration(IStorage storage, Class<?> abstraction, Class<?> bindedImplementation) {
        this.storage = storage;
        this.abstraction = abstraction;
        this.bindedImplementation = bindedImplementation;
    }

    @Override
    public <T> T get(HashSet<Class<?>> usedClasses) throws JRoboContainerException {
        return storage.getConfiguration(bindedImplementation).get(usedClasses);
    }

    @Override
    public <T> T create(HashSet<Class<?>> usedClasses) throws JRoboContainerException {
        return storage.getConfiguration(bindedImplementation).create(usedClasses);
    }
}
