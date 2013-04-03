package overclocking.jrobocontainer.logging;

public abstract class Log {
    private StringBuilder log;
    private int depth;
    private final String linesSeparator = "\r\n";
    private final String spaces = "  ";

    protected Log()
    {
        log = new StringBuilder();
        depth = 0;
    }


    protected void append(String message)
    {
        for(int i = 0; i < depth; i++)
            log.append(spaces);
        log.append(message);
        log.append(linesSeparator);
    }

    protected void begin(String message)
    {
        append(message);
        depth++;
    }

    protected void end(String message)
    {
        depth--;
        append(message);
    }

    public String getLog()
    {
        return log.toString();
    }
}
