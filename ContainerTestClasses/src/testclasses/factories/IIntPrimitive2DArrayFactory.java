package testclasses.factories;

import overclocking.jrobocontainer.annotations.ContainerFactory;

@ContainerFactory
public interface IIntPrimitive2DArrayFactory
{
    IntPrimitive2DArrayClass create(int[][] ints);
}
