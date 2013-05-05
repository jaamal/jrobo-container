package testclasses.factories;

import overclocking.jrobocontainer.annotations.ContainerFactory;

@ContainerFactory
public interface IBooleanPrimitiveFactory
{
    BooleanPrimitiveClass create(boolean arg0);
}
