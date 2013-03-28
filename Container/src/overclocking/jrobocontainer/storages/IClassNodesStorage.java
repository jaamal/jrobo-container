package overclocking.jrobocontainer.storages;

import org.apache.bcel.classfile.JavaClass;
import overclocking.jrobocontainer.classloadersstorage.IClassLoadersStorage;

public interface IClassNodesStorage
{
    ClassNode getClassNodeById(String id);

    String getClassId(JavaClass javaClass);

    String getClassId(String className);

    String getClassId(Class<?> clazz);

    <T> Class<T> getClassById(String id, IClassLoadersStorage classNodesStorage);
}
