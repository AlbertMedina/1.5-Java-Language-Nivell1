package level2;

import Utils.FileUtils;

import java.io.*;
import java.util.Properties;

public class Main {

    private static final String PROPERTIES_FILE_PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "config.properties";

    public static void main(String[] args) {

        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE_PATH)) {

            properties.load(fileInputStream);

            // getting properties
            String directoryPath = properties.getProperty("DIRECTORY_PATH").replace("/", File.separator);
            String directoryContentTreeFilePath = properties.getProperty("DIRECTORY_CONTENT_TREE_FILE_PATH").replace("/", File.separator);

            // saving directory content tree
            String directoryContentTree = FileUtils.getDirectoryContentAlphabetically(directoryPath, true);
            FileUtils.saveToTxt(directoryContentTree, directoryContentTreeFilePath);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
