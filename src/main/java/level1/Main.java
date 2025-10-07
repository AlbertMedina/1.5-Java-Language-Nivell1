package level1;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        FileUtils.saveDirectoryContentAlphabetically("src" + File.separator + "main", true);
        
        System.out.println(FileUtils.getSavedDirectoryContent());
    }
}
