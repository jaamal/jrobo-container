package overclocking.jrobocontainer.factories;

import org.apache.bcel.Constants;
import org.apache.bcel.generic.ClassGen;

public class ClassGeneratorCreator
{
    public ClassGen create(Class factoryInterface)
    {
        String className = factoryInterface.getName().substring(1);
        String superClassName = "java.lang.Object";
        String fileName = "<generated>";
        int accessFlags = Constants.ACC_PUBLIC;
        String[] interfaces = new String[]{factoryInterface.getName()};
        ClassGen result = new ClassGen(className, superClassName, fileName, accessFlags, interfaces);
        return result;
    }
}
