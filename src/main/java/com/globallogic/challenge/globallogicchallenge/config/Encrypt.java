package com.globallogic.challenge.globallogicchallenge.config;

import com.globallogic.challenge.globallogicchallenge.exception.errorModels.InternalServerErrorEx;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

@Configuration
public class Encrypt {

    @Bean
    public Encrypt getEncryptInstance() throws InternalServerErrorEx {
        return new Encrypt();
    }

    private static final String key = "kz4QRFIo9UbnbC8QzNoL";
    private static final String salt = "VZKa3nxa6Bztp69sdreY";

    private SecretKey secretKeyTmp;

    public Encrypt() throws InternalServerErrorEx {
        SecretKeyFactory secretKeyFactory;
        KeySpec keySpec;
        try {
            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            keySpec = new PBEKeySpec(key.toCharArray(), salt.getBytes(), 6536, 128);
            secretKeyTmp = secretKeyFactory.generateSecret(keySpec);
        } catch (Exception ex) {
            throw new InternalServerErrorEx("Encrypt generator instance error.");
        }
    }

    public String getAES(String data) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[16]);
            SecretKeySpec secretKey = new SecretKeySpec(secretKeyTmp.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAESDecrypt(String data) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[16]);
            SecretKeySpec secretKey = new SecretKeySpec(secretKeyTmp.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

            return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
