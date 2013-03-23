package overclocking.jrobocontainer.storages;

import overclocking.jrobocontainer.classloadersstorage.IClassLoadersStorage;

public interface IClassNodesStorage
{
    ClassNode getClassNode(String className);

    ClassNode getClassNode(Class<?> clazz);

    <T> Class<T> getClassByNode(ClassNode classNode, IClassLoadersStorage classNodesStorage);
}
