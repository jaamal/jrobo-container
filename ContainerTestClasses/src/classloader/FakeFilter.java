package classloader;

import classloader.IPathsFilter;

public class FakeFilter implements IPathsFilter
{
    public boolean accept(String path)
    {
        return true;
    }
}
