package overclocking.jrobocontainer.injectioncontext;

import overclocking.jrobocontainer.classloadersstorage.IClassLoadersStorage;
import overclocking.jrobocontainer.storages.ClassNode;
import overclocking.jrobocontainer.storages.IClassNodesStorage;

public interface IInjectionContext
{
    boolean isClassProcessing(String classId);
    void markClassAsProcessing(String classId);
    void markClassAsNotProcessing(String classId);

    void beginGet(ClassNode clazz);
    void endGet(ClassNode clazz);

    void beginCreate(ClassNode clazz);
    void endCreate(ClassNode clazz);

    void beginGetAll(ClassNode clazz);
    void endGetAll(ClassNode clazz);

    void reuse(ClassNode clazz);

    String getLog();

    IClassLoadersStorage getClassLoadersStorage();

    IClassNodesStorage getClassNodesStorage();
}
