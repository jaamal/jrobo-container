package container;

import configurations.IConfiguration;
import exceptions.JRoboContainerException;
import sun.font.Type1Font;

import java.util.ArrayList;

public interface IContainer {
	<T> T get(Class<T> requiredAbstraction) throws JRoboContainerException;

	<T> T create(Class<T> requiredAbstraction) throws JRoboContainerException;

    <T1, T2 extends T1> void bindInstance(Class<T1> abstraction, T2 instance);
}
