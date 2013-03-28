package overclocking.jrobocontainer.classpathscanning;

import overclocking.jrobocontainer.exceptions.AmbiguousImplementationMatchException;
import overclocking.jrobocontainer.exceptions.ImplementationNotFoundException;
import overclocking.jrobocontainer.storages.ClassNode;
import overclocking.jrobocontainer.storages.IClassNodesStorage;

import java.util.ArrayList;

public class Resolver
{

    public static String resolveClass(String requiredAbstractionId,
                                        ArrayList<String> implementations, IClassNodesStorage classNodesStorage)
    {
        ClassNode requiredAbstraction = classNodesStorage.getClassNodeById(requiredAbstractionId);
        if (implementations == null || implementations.size() == 0)
        {
            throw new ImplementationNotFoundException(requiredAbstraction);
        }
        if (implementations.size() > 1)
        {
            String implementationsString = "";
            for (String implementation : implementations)
                implementationsString = implementationsString + classNodesStorage.getClassNodeById(implementation).getClassName() + System.getProperty("line.separator");
            throw new AmbiguousImplementationMatchException(requiredAbstraction, implementationsString);
        }
        return implementations.get(0);
    }
}

