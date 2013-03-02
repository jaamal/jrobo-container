package overclocking.jrobocontainer.logging;

/**
 * Created with IntelliJ IDEA.
 * User: mr146
 * Date: 02.03.13
 * Time: 12:06
 * To change this template use File | Settings | File Templates.
 */
public interface IClassesLoadingLog {
    String getLog();
    void append(String location);
}
