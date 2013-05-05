package testclasses.factories;

import overclocking.jrobocontainer.annotations.ContainerFactory;

@ContainerFactory
public interface IStringFactory
{
    StringClass create(String arg0);
}
