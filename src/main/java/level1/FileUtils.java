package level1;

import java.io.*;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class FileUtils {

    private static final String DIRECTORY_CONTENT_FILE_PATH = "resources/directory-content.txt";

    public FileUtils() {
    }

    public static void saveDirectoryContentAlphabetically(String path, boolean showFullTree) {
        File file = new File(path);
        String directoryContent = getDirectoryContentAlphabetically(file, showFullTree, 0);
        saveToTxt(directoryContent, DIRECTORY_CONTENT_FILE_PATH);
    }

    private static String getDirectoryContentAlphabetically(File file, boolean showFullTree, int depth) {
        if (file.exists()) {
            StringBuilder sb = new StringBuilder();
            sb.append("  ".repeat(depth)).append("- ").append(file.getName()).append(" (").append(file.isDirectory() ? "D" : "F").append(") - Last modification: ").append(new Date(file.lastModified()));
            if (file.isDirectory()) {
                File[] filesArr = file.listFiles();
                if (filesArr != null) {
                    List<File> filesList = Stream.of(filesArr).sorted(Comparator.comparing(f -> f.getName().toLowerCase())).toList();
                    for (File f : filesList) {
                        if (showFullTree) {
                            sb.append("\n").append(getDirectoryContentAlphabetically(f, true, depth + 1));
                        } else {
                            sb.append("\n").append("  ".repeat(depth + 1)).append("- ").append(f.getName()).append(" (").append(f.isDirectory() ? "D" : "F").append(")- Last modification: ").append(new Date(f.lastModified()));
                        }
                    }
                }
            }
            return sb.toString();
        }
        return "";
    }

    private static void saveToTxt(String directoryContent, String path) {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)))) {
            pw.println(directoryContent);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
