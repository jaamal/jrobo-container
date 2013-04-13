package overclocking.jrobocontainer.factories;

public class EmitedClassLoader extends ClassLoader
{
    Class<?> loadClass(byte[] bytecode)
    {
        return defineClass(null, bytecode, 0, bytecode.length);
    }
}

