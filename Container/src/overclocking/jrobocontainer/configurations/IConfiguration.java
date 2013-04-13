package overclocking.jrobocontainer.configurations;

import overclocking.jrobocontainer.container.AbstractionInstancePair;
import overclocking.jrobocontainer.injectioncontext.IInjectionContext;

public interface IConfiguration {
    <T> T get(IInjectionContext injectionContext, ClassLoader classLoader);
    <T> T create(IInjectionContext injectionContext, ClassLoader classLoader, AbstractionInstancePair[] substitutions);
    <T> T[] getAll(IInjectionContext injectionContext, ClassLoader classLoader);
}