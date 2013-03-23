package overclocking.jrobocontainer.logging;

import overclocking.jrobocontainer.storages.ClassNode;

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
    public void beginGet(ClassNode clazz) {
        append("Getting " + clazz.getClassName());
        depth++;
    }

    @Override
    public void endGet(ClassNode clazz) {
        depth--;
        append("End getting " + clazz.getClassName());
    }

    @Override
    public void beginCreate(ClassNode clazz) {
        append("Creating " + clazz.getClassName());
        depth++;
    }

    @Override
    public void endCreate(ClassNode clazz) {
        depth--;
        append("End creating " + clazz.getClassName());
    }

    @Override
    public void beginGetAll(ClassNode clazz) {
        append("Getting all " + clazz.getClassName());
        depth++;
    }

    @Override
    public void endGetAll(ClassNode clazz) {
        depth--;
        append("End getting all " + clazz.getClassName());
    }

    @Override
    public void reuse(ClassNode clazz) {
        append("Reusing " + clazz.getClassName());
    }

    @Override
    public String getLog() {
        return log.toString();
    }
}
