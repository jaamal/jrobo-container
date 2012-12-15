package classloader;

import storage.IStorage;

import java.io.File;

public class EntitiesWalker
{
	private ClassFileLoader classFileLoader;
	private JarFileLoader jarFileLoader;

	public EntitiesWalker(IStorage storage, IEntitiesFilter entitiesFilter) {
		classFileLoader = new ClassFileLoader(storage, entitiesFilter);
		jarFileLoader = new JarFileLoader(storage, entitiesFilter);
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
