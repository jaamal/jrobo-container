package overclocking.jrobocontainer.configurations;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 06.10.12
 * Time: 14:00
 * To change this template use File | Settings | File Templates.
 */
public interface IConfigurationsManager
{
    IConfiguration getConfiguration(String abstractionId);

    void setConfiguration(String abstractionId, IConfiguration configuration);
}
