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

        /*
         * Cada bucle representa una posició a la contrassenya
         * Cada bucle genera totes les posibles contrassenyes per la seva longitud
         */

        char[] chars = new char[6];

        // Pel primer caracter
        for (char u : charset) { // a
            chars[0] = u;

            // Es crea un nou array de chars que nomes conté els caracters
            String result = testPassword(alg, hash, salt, chars, 0);
            if (result != null)
                return result;

            // Pel segon caracter
            for (char dos : charset) { // aa ab
                chars[1] = dos;
                result = testPassword(alg, hash, salt, chars, 1);
                if (result != null)
                    return result;

                // Pel tercer caracter
                for (char tres : charset) { // aaa aba
                    chars[2] = tres;
                    result = testPassword(alg, hash, salt, chars, 2);
                    if (result != null)
                        return result;

                    // Pel quart caracter
                    for (char quatre : charset) { // aaaa abaa
                        chars[3] = quatre;
                        result = testPassword(alg, hash, salt, chars, 3);
                        if (result != null)
                            return result;

                        // Pel cinqué caracter
                        for (char cinc : charset) { // aaaaa abaaa
                            chars[4] = cinc;
                            result = testPassword(alg, hash, salt, chars, 4);
                            if (result != null)
                                return result;

                            // Pel sisé caracter
                            for (char sis : charset) { // aaaaaa abaaaa
                                chars[5] = sis;
                                result = testPassword(alg, hash, salt, chars, 5);
                                if (result != null)
                                    return result;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private String testPassword(String alg, String hash, String salt, char[] charset, int length) throws Exception {
        npass++;

        String pwStr = stringConverter(charset, length);
        String generatedHash = alg.equals("SHA-512") ? getSHA512AmbSalt(pwStr, salt) : getPBKDF2AmbSalt(pwStr, salt);

        if (generatedHash.equals(hash)) {
            return pwStr;
        }
        return null;

    }

    // Metode auxiliar per convertir el char de password a string, per que amb new
    // string no ho pilla
    private String stringConverter(char[] pw, int len) {
        StringBuffer password = new StringBuffer();
        for (int i = 0; i <= len; i++) {
            password.append(pw[i]);
        }
        return password.toString();
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
