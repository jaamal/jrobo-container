package overclocking.jrobocontainer.factories;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.FieldGen;
import org.apache.bcel.generic.Type;

public class FieldCreator
{
    public Field create(String name, Class type, ClassGen classGen)
    {
        int accessFlags = Constants.ACC_PRIVATE;
        FieldGen fieldGen = new FieldGen(accessFlags, Type.getType(type), name, classGen.getConstantPool());
        return fieldGen.getField();
    }
}
