package tests.container;

import classloader.IPathsFilter;

public class NoSystemsFilter implements IPathsFilter
{

    @Override
    public boolean accept(String path) {
        return !path.startsWith("/System") && !path.startsWith("C:\\Program Files");
    }
}
