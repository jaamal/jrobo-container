package overclocking.jrobocontainer.injectioncontext;

public interface IInjectionContext
{
    boolean isClassProcessing(Class<?> clazz);
    void markClassAsProcessing(Class<?> clazz);
    void markClassAsNotProcessing(Class<?> clazz);

    void beginGet(Class<?> clazz);
    void endGet(Class<?> clazz);

    void beginCreate(Class<?> clazz);
    void endCreate(Class<?> clazz);

    void beginGetAll(Class<?> clazz);
    void endGetAll(Class<?> clazz);

    void reuse(Class<?> clazz);

    String getLog();
}
