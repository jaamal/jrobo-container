package testclasses.factories;

import overclocking.jrobocontainer.annotations.ContainerFactory;

@ContainerFactory
public interface IStringsFactory
{
    StringsClass create(String arg0);
    StringsClass create(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5);
}
