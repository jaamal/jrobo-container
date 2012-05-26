package classloader;

import storage.IStorage;

import java.io.File;

public class DirectoriesWalker {

	private ClassFileLoader classFileLoader;
	private JarFileLoader jarFileLoader;

	public DirectoriesWalker(IStorage storage) {
		classFileLoader = new ClassFileLoader(storage);
		jarFileLoader = new JarFileLoader(storage);
	}

	public void addFolder(File currentLocation) {
        if (currentLocation.isFile())
			addFile(currentLocation);
		else {
			for (File nextLocation : currentLocation.listFiles()) {
				addFolder(nextLocation);
			}
		}
	}

	private void addFile(File file) {
		if (file.getName().endsWith(".class"))
			classFileLoader.load(file);
		if (file.getName().endsWith(".jar"))
			jarFileLoader.load(file);
	}
}
