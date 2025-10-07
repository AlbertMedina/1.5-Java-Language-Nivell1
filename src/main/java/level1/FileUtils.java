package level1;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class FileUtils {

    public FileUtils() {
    }

    public static void printDirectoryContentAlphabetically(String path, boolean showFullTree) {
        File file = new File(path);
        printDirectoryContentAlphabetically(file, showFullTree, 0);
    }

    private static void printDirectoryContentAlphabetically(File file, boolean showFullTree, int depth) {
        if (file.exists()) {
            System.out.println("  ".repeat(depth) + "- " + file.getName() + " (" + (file.isDirectory() ? "D" : "F") + ")");
            if (file.isDirectory()) {
                File[] filesArr = file.listFiles();
                if (filesArr != null) {
                    List<File> filesList = Stream.of(filesArr).sorted(Comparator.comparing(f -> f.getName().toLowerCase())).toList();
                    for (File f : filesList) {
                        if (showFullTree) {
                            printDirectoryContentAlphabetically(f, true, depth + 1);
                        } else {
                            System.out.println("  - " + f.getName());
                        }
                    }
                }
            }
        }
    }
}
