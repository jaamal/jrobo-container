package testclasses.factories;

import overclocking.jrobocontainer.annotations.ContainerFactory;

@ContainerFactory
public interface IIntsFactory
{
    IntsClass create(int arg0);
}
