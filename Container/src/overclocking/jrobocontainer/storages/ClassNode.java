package overclocking.jrobocontainer.storages;

public class ClassNode
{
    private Class<?> clazz;
    private String className;

    public ClassNode(String className)
    {
        this.className = className;
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
        return false;
    }

    public boolean isInterface()
    {
        return false;
    }
}
