package Filters;

import classloader.IEntitiesFilter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TestFilesFilter implements IEntitiesFilter
{
    Logger logger = LogManager.getLogger(TestFilesFilter.class);

    @Override
    public boolean acceptJar(String jarName)
    {
        return false;
    }

    @Override
    public boolean acceptClass(String className)
    {
        boolean result = className.startsWith("testclasses");
        logger.info("Class " + className + (result ? " accepted" : " rejected"));
        return result;
    }
}
