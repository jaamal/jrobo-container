package getall;

public class ArrayInArguments implements IArrayInArguments
{

    private IFourImplementations[] implementations;

    public ArrayInArguments(IFourImplementations[] implementations)
    {
        this.implementations = implementations;
    }

    public IFourImplementations[] getImplementations()
    {
        return implementations;
    }
}
