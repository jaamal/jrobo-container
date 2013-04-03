package overclocking.jrobocontainer.configurations;

import overclocking.jrobocontainer.injectioncontext.IInjectionContext;

public interface IConfiguration {
    <T> T get(IInjectionContext injectionContext, ClassLoader classLoader);
    <T> T create(IInjectionContext injectionContext, ClassLoader classLoader);
    <T> T[] getAll(IInjectionContext injectionContext, ClassLoader classLoader);
}