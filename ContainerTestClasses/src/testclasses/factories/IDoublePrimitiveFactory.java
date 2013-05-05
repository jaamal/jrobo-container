package testclasses.factories;

import overclocking.jrobocontainer.annotations.ContainerFactory;

@ContainerFactory
public interface IDoublePrimitiveFactory
{
    DoublePrimitiveClass create(double arg0);
}
