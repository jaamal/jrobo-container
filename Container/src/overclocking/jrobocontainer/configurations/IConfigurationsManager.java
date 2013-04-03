package overclocking.jrobocontainer.configurations;

public interface IConfigurationsManager
{
    IConfiguration getConfiguration(String abstractionId);
    void setConfiguration(String abstractionId, IConfiguration configuration);
}
