package level3;

import Utils.EncryptionUtils;
import Utils.FileUtils;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

public class EncryptionService {

    private static final String PROPERTIES_FILE_PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "config.properties";

    public EncryptionService() {
    }

    public void start() {
        try {
            Properties properties = loadProperties(PROPERTIES_FILE_PATH);
            String directoryContentTreeFilePath = properties.getProperty("DIRECTORY_CONTENT_TREE_FILE_PATH").replace("/", File.separator);
            String directoryContentTreeEncryptedFilePath = properties.getProperty("DIRECTORY_CONTENT_TREE_ENCRYPTED_FILE_PATH").replace("/", File.separator);
            String text = FileUtils.readFromTxt(directoryContentTreeFilePath);

            // we use key generator to get a random password because we just want to test the encryption is working properly
            SecretKey password = generateRandomSecretKey();

            System.out.println("Text:");
            System.out.println(text);

            System.out.println();

            byte[] encryptedText = encryptAndSave(text, password, directoryContentTreeEncryptedFilePath);
            System.out.println("Crypted text:");
            System.out.println(Base64.getEncoder().encodeToString(encryptedText));

            System.out.println();

            String decryptedText = decrypt(encryptedText, password);
            System.out.println("Decrypted text:");
            System.out.println(decryptedText);

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

    private SecretKey generateRandomSecretKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            return keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("error generating random password: " + e.getMessage());
            return null;
        }
    }

    private byte[] encryptAndSave(String text, SecretKey password, String path) {
        try {
            byte[] encryptedText = EncryptionUtils.encrypt(text, password);
            FileUtils.saveToTxt(Base64.getEncoder().encodeToString(encryptedText), path);
            return encryptedText;
        } catch (Exception e) {
            throw new IllegalStateException("Error encrypting or saving: " + e.getMessage(), e);
        }
    }

    private String decrypt(byte[] encryptedText, SecretKey password) {
        try {
            return EncryptionUtils.decrypt(encryptedText, password);
        } catch (Exception e) {
            throw new IllegalStateException("Error decrypting: " + e.getMessage(), e);
        }
    }
}
