package overclocking.jrobocontainer.factories;

import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.Type;
import overclocking.jrobocontainer.container.Container;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Method;

public class FactoryCreator
{
    private final ClassGeneratorCreator classGeneratorCreator;
    private final MethodCreator methodCreator;
    private final FieldCreator fieldCreator;

    public FactoryCreator()
    {
        classGeneratorCreator = new ClassGeneratorCreator();
        methodCreator = new MethodCreator();
        fieldCreator = new FieldCreator();
    }

    public <T> T createFactory(Class<T> factoryInterface)
    {
        if (!factoryInterface.isInterface())
            return null;
        ClassGen classGenerator = classGeneratorCreator.create(factoryInterface);
        classGenerator.setMinor(0);
        classGenerator.setMajor(50);
        classGenerator.addField(fieldCreator.create("container", Container.class, classGenerator));
        classGenerator.addMethod(methodCreator.createConstructor(classGenerator));
        for (Method method : factoryInterface.getDeclaredMethods())
            if (method.getName().equals("create"))
            {
                classGenerator.addMethod(methodCreator.create(classGenerator, Type.getType(method.getReturnType()), Type.getTypes(method.getParameterTypes())));
            }
        JavaClass javaClass = classGenerator.getJavaClass();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try
        {
            javaClass.dump(dataOutputStream);
            Class clazz = new EmitedClassLoader().loadClass(byteArrayOutputStream.toByteArray());
            return (T)clazz.getConstructors()[0].newInstance(Container.getInstance());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
