package overclocking.jrobocontainer.classscanning;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import overclocking.jrobocontainer.loadingcontext.ILoadingContext;
import overclocking.jrobocontainer.storages.IStorage;

import java.io.File;
import java.io.IOException;

public class ClassFileScanner
{

    IStorage storage;
    public ClassFileScanner(IStorage storage)
    {
        this.storage = storage;
    }

    public void load(File file, ILoadingContext loadingContext)
    {
        try
        {
            ClassParser classParser = new ClassParser(file.getAbsolutePath());
            JavaClass clazz = classParser.parse();
            storage.addClass(clazz, loadingContext);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
