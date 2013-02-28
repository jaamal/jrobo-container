package overclocking.jrobocontainer.logging;

/**
 * Created with IntelliJ IDEA.
 * User: mr146
 * Date: 01.03.13
 * Time: 2:07
 * To change this template use File | Settings | File Templates.
 */
public class Log implements ILog {
    private StringBuilder log;
    private int depth;
    private final String spaces = "  ";
    public Log()
    {
        log = new StringBuilder();
        depth = 0;
    }

    private void append(String message)
    {
        for(int i = 0; i < depth; i++)
            log.append(spaces);
        log.append(message);
        log.append("\r\n");
    }

    @Override
    public void beginGet(Class<?> clazz) {
        append("Getting " + clazz.getCanonicalName());
        depth++;
    }

    @Override
    public void endGet(Class<?> clazz) {
        depth--;
        append("End getting " + clazz.getCanonicalName());
    }

    @Override
    public void beginCreate(Class<?> clazz) {
        append("Creating " + clazz.getCanonicalName());
        depth++;
    }

    @Override
    public void endCreate(Class<?> clazz) {
        depth--;
        append("End creating " + clazz.getCanonicalName());
    }

    @Override
    public void beginGetAll(Class<?> clazz) {
        append("Getting all " + clazz.getCanonicalName());
        depth++;
    }

    @Override
    public void endGetAll(Class<?> clazz) {
        depth--;
        append("End getting all " + clazz.getCanonicalName());
    }

    @Override
    public void reuse(Class<?> clazz) {
        append("Reusing " + clazz.getCanonicalName());
    }

    @Override
    public String getLog() {
        return log.toString();
    }
}
