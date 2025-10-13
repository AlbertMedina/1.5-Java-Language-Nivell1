package level2;

import Utils.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesDemo {

    private static final String PROPERTIES_FILE_PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "config.properties";

    public PropertiesDemo() {
    }

    public void start() {
        try {
            // getting properties
            Properties properties = loadProperties(PROPERTIES_FILE_PATH);
            String directoryPath = properties.getProperty("DIRECTORY_PATH").replace("/", File.separator);
            String directoryContentTreeFilePath = properties.getProperty("DIRECTORY_CONTENT_TREE_FILE_PATH").replace("/", File.separator);

            // saving directory content tree
            String directoryContentTree = FileUtils.getDirectoryContentAlphabetically(directoryPath, true);
            FileUtils.saveToTxt(directoryContentTree, directoryContentTreeFilePath);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private Properties loadProperties(String path) throws IOException {
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(path)) {
            properties.load(inputStream);
        }
        return properties;
    }
}
