package overclocking.jrobocontainer.configurations;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 06.10.12
 * Time: 14:18
 * To change this template use File | Settings | File Templates.
 */
public class ConfigurationsManager implements IConfigurationsManager {

    HashMap<Class<?>, IConfiguration> configurations;

    public ConfigurationsManager() {
        configurations = new HashMap<Class<?>, IConfiguration>();
    }

    @Override
    public <T> IConfiguration getConfiguration(Class<T> abstraction) {
        return configurations.get(abstraction);
    }

    @Override
    public <T> void setConfiguration(Class<T> abstraction, IConfiguration configuration) {
        configurations.put(abstraction, configuration);
    }
}
