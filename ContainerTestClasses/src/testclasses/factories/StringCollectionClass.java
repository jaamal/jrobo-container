package testclasses.factories;

import java.util.ArrayList;

public class StringCollectionClass
{
    ArrayList<String> strings;

    public StringCollectionClass(ArrayList<String> strings)
    {
        this.strings = strings;
    }

    public ArrayList<String> getStrings()
    {
        return strings;
    }
}
