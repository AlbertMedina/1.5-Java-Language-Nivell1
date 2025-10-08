package level3;

import Utils.EncryptionUtils;
import Utils.FileUtils;

import javax.crypto.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

public class Main {

    private static final String PROPERTIES_FILE_PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "config.properties";

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE_PATH)) {

            properties.load(fileInputStream);
            String directoryContentTreeFilePath = properties.getProperty("DIRECTORY_CONTENT_TREE_FILE_PATH").replace("/", File.separator);
            String directoryContentTreeEncryptedFilePath = properties.getProperty("DIRECTORY_CONTENT_TREE_ENCRYPTED_FILE_PATH").replace("/", File.separator);
            String text = FileUtils.readFromTxt(directoryContentTreeFilePath);

            // we use key generator to get a random password because we just want to test the encryption is working properly
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            SecretKey password = keyGen.generateKey();

            System.out.println("Text:");
            System.out.println(text);

            System.out.println();

            byte[] encryptedText = EncryptionUtils.encrypt(text, password);
            System.out.println("Crypted text:");
            System.out.println(Base64.getEncoder().encodeToString(encryptedText));

            FileUtils.saveToTxt(Base64.getEncoder().encodeToString(encryptedText), directoryContentTreeEncryptedFilePath);

            System.out.println();

            String decryptedText = EncryptionUtils.decrypt(encryptedText, password);
            System.out.println("Decrypted text:");
            System.out.println(decryptedText);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
