package classloader;

import java.io.File;

import storage.IStorage;

public class JRoboClassLoader{

	String classPath;
	InnerClassLoader innerClassLoader;
	DirectoriesWalker directoriesWalker;
	
	public JRoboClassLoader(IStorage storage, String classPath)
	{
		this.classPath = classPath;
		this.directoriesWalker = new DirectoriesWalker(storage);
	}
	
	public void loadClasses() {
		for (String path : classPath.split(";")) {
			directoriesWalker.addFolder(new File(path));
		}
	}
	
}
