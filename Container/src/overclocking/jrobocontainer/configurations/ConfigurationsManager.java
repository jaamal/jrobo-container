package overclocking.jrobocontainer.configurations;

import overclocking.jrobocontainer.exceptions.JRoboContainerException;
import overclocking.jrobocontainer.storages.ClassNode;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 06.10.12
 * Time: 14:18
 * To change this template use File | Settings | File Templates.
 */
public class ConfigurationsManager implements IConfigurationsManager {

    HashMap<ClassNode, IConfiguration> configurations;

    public ConfigurationsManager() {
        configurations = new HashMap<ClassNode, IConfiguration>();
    }

    @Override
    public IConfiguration getConfiguration(ClassNode abstraction) {
        if(configurations.containsKey(abstraction))
            return configurations.get(abstraction);
        throw new JRoboContainerException(abstraction.getClassName() + " not found in paths");
    }

    @Override
    public void setConfiguration(ClassNode abstraction, IConfiguration configuration) {
        configurations.put(abstraction, configuration);
    }
}
