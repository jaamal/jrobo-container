package overclocking.jrobocontainer.storages;

import org.apache.bcel.classfile.JavaClass;

public class ClassNode
{
    private Class<?> clazz;
    private String className;
    private JavaClass javaClass;

    public void setJavaClass(JavaClass javaClass)
    {
        this.javaClass = javaClass;
    }

    public JavaClass getJavaClass()
    {
        return javaClass;
    }

    public ClassNode(String className)
    {
        this.className = className;
    }

    public ClassNode(JavaClass javaClass)
    {
        this.javaClass = javaClass;
        this.className = javaClass.getClassName();
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
        return javaClass.isAbstract();
    }

    public boolean isInterface()
    {
        return javaClass.isInterface();
    }
}
