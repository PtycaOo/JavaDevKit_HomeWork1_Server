package DateBase;

import java.io.*;

public class WorkWithFile implements StorageSystem {


    public void saveLogToFile(String s){
        String filePath = "DateBase/log.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath,true))){
            writer.println(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String loadLogFromFile() {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("DateBase/log.txt"))) {
            String line;
            while((line = bufferedReader.readLine()) != null){
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
        }
        return sb.toString();
    }
}
