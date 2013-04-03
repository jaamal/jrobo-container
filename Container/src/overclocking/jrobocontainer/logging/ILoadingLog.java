package overclocking.jrobocontainer.logging;

/**
 * Created with IntelliJ IDEA.
 * User: mr146
 * Date: 02.03.13
 * Time: 12:06
 * To change this template use File | Settings | File Templates.
 */
public interface ILoadingLog {
    String getLog();
    void beginScanDirectory(String directoryName);
    void endScanDirectory();

    void beginScanFile(String fileName);
    void endScanFile();

    void scanFileSuccess(String className);
    void scanFileFail(String reason);

    void skipJarFile();

    void beginScanJarEntry(String entryName);
    void endScanJarEntry();

    void scanEntrySuccess(String className);
    void scanEntryFail(String reason);
}
