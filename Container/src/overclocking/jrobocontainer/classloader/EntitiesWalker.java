package overclocking.jrobocontainer.classloader;

import overclocking.jrobocontainer.logging.IClassesLoadingLog;
import overclocking.jrobocontainer.storage.IStorage;

import java.io.File;

public class EntitiesWalker
{
	private ClassFileLoader classFileLoader;
	private JarFileLoader jarFileLoader;

	public EntitiesWalker(IStorage storage, IClassLoaderConfiguration jarsFilter) {
		classFileLoader = new ClassFileLoader(storage);
		jarFileLoader = new JarFileLoader(storage, jarsFilter);
	}

	public void addFolder(File currentLocation, IClassesLoadingLog log) {
        if (currentLocation.isFile())
			addFile(currentLocation, log);
		else {
            log.append("Now in " + currentLocation.getAbsolutePath());
			for (File nextLocation : currentLocation.listFiles()) {
				addFolder(nextLocation, log);
			}
		}
	}

	private void addFile(File file, IClassesLoadingLog log) {
        log.append("Trying to add " + file.getAbsolutePath());
		if (file.getName().endsWith(".class"))
			classFileLoader.load(file, log);
		if (file.getName().endsWith(".jar"))
			jarFileLoader.load(file);
	}
}
