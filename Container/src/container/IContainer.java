package container;

import exceptions.JRoboContainerException;

public interface IContainer {
	<T> T get(Class<T> requiredAbstraction) throws JRoboContainerException;

	<T> T create(Class<T> requiredAbstraction) throws JRoboContainerException;

    <T1, T2 extends T1> void bindInstance(Class<T1> abstraction, T2 instance);

    <T1, T2 extends T1> void bindImplementation(Class<T1> abstraction, Class<T2> bindedImplementation);

    <T> T[] getAll(Class<T> requiredAbstraction) throws JRoboContainerException;
}
