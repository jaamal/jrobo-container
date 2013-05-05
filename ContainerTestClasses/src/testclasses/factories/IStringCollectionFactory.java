package testclasses.factories;

import overclocking.jrobocontainer.annotations.ContainerFactory;

import java.util.ArrayList;

@ContainerFactory
public interface IStringCollectionFactory
{
    StringCollectionClass create(ArrayList<String> arg0);
}
