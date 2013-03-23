package overclocking.jrobocontainer.classscanning;

import overclocking.jrobocontainer.exceptions.AmbiguousImplementationMatchException;
import overclocking.jrobocontainer.exceptions.ImplementationNotFoundException;
import overclocking.jrobocontainer.storages.ClassNode;

import java.util.ArrayList;

public class Resolver
{

    public static ClassNode resolveClass(ClassNode requiredAbstraction,
                                        ArrayList<ClassNode> implementations)
    {
        if (implementations == null || implementations.size() == 0)
        {
            throw new ImplementationNotFoundException(requiredAbstraction);
        }
        if (implementations.size() > 1)
        {
            String implementationsString = "";
            for (ClassNode implementation : implementations)
                implementationsString = implementationsString + implementation.getClassName() + System.getProperty("line.separator");
            throw new AmbiguousImplementationMatchException(requiredAbstraction, implementationsString);
        }
        return implementations.get(0);
    }
}

