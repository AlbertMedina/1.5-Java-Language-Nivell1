package Utils;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtils {

    public EncryptionUtils() {
    }

    public static byte[] encrypt(String text, SecretKey password) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, password);
        return cipher.doFinal(text.getBytes());
    }

    public static String decrypt(byte[] encryptedText, SecretKey password) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, password);
        byte[] decryptedText = cipher.doFinal(encryptedText);
        return new String(decryptedText);
    }
}
