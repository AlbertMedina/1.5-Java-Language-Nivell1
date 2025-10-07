package level1;

import java.io.File;

public class Main {

    private static final String SERIALIZED_OBJECT_PATH = "resources/serialized-object.txt";

    public static void main(String[] args) {

        // saving directory content
        FileUtils.saveDirectoryContentAlphabetically("src" + File.separator + "main", true);

        // printing saved directory content
        System.out.println(FileUtils.getSavedDirectoryContent());

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
