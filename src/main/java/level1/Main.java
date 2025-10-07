package level1;

import java.io.File;

public class Main {

    private static final String DIRECTORY_PATH = "src" + File.separator + "main";
    private static final String DIRECTORY_CONTENT_TREE_FILE_PATH = "resources/directory-content.txt";
    private static final String SERIALIZED_OBJECT_PATH = "resources/serialized-object.txt";

    public static void main(String[] args) {

        // printing directory content alphabetically
        System.out.println(FileUtils.getDirectoryContentAlphabetically(DIRECTORY_PATH, false));

        System.out.println();

        // printing directory content full tree alphabetically
        System.out.println(FileUtils.getDirectoryContentAlphabetically(DIRECTORY_PATH, true));

        System.out.println();

        // saving directory content tree
        String directoryContentTree = FileUtils.getDirectoryContentAlphabetically(DIRECTORY_PATH, true);
        FileUtils.saveToTxt(directoryContentTree, DIRECTORY_CONTENT_TREE_FILE_PATH);

        // getting and printing saved directory content tree
        System.out.println(FileUtils.readFromTxt(DIRECTORY_CONTENT_TREE_FILE_PATH));

        System.out.println();

        // serializing object
        MySerializableObject myObj = new MySerializableObject("Hello", 'A', 1);
        FileUtils.serializeObject(myObj, SERIALIZED_OBJECT_PATH);

        // deserializing object
        MySerializableObject myDeserializedObj = (MySerializableObject) FileUtils.deserializeObject(SERIALIZED_OBJECT_PATH);
        if (myDeserializedObj != null) {
            System.out.println(myDeserializedObj);
        }
    }
}
