package overclocking.jrobocontainer.container;

public class AbstractionInstancePair<T1, T2 extends T1>
{
    private Class<T1> abstraction;
    private T2 instance;

    public AbstractionInstancePair(Class<T1> abstraction, T2 instance)
    {
        this.abstraction = abstraction;
        this.instance = instance;
    }
    public Class<T1> getAbstraction()
    {
        return abstraction;
    }

    public T2 getInstance()
    {
        return instance;
    }
}
