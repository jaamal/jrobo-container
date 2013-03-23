package overclocking.jrobocontainer.storages;

import org.apache.bcel.classfile.JavaClass;
import overclocking.jrobocontainer.configurations.IConfiguration;
import overclocking.jrobocontainer.loadingcontext.ILoadingContext;

import java.util.ArrayList;

public interface IStorage {
    void addClass(JavaClass clazz, ILoadingContext loadingContext);

    ArrayList<ClassNode> getImplementations(ClassNode requiredAbstraction);

    void buildExtendedInheritanceGraph();

    Object getSynchronizeObject(ClassNode resolvedClass);

    IConfiguration getConfiguration(ClassNode abstraction);

    void setConfiguration(ClassNode abstraction, IConfiguration configuration);
}
