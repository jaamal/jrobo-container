package testclasses.multipleconstructor;

import annotations.ContainerConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 17.09.12
 * Time: 13:23
 * To change this template use File | Settings | File Templates.
 */
public class MultipleConstructorIncorrect {
    @ContainerConstructor
    public MultipleConstructorIncorrect()
    {
    }

    @ContainerConstructor
    public MultipleConstructorIncorrect(MultipleConstructorIncorrect multipleConstructor)
    {
    }
}
