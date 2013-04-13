package testclasses.primitivesinconstructor;

public class TwoIntsInConstructor implements ITwoIntsInConstructor
{
    private int x, y;

    public TwoIntsInConstructor(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX()
    {
        return x;
    }

    @Override
    public int getY()
    {
        return y;
    }
}
