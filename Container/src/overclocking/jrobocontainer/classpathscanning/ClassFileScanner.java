package overclocking.jrobocontainer.classpathscanning;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import overclocking.jrobocontainer.logging.ILoadingLog;
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

    public void scanFile(File file, ILoadingLog log)
    {
        String fileName = file.getName();
        log.beginScanFile(fileName);
        try
        {
            ClassParser classParser = new ClassParser(file.getAbsolutePath());
            JavaClass javaClass = classParser.parse();
            storage.addClass(javaClass, log);
            log.scanFileSuccess(javaClass.getClassName());
        }
        catch (IOException e)
        {
            log.scanFileFail(e.getMessage());
        }
        finally
        {
            log.endScanFile();
        }
    }

}
