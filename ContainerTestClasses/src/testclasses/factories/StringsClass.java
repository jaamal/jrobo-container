package testclasses.factories;

public class StringsClass
{
    String s;
    String[] strings;

    public StringsClass(String s)
    {
        this.s = s;
    }

    public StringsClass(String a, String b, String c, String d, String e, String f)
    {
        strings = new String[]{a, b, c, d, e, f};
    }

    public String[] getStrings()
    {
        return strings;
    }

    public String getString()
    {
        return s;
    }
}
