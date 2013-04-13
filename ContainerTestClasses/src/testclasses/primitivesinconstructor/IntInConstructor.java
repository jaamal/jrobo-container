package testclasses.primitivesinconstructor;

public class IntInConstructor implements IIntInConstructor
{
    private int x;

    public IntInConstructor(int x)
    {
        this.x = x;
    }

    @Override
    public int getInt()
    {
        return x;
    }
}
