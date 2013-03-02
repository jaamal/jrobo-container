package overclocking.jrobocontainer.classloader;

import overclocking.jrobocontainer.logging.IClassesLoadingLog;

import java.io.IOException;
import java.io.InputStream;

public class InnerClassLoader extends ClassLoader {
	@SuppressWarnings({ "deprecation", "unchecked" })
	private <T> Class<T> getClassByByteCode(byte[] byteCode, IClassesLoadingLog log) {
		try {
			Class<T> result = (Class<T>) defineClass(byteCode, 0,
					byteCode.length);
			return (Class<T>) ClassLoader.getSystemClassLoader().loadClass(
					result.getCanonicalName());
		} catch (Exception ex) {
            log.append("Failed to get class by bytecode: " + ex.getMessage());
		}
		return null;
	}

	private byte[] readAllBytes(InputStream inputStream, IClassesLoadingLog log) {
		byte[] result = null;
		try {
			result = new byte[inputStream.available()];
			inputStream.read(result);
		} catch (IOException e) {
            log.append("Failed to read bytes: " + e.getMessage());
		}
		return result;
	}

	public <T> Class<T> readClass(InputStream inputStream, IClassesLoadingLog log) {
		byte[] byteCode = readAllBytes(inputStream, log);
		return getClassByByteCode(byteCode, log);
	}
}
