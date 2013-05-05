package overclocking.jrobocontainer.factories;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.*;
import overclocking.jrobocontainer.container.AbstractionInstancePair;
import overclocking.jrobocontainer.container.Container;

public class MethodCreator
{
    public Method createConstructor(ClassGen classGenerator)
    {
        int accessFlags = Constants.ACC_PUBLIC;
        Type[] argumentTypes = new Type[]{Type.getType(Container.class)};
        String[] argumentNames = new String[]{"container"};
        String className = classGenerator.getClassName();
        InstructionList il = new InstructionList();
        String methodName = "<init>";
        Type returnType = Type.VOID;
        ConstantPoolGen constantPoolGen = classGenerator.getConstantPool();
        MethodGen methodGen = new MethodGen(accessFlags, returnType, argumentTypes, argumentNames, methodName, className, il, constantPoolGen);
        InstructionFactory instructionFactory = new InstructionFactory(classGenerator);
        il.append(InstructionConstants.ALOAD_0);//[this]
        il.append(instructionFactory.createInvoke("java.lang.Object", "<init>", Type.VOID, new Type[0], Constants.INVOKESPECIAL));//[]
        il.append(InstructionConstants.ALOAD_0);//[this]
        il.append(InstructionConstants.ALOAD_1);//[this, arg]
        il.append(instructionFactory.createPutField(classGenerator.getClassName(), "container", Type.getType(Container.class)));//[]
        il.append(InstructionConstants.RETURN);
        methodGen.setMaxStack();
        methodGen.setMaxLocals();
        Method result = methodGen.getMethod();
        return result;
    }

