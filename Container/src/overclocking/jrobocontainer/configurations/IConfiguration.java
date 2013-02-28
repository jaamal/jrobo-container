package overclocking.jrobocontainer.configurations;

import overclocking.jrobocontainer.exceptions.JRoboContainerException;
import overclocking.jrobocontainer.injectioncontext.IInjectionContext;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 06.10.12
 * Time: 13:26
 * To change this template use File | Settings | File Templates.
 */
public interface IConfiguration {
    <T> T get(IInjectionContext injectionContext) throws JRoboContainerException;
    <T> T create(IInjectionContext injectionContext) throws JRoboContainerException;
    <T> T[] getAll(IInjectionContext injectionContext) throws JRoboContainerException;
}