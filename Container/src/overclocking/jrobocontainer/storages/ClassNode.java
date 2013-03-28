package overclocking.jrobocontainer.storages;

import org.apache.bcel.classfile.JavaClass;

public class ClassNode
{
    private Class<?> clazz;
    private String className;
    private JavaClass javaClass;
    private String id;

    public ClassNode(String className, String id)
    {
        this.className = className;
        this.id = id;
    }

    public ClassNode(JavaClass javaClass, String id)
    {
        this.javaClass = javaClass;
        this.className = javaClass.getClassName();
        this.id = id;
    }

    public void setJavaClass(JavaClass javaClass)
    {
        this.javaClass = javaClass;
    }

    public JavaClass getJavaClass()
    {
        return javaClass;
    }

    public Class<?> getClazz()
    {
        return clazz;
    }

    public void setClazz(Class<?> clazz)
    {
        this.clazz = clazz;
    }

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public boolean isAbstract()
    {
        if(javaClass == null)
            return false;
        return javaClass.isAbstract();
    }

    public boolean isInterface()
    {
        if(javaClass == null)
            return false;
        return javaClass.isInterface();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
