package overclocking.jrobocontainer.storages;

import org.apache.bcel.classfile.JavaClass;
import overclocking.jrobocontainer.configurations.IConfiguration;
import overclocking.jrobocontainer.logging.ILoadingLog;

import java.util.ArrayList;

public interface IStorage {
    void addClass(JavaClass clazz, ILoadingLog log);

    ArrayList<String> getImplementations(String requiredAbstractionId);

    void buildExtendedInheritanceGraph();

    Object getSynchronizeObject(String classNodeId);

    IConfiguration getConfiguration(String abstractionId);

    void setConfiguration(String abstractionId, IConfiguration configuration);
}
