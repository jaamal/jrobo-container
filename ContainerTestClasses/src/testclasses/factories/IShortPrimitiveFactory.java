package testclasses.factories;

import overclocking.jrobocontainer.annotations.ContainerFactory;

@ContainerFactory
public interface IShortPrimitiveFactory
{
    ShortPrimitiveClass create(short arg0);
}
