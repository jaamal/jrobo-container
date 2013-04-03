package overclocking.jrobocontainer.logging;

/**
 * Created with IntelliJ IDEA.
 * User: mr146
 * Date: 02.03.13
 * Time: 12:07
 * To change this template use File | Settings | File Templates.
 */
public class LoadingLog extends Log implements ILoadingLog
{
    public LoadingLog()
    {
        super();
    }

    @Override
    public void beginScanDirectory(String directoryName) {
        begin("Scanning directory " + directoryName);
    }

    @Override
    public void endScanDirectory() {
        end("Directory scanned");

    }

    @Override
    public void beginScanFile(String fileName) {
        begin("Scanning file " + fileName);
    }

    @Override
    public void endScanFile() {
        end("File scanned");
    }

    @Override
    public void scanFileSuccess(String className) {
        append("Scan result is class " + className);
    }

    @Override
    public void scanFileFail(String reason) {
        append("Failed to scan file because: " + reason);
    }

    @Override
    public void skipJarFile() {
        append("Sipping this jar");
    }

    @Override
    public void beginScanJarEntry(String entryName) {
        begin("Scanning entry " + entryName);
    }

    @Override
    public void endScanJarEntry() {
        end("Entry scanned");
    }

    @Override
    public void scanEntrySuccess(String className) {
        append("Entry scan result is " + className);
    }

    @Override
    public void scanEntryFail(String reason) {
        append("Failed to scan entry because: " + reason);
    }
}
