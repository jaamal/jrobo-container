package overclocking.jrobocontainer.configurations;

import overclocking.jrobocontainer.exceptions.JRoboContainerException;
import overclocking.jrobocontainer.storages.IClassNodesStorage;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 06.10.12
 * Time: 14:18
 * To change this template use File | Settings | File Templates.
 */
public class ConfigurationsManager implements IConfigurationsManager {

    HashMap<String, IConfiguration> configurations;
    private IClassNodesStorage classNodesStorage;

    public ConfigurationsManager(IClassNodesStorage classNodesStorage) {
        this.classNodesStorage = classNodesStorage;
        configurations = new HashMap<String, IConfiguration>();
    }

    @Override
    public IConfiguration getConfiguration(String abstractionId) {
        if(configurations.containsKey(abstractionId))
            return configurations.get(abstractionId);
        throw new JRoboContainerException(classNodesStorage.getClassNodeById(abstractionId).getClassName() + " not found in paths");
    }

    @Override
    public void setConfiguration(String abstraction, IConfiguration configuration) {
        configurations.put(abstraction, configuration);
    }
}
