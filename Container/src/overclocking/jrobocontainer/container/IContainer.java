package overclocking.jrobocontainer.container;

public interface IContainer {
	<T> T get(Class<T> requiredAbstraction);

	<T> T create(Class<T> requiredAbstraction);

    <T1, T2 extends T1> void bindInstance(Class<T1> abstraction, T2 instance);

    <T1, T2 extends T1> void bindImplementation(Class<T1> abstraction, Class<T2> boundImplementation);

    <T> void bindClassLoader(Class<T> abstraction, ClassLoader classLoader);

    <T> T[] getAll(Class<T> requiredAbstraction);

    String getClassesLoadingLog();

    String getLastLog();
}
