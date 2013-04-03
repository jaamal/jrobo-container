package overclocking.jrobocontainer.logging;

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
