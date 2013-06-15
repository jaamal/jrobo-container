package testclasses.parametrizedcreate;

public class A implements IA
{
    private String s;
    private IB b;
    private int x;

    public A(String s, IB b, int x)
    {
        this.s = s;
        this.b = b;
        this.x = x;
    }

    public A(String s)
    {
        this.s = s;
        this.b = null;
        this.x = 0;
    }

    public A(IB b, int x)
    {
        this.s = null;
        this.b = b;
        this.x = x;
    }

    public String getS()
    {
        return s;
    }

    public IB getB()
    {
        return b;
    }

    public int getX()
    {
        return x;
    }
}
