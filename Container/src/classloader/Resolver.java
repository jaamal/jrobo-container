package classloader;

import exceptions.AmbiguousImplementationMatchException;
import exceptions.ImplementationNotFoundException;
import exceptions.JRoboContainerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class Resolver {

    public static Class<?> resolveClass(Class<?> requiredAbstraction,
                                 ArrayList<Class<?>> implementations) throws JRoboContainerException {
        if (implementations == null || implementations.size() == 0) {
            throw new ImplementationNotFoundException(requiredAbstraction);
        }
        if (implementations.size() > 1) {
            throw new AmbiguousImplementationMatchException(requiredAbstraction);
        }
        return implementations.get(0);
    }


    Logger logger = LogManager.getLogger(Resolver.class);
}

