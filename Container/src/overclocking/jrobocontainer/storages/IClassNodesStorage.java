package overclocking.jrobocontainer.storages;

import org.apache.bcel.classfile.JavaClass;

public interface IClassNodesStorage
{
    ClassNode getClassNodeById(String id);
    String getClassId(JavaClass javaClass);
    String getClassId(String className);
    String getClassId(Class<?> clazz);
    void setClassLoader(String name, ClassLoader classLoader);
}
