package overclocking;

import java.lang.instrument.Instrumentation;

public class InstrumentationProvider
{
    private static Instrumentation instrumentation;
    public static void premain(String arg, Instrumentation inst)
    {
        instrumentation = inst;
    }

    public static Instrumentation getInstrumentation()
    {
        return instrumentation;
    }
}
