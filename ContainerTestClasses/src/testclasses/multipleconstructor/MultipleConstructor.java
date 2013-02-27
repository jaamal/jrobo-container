package testclasses.multipleconstructor;

import overclocking.jrobocontainer.annotations.ContainerConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 17.09.12
 * Time: 13:01
 * To change this template use File | Settings | File Templates.
 */
public class MultipleConstructor{
    @ContainerConstructor
    public MultipleConstructor()
    {

    }

    public MultipleConstructor(MultipleConstructor multipleConstructor)
    {
        throw new RuntimeException();
    }
}
