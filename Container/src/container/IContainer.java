package container;

import exceptions.JRoboContainerException;

public interface IContainer {
	<T> T get(Class<T> requiredAbstraction) throws JRoboContainerException;

	<T> T create(Class<T> requiredAbstraction) throws JRoboContainerException;
}
