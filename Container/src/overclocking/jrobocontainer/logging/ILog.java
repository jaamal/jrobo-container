package overclocking.jrobocontainer.logging;

import overclocking.jrobocontainer.storages.ClassNode;

public interface ILog
{
    void beginGet(ClassNode clazz);
    void endGet(ClassNode clazz);

    void beginCreate(ClassNode clazz);
    void endCreate(ClassNode clazz);

    void beginGetAll(ClassNode clazz);
    void endGetAll(ClassNode clazz);

    void reuse(ClassNode clazz);

    String getLog();
}
