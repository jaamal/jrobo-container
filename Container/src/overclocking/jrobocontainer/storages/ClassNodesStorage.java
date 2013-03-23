package overclocking.jrobocontainer.storages;

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
    public ClassNode getClassNode(String className)
    {
        if (!map.containsKey(className))
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
