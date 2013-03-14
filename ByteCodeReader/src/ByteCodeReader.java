import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class ByteCodeReader implements IByteCodeReader
{

    @Override
    public ClassInfo getClassInfo(byte[] byteCode)
    {
        try
        {
            DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(byteCode));
            ClassInfo result = new ClassInfo();
            result.magic = inputStream.readInt();
            return result;
        }
        catch (Exception ex)
        {
            return null;
        }
    }
}
