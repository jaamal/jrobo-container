package testclasses.factories;

import overclocking.jrobocontainer.annotations.ContainerFactory;

@ContainerFactory
public interface ILongPrimitiveFactory
{
    LongPrimitiveClass create(long arg0);
}
