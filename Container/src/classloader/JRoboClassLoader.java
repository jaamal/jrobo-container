package classloader;

import storage.IStorage;

import java.io.File;

public class JRoboClassLoader {

	String classPath;
    private IPathsFilter filter;
    InnerClassLoader innerClassLoader;
	DirectoriesWalker directoriesWalker;

	public JRoboClassLoader(IStorage storage, String classPath, IPathsFilter filter) {
		this.classPath = classPath;
        this.filter = filter;
        this.directoriesWalker = new DirectoriesWalker(storage);
	}

	public void loadClasses() {
		for (String path : classPath.split(System.getProperty("path.separator"))) {
            if(filter.accept(path))
                directoriesWalker.addFolder(new File(path));
		}
	}

}
