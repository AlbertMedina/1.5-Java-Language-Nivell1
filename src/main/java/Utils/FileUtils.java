package Utils;

import java.io.*;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class FileUtils {


    public FileUtils() {
    }

    public static String getDirectoryContentAlphabetically(String path, boolean includeFullTree) {
        File file = new File(path);
        return includeFullTree ? getDirectoryContentTreeAlphabetically(file, 0) : getDirectoryContentAlphabetically(file);
    }

    public static void saveToTxt(String directoryContent, String path) {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)))) {
            pw.println(directoryContent);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String readFromTxt(String path) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
                if (line != null) {
                    sb.append("\n");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }

    public static void serializeObject(Object obj, String path) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Object deserializeObject(String path) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))) {
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static String getDirectoryContentAlphabetically(File file) {
        if (file.exists()) {
            StringBuilder sb = new StringBuilder();
            sb.append("- ").append(file.getName()).append(" (").append(file.isDirectory() ? "D" : "F").append(") - Last modification: ").append(new Date(file.lastModified()));
            if (file.isDirectory()) {
                File[] filesArr = file.listFiles();
                if (filesArr != null) {
                    List<File> filesList = Stream.of(filesArr).sorted(Comparator.comparing(f -> f.getName().toLowerCase())).toList();
                    for (File f : filesList) {
                        sb.append("\n  - ").append(f.getName()).append(" (").append(f.isDirectory() ? "D" : "F").append(") - Last modification: ").append(new Date(f.lastModified()));
                    }
                }
            }
            return sb.toString();
        }
        return "";
    }

    private static String getDirectoryContentTreeAlphabetically(File file, int depth) {
        if (file.exists()) {
            StringBuilder sb = new StringBuilder();
            sb.append("  ".repeat(depth)).append("- ").append(file.getName()).append(" (").append(file.isDirectory() ? "D" : "F").append(") - Last modification: ").append(new Date(file.lastModified()));
            if (file.isDirectory()) {
                File[] filesArr = file.listFiles();
                if (filesArr != null) {
                    List<File> filesList = Stream.of(filesArr).sorted(Comparator.comparing(f -> f.getName().toLowerCase())).toList();
                    for (File f : filesList) {
                        sb.append("\n").append(getDirectoryContentTreeAlphabetically(f, depth + 1));
                    }
                }
            }
            return sb.toString();
        }
        return "";
    }
}
