package overclocking.jrobocontainer.classloader;

import java.io.IOException;
import java.io.InputStream;

public class InnerClassLoader extends ClassLoader {
	@SuppressWarnings({ "deprecation", "unchecked" })
	private <T> Class<T> getClassByByteCode(byte[] byteCode) {
		try {
			Class<T> result = (Class<T>) defineClass(byteCode, 0,
					byteCode.length);
			return (Class<T>) ClassLoader.getSystemClassLoader().loadClass(
					result.getCanonicalName());
		} catch (Throwable th) {
		}
		return null;
	}

	private byte[] readAllBytes(InputStream inputStream) {
		byte[] result = null;
		try {
			result = new byte[inputStream.available()];
			inputStream.read(result);
		} catch (IOException e) {
		}
		return result;
	}

	public <T> Class<T> readClass(InputStream inputStream) {
		byte[] byteCode = readAllBytes(inputStream);
		return getClassByByteCode(byteCode);
	}
}
