package testclasses.factories;

import overclocking.jrobocontainer.annotations.ContainerFactory;

@ContainerFactory
public interface IIntPrimitiveFactory
{
    IntPrimitiveClass create(int arg0);
}


