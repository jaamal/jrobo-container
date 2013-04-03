package overclocking.jrobocontainer.storages;

import org.apache.bcel.classfile.JavaClass;

import java.util.TreeMap;
import java.util.UUID;

public class ClassNodesStorage implements IClassNodesStorage
{
    private TreeMap<String, ClassNode> idToClassNode;
    private TreeMap<String, String> classNameToId;

    public ClassNodesStorage()
    {
        idToClassNode = new TreeMap<String, ClassNode>();
        classNameToId = new TreeMap<String, String>();
    }

    @Override
    public String getClassId(JavaClass javaClass)
    {
        String className = javaClass.getClassName();
        if(!classNameToId.containsKey(className))
        {
            String id = UUID.randomUUID().toString();
            ClassNode result = new ClassNode(javaClass, id);
            classNameToId.put(className, id);
            idToClassNode.put(id, result);
            return id;
        }
        String id = classNameToId.get(className);
        ClassNode classNode = idToClassNode.get(id);
        if(classNode.getJavaClass() == null)
        {
            classNode.setJavaClass(javaClass);
            idToClassNode.put(id, classNode);
        }
        return id;
    }

    @Override
    public String getClassId(String className)
    {
        if(!classNameToId.containsKey(className))
        {
            String id = UUID.randomUUID().toString();
            ClassNode result = new ClassNode(className, id);
            classNameToId.put(className, id);
            idToClassNode.put(id, result);
            return id;
        }
        return classNameToId.get(className);
    }

    @Override
    public String getClassId(Class<?> clazz)
    {
        return getClassId(clazz.getName());
    }

    @Override
    public ClassNode getClassNodeById(String id)
    {
        return idToClassNode.get(id);
    }

    @Override
    public void setClassLoader(String name, ClassLoader classLoader) {
        String id = getClassId(name);
        ClassNode node = getClassNodeById(id);
        node.setClassLoader(classLoader);
        idToClassNode.put(id, node);
    }
}
