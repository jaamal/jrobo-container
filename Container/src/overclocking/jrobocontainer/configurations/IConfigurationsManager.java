package overclocking.jrobocontainer.configurations;

import overclocking.jrobocontainer.storages.ClassNode;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 06.10.12
 * Time: 14:00
 * To change this template use File | Settings | File Templates.
 */
public interface IConfigurationsManager
{
    IConfiguration getConfiguration(ClassNode abstraction);

    void setConfiguration(ClassNode abstraction, IConfiguration configuration);
}
