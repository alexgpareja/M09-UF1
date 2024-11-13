package iticbcn.hashes;

import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    public String getSHA512AmbSalt(String pw, String salt) throws Exception {

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes());

        byte[] hashedPassword = md.digest(pw.getBytes());
        HexFormat hex = HexFormat.of();
        return hex.formatHex(hashedPassword);
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
        byte[] saltBytes = salt.getBytes();
        KeySpec spec = new PBEKeySpec(pw.toCharArray(), saltBytes, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        HexFormat hex = HexFormat.of();
        return hex.formatHex(hash);
    }

    int npass = 0;

    public String forcaBruta(String alg, String hash, String salt) throws Exception {
        npass = 0;
        char[] charset = "abcdefABCDEF1234567890!".toCharArray();

        // Intentos para contraseñas de longitud de 1 a 6
        for (int length = 1; length <= 6; length++) {
            String found = testPassword(alg, hash, salt, charset, length);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    private String testPassword(String alg, String hash, String salt, char[] charset, int length) throws Exception {
        
    }

        

    public String getInterval(long t1, long t2) {
        long millis = t2 - t1;
        long seconds = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60)) % 60;
        long hours = (millis / (1000 * 60 * 60)) % 24;
        long days = millis / (1000 * 60 * 60 * 24);

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", days, hours, minutes, seconds,
                millis % 1000);
    }
}
