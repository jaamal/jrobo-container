package classloader;

public interface IEntitiesFilter
{
    boolean acceptJar(String jarName);

    boolean acceptClass(String className);
}
