package overclocking.jrobocontainer.factories;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.*;
import overclocking.jrobocontainer.container.AbstractionInstancePair;
import overclocking.jrobocontainer.container.IContainer;

public class MethodCreator
{
    public Method createConstructor(ClassGen classGenerator)
    {
        int accessFlags = Constants.ACC_PUBLIC;
        Type[] argumentTypes = new Type[]{Type.getType(IContainer.class)};
        String[] argumentNames = new String[]{"printer"};
        String className = classGenerator.getClassName();
        InstructionList il = new InstructionList();
        String methodName = "<init>";
        Type returnType = Type.VOID;
        ConstantPoolGen constantPoolGen = classGenerator.getConstantPool();
        MethodGen methodGen = new MethodGen(accessFlags, returnType, argumentTypes, argumentNames, methodName, className, il, constantPoolGen);
        InstructionFactory instructionFactory = new InstructionFactory(classGenerator);
        il.append(InstructionConstants.ALOAD_0);
        il.append(instructionFactory.createInvoke("java.lang.Object", "<init>", Type.VOID, new Type[0], Constants.INVOKESPECIAL));
        il.append(InstructionConstants.ALOAD_0);
        il.append(InstructionConstants.ALOAD_1);
        il.append(instructionFactory.createPutField(classGenerator.getClassName(), "printer", Type.getType(IContainer.class)));
        il.append(InstructionConstants.RETURN);
        methodGen.setMaxStack();
        Method result = methodGen.getMethod();
        System.out.println(result.getCode());
        return result;
    }

    public Method create(ClassGen classGenerator, Type returnType, Type[] argumentTypes)
    {
        int accessFlags = Constants.ACC_PUBLIC;
        String[] argumentNames = new String[argumentTypes.length];
        for (int i = 0; i < argumentNames.length; i++)
            argumentNames[i] = "arg" + i;
        String className = classGenerator.getClassName();
        InstructionList il = new InstructionList();
        String methodName = "create";
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        MethodGen methodGen = new MethodGen(accessFlags, returnType, argumentTypes, argumentNames, methodName, className, il, constantPool);
        InstructionFactory instructionFactory = new InstructionFactory(classGenerator);

        il.append(new ALOAD(0));
        il.append(instructionFactory.createGetField(className, "container", Type.getType(IContainer.class)));
        il.append(new ICONST(argumentTypes.length));
        il.append(instructionFactory.createNewArray(Type.getType(AbstractionInstancePair.class), (short)1));
        for (int i = 0; i < argumentTypes.length; i++)
        {
            il.append(new DUP());
            il.append(new ICONST(i));
            il.append(instructionFactory.createNew(new ObjectType(AbstractionInstancePair.class.getName())));
            il.append(new DUP());
            il.append(new ALOAD(i + 1));
            il.append(new ICONST(i + 3));
            il.append(instructionFactory.createInvoke("SomePair", "<init>", Type.VOID, new Type[] {Type.getType(String.class), Type.getType(int.class)}, Constants.INVOKESPECIAL));
            il.append(new AASTORE());
        }
        il.append(instructionFactory.createInvoke("Printer", "create", Type.STRING, new Type[] {Type.getType(AbstractionInstancePair[].class)}, Constants.INVOKEVIRTUAL));
        il.append(new ARETURN());
        methodGen.setMaxStack();
        Method result = methodGen.getMethod();
        System.out.println(result.getCode());
        return result;

    }
}
