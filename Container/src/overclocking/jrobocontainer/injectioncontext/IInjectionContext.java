package overclocking.jrobocontainer.injectioncontext;

public interface IInjectionContext
{
    boolean isClassProcessing(Class<?> clazz);
    void markClassAsProcessing(Class<?> clazz);
    void markClassAsNotProcessing(Class<?> clazz);
}
