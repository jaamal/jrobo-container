package testclasses.factories;

import overclocking.jrobocontainer.annotations.ContainerFactory;

@ContainerFactory
public interface IBytePrimitiveFactory
{
    BytePrimitiveClass create(byte arg0);
}
