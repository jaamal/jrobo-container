package overclocking.jrobocontainer.storages;

import org.apache.bcel.classfile.JavaClass;
import overclocking.jrobocontainer.classloadersstorage.IClassLoadersStorage;

public interface IClassNodesStorage
{
    ClassNode getClassNode(JavaClass javaClass);

    ClassNode getClassNode(String className);

    ClassNode getClassNode(Class<?> clazz);

    <T> Class<T> getClassByNode(ClassNode classNode, IClassLoadersStorage classNodesStorage);
}
