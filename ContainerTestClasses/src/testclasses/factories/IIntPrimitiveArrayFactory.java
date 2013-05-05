package testclasses.factories;

import overclocking.jrobocontainer.annotations.ContainerFactory;

@ContainerFactory
public interface IIntPrimitiveArrayFactory
{
    IntPrimitiveArrayClass create(int[] ints);
}
