package container;

public class InnerClassLoader extends ClassLoader{
	@SuppressWarnings({ "deprecation", "unchecked" })
	public <T> Class<T> getClassByByteCode(byte[] byteCode) {
		try
		{
			Class<T> result = (Class<T>) defineClass(byteCode, 0, byteCode.length);
			return (Class<T>) ClassLoader.getSystemClassLoader().loadClass(result.getCanonicalName());
		}
		catch(Throwable th)
		{
		}
		System.err.println("Fail");
		return null;
	}
}
