package testclasses.factories;

import overclocking.jrobocontainer.annotations.ContainerFactory;

@ContainerFactory
public interface ICharPrimitiveFactory
{
    CharPrimitiveClass create(char arg0);
}
