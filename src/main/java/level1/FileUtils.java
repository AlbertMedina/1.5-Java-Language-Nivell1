package level1;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class FileUtils {

    public FileUtils() {
    }

    public static void printDirectoryContentAlphabetically(String path) {
        File file = new File(path);
        if (file.exists()) {
            System.out.println("- " + file.getName());
            if (file.isDirectory()) {
                File[] filesArr = file.listFiles();
                if (filesArr != null) {
                    List<File> filesList = Stream.of(filesArr).sorted(Comparator.comparing(f -> f.getName().toLowerCase())).toList();
                    for (File f : filesList) {
                        System.out.println("  - " + f.getName());
                    }
                }
            }
        }
    }
}
