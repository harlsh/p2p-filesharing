package config;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Data
public class CommonConfigLoader {
    InputStream configFile;
    CommonConfig commonConfig;

    public CommonConfigLoader(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        classLoader.getResourceAsStream(fileName);
        configFile = classLoader.getResourceAsStream(fileName);
        commonConfig = new CommonConfig();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(configFile));
            String line = reader.readLine();
            String value = line.split(" ")[1];
            commonConfig.setNumberOfPreferredNeighbors(Integer.parseInt(value));
            line = reader.readLine();
            value = line.split(" ")[1];
            commonConfig.setUnchockingInterval(Integer.parseInt(value));
            line = reader.readLine();
            value = line.split(" ")[1];
            commonConfig.setOptimisticUnchockingInterval(Integer.parseInt(value));
            line = reader.readLine();
            value = line.split(" ")[1];
            commonConfig.setFileName(value);
            line = reader.readLine();
            value = line.split(" ")[1];
            commonConfig.setFileSize(Long.parseLong(value));
            line = reader.readLine();
            value = line.split(" ")[1];
            commonConfig.setPieceSize(Long.parseLong(value));
            System.out.println("Parsed the " + fileName + " successfully.");
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