    public Method create(ClassGen classGenerator, Type returnType, Class[] argumentClasses)
    {
        Type[] argumentTypes = Type.getTypes(argumentClasses);
        int accessFlags = Constants.ACC_PUBLIC;
        String[] argumentNames = new String[argumentTypes.length];
        for (int i = 0; i < argumentNames.length; i++)
            argumentNames[i] = "arg" + i;
        String className = classGenerator.getClassName();
        InstructionList il = new InstructionList();
        String methodName = "create";
        ConstantPoolGen constantPool = classGenerator.getConstantPool();

        //constant pool filling
        //end constant pool filling

        MethodGen methodGen = new MethodGen(accessFlags, returnType, argumentTypes, argumentNames, methodName, className, il, constantPool);
        InstructionFactory instructionFactory = new InstructionFactory(classGenerator);

        //taking Printer
        il.append(new ALOAD(0));//[this]
        il.append(instructionFactory.createGetField(className, "container", Type.getType(Container.class)));//[printer]

        il.append(new LDC_W(getIndexInConstantPool(constantPool, returnType.toString())));


        int lengthIndex = constantPool.addInteger(argumentTypes.length);
        il.append(new LDC(lengthIndex));//[printer, length]
        il.append(instructionFactory.createNewArray(Type.getType(AbstractionInstancePair.class), (short) 1));//[printer, array]

        for (int i = 0; i < argumentTypes.length; i++)
        {
            il.append(new DUP());//[printer, array, array]
            int curIndex = constantPool.addInteger(i);
            il.append(new LDC(curIndex));//[printer, array, array, i]
            il.append(instructionFactory.createNew(new ObjectType(AbstractionInstancePair.class.getName())));//[printer, array, array, i, pair]
            il.append(new DUP());//[printer, array, array, i, pair, pair]

            if (argumentTypes[i].equals(Type.INT))
            {
                il.append(instructionFactory.createGetStatic("java.lang.Integer", "TYPE", Type.CLASS));
                il.append(instructionFactory.createNew((ObjectType) Type.getType(Integer.class)));
                il.append(new DUP());
                il.append(new ILOAD(i + 1));
                il.append(instructionFactory.createInvoke("java.lang.Integer", "<init>", Type.VOID, new Type[]{Type.INT}, Constants.INVOKESPECIAL));
            }
            else if (argumentTypes[i].equals(Type.SHORT))
            {
                il.append(instructionFactory.createGetStatic("java.lang.Short", "TYPE", Type.CLASS));
                il.append(instructionFactory.createNew((ObjectType) Type.getType(Short.class)));
                il.append(new DUP());
                il.append(new ILOAD(i + 1));
                il.append(instructionFactory.createInvoke("java.lang.Short", "<init>", Type.VOID, new Type[]{Type.SHORT}, Constants.INVOKESPECIAL));
            }
            else if (argumentTypes[i].equals(Type.LONG))
            {
                il.append(instructionFactory.createGetStatic("java.lang.Long", "TYPE", Type.CLASS));
                il.append(instructionFactory.createNew((ObjectType) Type.getType(Long.class)));
                il.append(new DUP());
                il.append(new LLOAD(i + 1));
                il.append(instructionFactory.createInvoke("java.lang.Long", "<init>", Type.VOID, new Type[]{Type.LONG}, Constants.INVOKESPECIAL));
            }
            else if (argumentTypes[i].equals(Type.BYTE))
            {
                il.append(instructionFactory.createGetStatic("java.lang.Byte", "TYPE", Type.CLASS));
                il.append(instructionFactory.createNew((ObjectType) Type.getType(Byte.class)));
                il.append(new DUP());
                il.append(new ILOAD(i + 1));
                il.append(instructionFactory.createInvoke("java.lang.Byte", "<init>", Type.VOID, new Type[]{Type.BYTE}, Constants.INVOKESPECIAL));
            }
            else if (argumentTypes[i].equals(Type.CHAR))
            {
                il.append(instructionFactory.createGetStatic("java.lang.Character", "TYPE", Type.CLASS));
                il.append(instructionFactory.createNew((ObjectType) Type.getType(Character.class)));
                il.append(new DUP());
                il.append(new ILOAD(i + 1));
                il.append(instructionFactory.createInvoke("java.lang.Character", "<init>", Type.VOID, new Type[]{Type.CHAR}, Constants.INVOKESPECIAL));
            }
            else  if (argumentTypes[i].equals(Type.BOOLEAN))
            {
                il.append(instructionFactory.createGetStatic("java.lang.Boolean", "TYPE", Type.CLASS));
                il.append(instructionFactory.createNew((ObjectType) Type.getType(Boolean.class)));
                il.append(new DUP());
                il.append(new ILOAD(i + 1));
                il.append(instructionFactory.createInvoke("java.lang.Boolean", "<init>", Type.VOID, new Type[]{Type.BOOLEAN}, Constants.INVOKESPECIAL));
            }
            else if (argumentTypes[i].equals(Type.DOUBLE))
            {
                il.append(instructionFactory.createGetStatic("java.lang.Double", "TYPE", Type.CLASS));
                il.append(instructionFactory.createNew((ObjectType) Type.getType(Double.class)));
                il.append(new DUP());
                il.append(new DLOAD(i + 1));
                il.append(instructionFactory.createInvoke("java.lang.Double", "<init>", Type.VOID, new Type[]{Type.DOUBLE}, Constants.INVOKESPECIAL));
            }
            else if (argumentTypes[i].equals(Type.FLOAT))
            {
                il.append(instructionFactory.createGetStatic("java.lang.Float", "TYPE", Type.CLASS));
                il.append(instructionFactory.createNew((ObjectType) Type.getType(Float.class)));
                il.append(new DUP());
                il.append(new FLOAD(i + 1));
                il.append(instructionFactory.createInvoke("java.lang.Float", "<init>", Type.VOID, new Type[]{Type.FLOAT}, Constants.INVOKESPECIAL));
            }
            else
            {
                //first argument
                int classIndex = getIndexInConstantPool(constantPool, getClassName(argumentClasses[i]));
                il.append(new LDC_W(classIndex));//[printer, array, array, i, pair, pair, classarg]
                //second argument
                il.append(new ALOAD(i + 1));//[printer, array, array, i, pair, pair, classarg, objectarg]
            }
            il.append(instructionFactory.createInvoke("overclocking.jrobocontainer.container.AbstractionInstancePair", "<init>", Type.VOID, new Type[]{Type.CLASS, Type.OBJECT}, Constants.INVOKESPECIAL));//[printer, array, array, i, pair]
            il.append(new AASTORE());//[printer, array]
        }
        il.append(instructionFactory.createInvoke("overclocking.jrobocontainer.container.Container", "create", Type.OBJECT, new Type[]{Type.CLASS, Type.getType(AbstractionInstancePair[].class)}, Constants.INVOKEVIRTUAL));//[String]
        il.append(instructionFactory.createCast(Type.OBJECT, returnType));
        il.append(new ARETURN());

        methodGen.setMaxStack();
        methodGen.setMaxLocals();
        Method result = methodGen.getMethod();
        return result;
    }

    private String getClassName(Class type)
    {
        return type.getName();
    }

    private int getIndexInConstantPool(ConstantPoolGen constantPool, String name)
    {
        return constantPool.addClass(name);
    }
}
