package DateBase;

public interface StorageSystem {
    void saveLogToFile(String log);
    String loadLogFromFile();
}
