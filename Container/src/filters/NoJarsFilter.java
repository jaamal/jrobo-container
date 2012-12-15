package filters;

import classloader.IJarsFilter;

public class NoJarsFilter implements IJarsFilter
{
    @Override
    public boolean acceptJar(String jarName)
    {
        return false;
    }
}
