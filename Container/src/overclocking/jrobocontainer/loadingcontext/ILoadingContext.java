package overclocking.jrobocontainer.loadingcontext;

import java.util.ArrayList;

public interface ILoadingContext
{
    <T> void addClass(Class<T> clazz);

    void addByteCode(byte[] byteCode);

    ArrayList<byte[]> getByteCodes();

    void appendToLog(String message);

    String getLog();

    ArrayList<Class<?>> getClasses();

    ClassLoader getMainClassLoader();
}
