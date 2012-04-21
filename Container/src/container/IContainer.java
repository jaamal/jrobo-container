package container;

import exceptions.JRoboContainerException;

public interface IContainer {
	<T> T get(Class<T> requiredInterface) throws JRoboContainerException;

	<T> T create(Class<T> requiredInterface) throws JRoboContainerException;
}
