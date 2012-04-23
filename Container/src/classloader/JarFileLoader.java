package classloader;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import storage.IStorage;

public class JarFileLoader {

	IStorage storage;

	public JarFileLoader(IStorage storage) {
		this.storage = storage;
	}

	public void load(File file) {
		try {
			JarFile jarFile = new JarFile(file);
			Enumeration<JarEntry> entries = jarFile.entries();
			while (entries.hasMoreElements()) {
				loadJarEntry(jarFile, entries.nextElement());
			}
		} catch (IOException e) {
		}

	}

	private void loadJarEntry(JarFile file, JarEntry entry) {
		try {
			if (entry.getName().endsWith(".class")) {
				Class<?> clazz = new InnerClassLoader().readClass(file
						.getInputStream(entry));
				if (clazz != null)
					storage.addClass(clazz);
				else {

				}
			}
		} catch (IOException e) {
		}
	}

}
