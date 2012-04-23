package classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import storage.IStorage;

public class ClassFileLoader {

	IStorage storage;

	public ClassFileLoader(IStorage storage) {
		this.storage = storage;
	}

	public void load(File file) {
		try {
			Class<?> clazz = new InnerClassLoader()
					.readClass(new FileInputStream(file));
			if (clazz != null) {
				storage.addClass(clazz);
			} else {

			}
		} catch (FileNotFoundException e) {
		}
	}

}
