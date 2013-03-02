package overclocking.jrobocontainer.logging;

/**
 * Created with IntelliJ IDEA.
 * User: mr146
 * Date: 02.03.13
 * Time: 12:07
 * To change this template use File | Settings | File Templates.
 */
public class ClassesLoadingLog implements IClassesLoadingLog {

    private StringBuilder log;

    public ClassesLoadingLog()
    {
        log = new StringBuilder();
    }
    @Override
    public String getLog() {
        return log.toString();
    }

    @Override
    public void append(String location) {
        log.append(location + "\r\n");
    }
}
