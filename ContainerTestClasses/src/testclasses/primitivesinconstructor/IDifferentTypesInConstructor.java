package testclasses.primitivesinconstructor;

import testclasses.classloader.simpletest.IOneImplementation;

public interface IDifferentTypesInConstructor
{
    IOneImplementation getOneImplementation();
    int getFirstInt();
    int getSecondInt();
    String getFirstString();
    String getSecondString();
}
