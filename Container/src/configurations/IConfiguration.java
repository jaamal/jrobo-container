package configurations;

import exceptions.JRoboContainerException;

import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 06.10.12
 * Time: 13:26
 * To change this template use File | Settings | File Templates.
 */
public interface IConfiguration {
    <T> T get(HashSet<Class<?>> usedClasses) throws JRoboContainerException;
    <T> T create(HashSet<Class<?>> usedClasses) throws JRoboContainerException;
    <T> T[] getAll(HashSet<Class<?>> usedClasses) throws JRoboContainerException;
}