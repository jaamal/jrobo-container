package container;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JRoboClassLoader{

	IStorage storage;
	String classPath;
	InnerClassLoader innerClassLoader;
	
	public JRoboClassLoader(IStorage storage, String classPath)
	{
		this.storage = storage;
		this.classPath = classPath;
	}
	
	private void addContent(File location) {
		if (location.isFile())
			addFile(location);
		else {
			for (File nextLocation : location.listFiles()) {
				addContent(nextLocation);
			}
		}
	}

	public void loadClasses() {
		for (String path : classPath.split(";")) {
			addContent(new File(path));
		}
	}

	private void addFile(File file) {
		if (file.getName().endsWith(".class"))
			addClassFile(file);
		if (file.getName().endsWith(".jar"))
			addJarFile(file);
	}

	private void addJarFile(File file) {
		try {
			JarFile jarFile = new JarFile(file);
			Enumeration<JarEntry> entries = jarFile.entries();
			while (entries.hasMoreElements()) {
				addJarEntry(jarFile, entries.nextElement());
			}
		} catch (IOException e) {
		}

	}

	private void addJarEntry(JarFile file, JarEntry entry) {
		try {
			if (entry.getName().endsWith(".class"))
				addClassByByteCode(readAll(file.getInputStream(entry)));
		} catch (IOException e) {
		}
	}

	private byte[] readAll(InputStream inputStream) {
		byte[] result = null;
		try {
			result = new byte[inputStream.available()];
			inputStream.read(result);
		} catch (IOException e) {
		}
		return result;
	}

	private void addClassFile(File file) {
		try {
			addClassByByteCode(readAll(new FileInputStream(file)));
		} catch (FileNotFoundException e) {
		}
	}

	private <T> void addClassByByteCode(byte[] byteCode) {
		try {
			Class<T> result = getClassByByteCode(byteCode);
			if (result != null)
				storage.addClass(result);
		} catch (Exception ex) {
		}
	}
	
	private <T> Class<T> getClassByByteCode(byte[] bytecode)
	{
		return new InnerClassLoader().getClassByByteCode(bytecode);
	}
	
	
}
