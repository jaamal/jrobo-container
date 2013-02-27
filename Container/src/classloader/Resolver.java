package classloader;

import exceptions.AmbiguousImplementationMatchException;
import exceptions.ImplementationNotFoundException;
import exceptions.JRoboContainerException;

import java.util.ArrayList;

public class Resolver {

    public static Class<?> resolveClass(Class<?> requiredAbstraction,
                                 ArrayList<Class<?>> implementations) throws JRoboContainerException {
        if (implementations == null || implementations.size() == 0) {
            throw new ImplementationNotFoundException(requiredAbstraction);
        }
        if (implementations.size() > 1) {
            String implementationsString = "";
            for(Class<?> implementation : implementations)
            implementationsString = implementationsString + implementation.getCanonicalName() + System.getProperty("line.separator");
            throw new AmbiguousImplementationMatchException(requiredAbstraction, implementationsString);
        }
        return implementations.get(0);
    }
}

