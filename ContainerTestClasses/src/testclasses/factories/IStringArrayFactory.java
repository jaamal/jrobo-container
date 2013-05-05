package testclasses.factories;

import overclocking.jrobocontainer.annotations.ContainerFactory;

@ContainerFactory
public interface IStringArrayFactory
{
    StringArrayClass create(String[] arg0);
}
