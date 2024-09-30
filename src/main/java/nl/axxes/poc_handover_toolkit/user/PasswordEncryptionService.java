package nl.axxes.poc_handover_toolkit.user;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

@Service
public class PasswordEncryptionService {

    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    private final SecretKey secretKey;
    private final IvParameterSpec ivParameterSpec;

    public PasswordEncryptionService(SecretKey secretKey, IvParameterSpec ivParameterSpec) {
        this.secretKey = secretKey;
        this.ivParameterSpec = ivParameterSpec;
    }

    public String encryptPassword(String password) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION, "BC");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decryptPassword(String encryptedPassword) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION, "BC");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        return new String(decrypted);
    }
}
