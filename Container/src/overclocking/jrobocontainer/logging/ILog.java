package overclocking.jrobocontainer.logging;

public interface ILog
{
    void beginGet(Class<?> clazz);
    void endGet(Class<?> clazz);

    void beginCreate(Class<?> clazz);
    void endCreate(Class<?> clazz);

    void beginGetAll(Class<?> clazz);
    void endGetAll(Class<?> clazz);

    void reuse(Class<?> clazz);

    String getLog();
}
