package container;

import exceptions.JRoboContainerException;
import sun.font.Type1Font;

public interface IContainer {
	<T> T get(Class<T> requiredAbstraction) throws JRoboContainerException;

	<T> T create(Class<T> requiredAbstraction) throws JRoboContainerException;

    <T1, T2 extends T1> void bind(Class<T1> abstraction, T2 instance);
}
