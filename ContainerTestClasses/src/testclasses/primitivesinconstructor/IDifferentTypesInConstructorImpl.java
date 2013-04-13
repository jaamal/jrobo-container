package testclasses.primitivesinconstructor;

import testclasses.classloader.simpletest.IOneImplementation;

public class IDifferentTypesInConstructorImpl implements IDifferentTypesInConstructor
{
    private IOneImplementation oneImplementation;
    private int firstInt;
    private int secondInt;
    private String firstString;
    private String secondString;

    public IDifferentTypesInConstructorImpl(IOneImplementation oneImplementation, int firstInt, String firstString, int secondInt, String secondString)
    {
        this.oneImplementation = oneImplementation;
        this.firstInt = firstInt;
        this.secondInt = secondInt;
        this.firstString = firstString;
        this.secondString = secondString;
    }

    public String getSecondString()
    {
        return secondString;
    }

    public String getFirstString()
    {
        return firstString;
    }

    public int getSecondInt()
    {
        return secondInt;
    }

    public int getFirstInt()
    {
        return firstInt;
    }

    public IOneImplementation getOneImplementation()
    {
        return oneImplementation;
    }
}
