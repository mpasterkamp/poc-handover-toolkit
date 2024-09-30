package nl.axxes.poc_handover_toolkit.security;

import jakarta.annotation.PostConstruct;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.security.Security;

@Configuration
public class SecurityConfig {

    private static final String ALGORITHM = "AES";

    @PostConstruct
    public void setupBouncyCastleProvider() {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Bean
    public SecretKey secretKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM, "BC");
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }

    @Bean
    public IvParameterSpec ivParameterSpec() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }
}
