package testclasses.factories;

import overclocking.jrobocontainer.annotations.ContainerFactory;

@ContainerFactory
public interface IFloatPrimitiveFactory
{
    FloatPrimitiveClass create(float arg0);
}
