package overclocking.jrobocontainer.storages;

import org.apache.bcel.classfile.JavaClass;
import overclocking.jrobocontainer.classloadersstorage.IClassLoadersStorage;

import java.util.TreeMap;

public class ClassNodesStorage implements IClassNodesStorage
{
    TreeMap<String, ClassNode> map;

    public ClassNodesStorage()
    {
        map = new TreeMap<String, ClassNode>();
    }

    @Override
    public ClassNode getClassNode(JavaClass javaClass)
    {
        String className = javaClass.getClassName();
        if (!map.containsKey(className))
            map.put(className, new ClassNode(javaClass));
        ClassNode result = map.get(className);
        if(result.getJavaClass() == null)
        {
            result.setJavaClass(javaClass);
            map.put(className, result);
        }
        return result;
    }

    @Override
    public ClassNode getClassNode(String className)
    {
        if(!map.containsKey(className))
            map.put(className, new ClassNode(className));
        return map.get(className);
    }

    @Override
    public ClassNode getClassNode(Class<?> clazz)
    {
        return getClassNode(clazz.getName());
    }

    @Override
    public <T> Class<T> getClassByNode(ClassNode classNode, IClassLoadersStorage classLoadersStorage)
    {
        ClassLoader classLoader = classLoadersStorage.getClassLoaderFor(classNode.getClassName());
        try
        {
            Class<T> result = (Class<T>) classLoader.loadClass(classNode.getClassName());
            classNode.setClazz(result);
            map.put(classNode.getClassName(), classNode);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
