package overclocking.jrobocontainer.configurations;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 06.10.12
 * Time: 14:00
 * To change this template use File | Settings | File Templates.
 */
public interface IConfigurationsManager {
    <T> IConfiguration getConfiguration(Class<T> abstraction);
    <T> void setConfiguration(Class<T> abstraction, IConfiguration configuration);
}
