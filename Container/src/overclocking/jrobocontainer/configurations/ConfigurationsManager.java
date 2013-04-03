package overclocking.jrobocontainer.configurations;

import overclocking.jrobocontainer.exceptions.JroboContainerException;
import overclocking.jrobocontainer.storages.IClassNodesStorage;

import java.util.HashMap;

public class ConfigurationsManager implements IConfigurationsManager {

    private HashMap<String, IConfiguration> configurations;
    private IClassNodesStorage classNodesStorage;

    public ConfigurationsManager(IClassNodesStorage classNodesStorage) {
        this.classNodesStorage = classNodesStorage;
        this.configurations = new HashMap<String, IConfiguration>();
    }

    @Override
    public IConfiguration getConfiguration(String abstractionId) {
        if(configurations.containsKey(abstractionId))
            return configurations.get(abstractionId);
        throw new JroboContainerException(classNodesStorage.getClassNodeById(abstractionId).getClassName() + " not found in paths");
    }

    @Override
    public void setConfiguration(String abstraction, IConfiguration configuration) {
        configurations.put(abstraction, configuration);
    }
}
