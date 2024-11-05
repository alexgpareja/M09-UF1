package iticbcn.xifratge;

import javax.crypto.spec.*;
import javax.crypto.Cipher; // S'utilitza per fer operacions de xifratge/desxifratge

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;

public class XifradorAES implements Xifrador {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";

    public byte[] xifraAES(String msg, String clau) throws Exception {
        // Obtenir els bytes de l’String
        byte[] byteMsg = msg.getBytes("UTF-8");

        // Omplir el iv amb valors randomSecure
        SecureRandom secureRandom = new SecureRandom(); // Crea una instancia de numeros aleatoris
        secureRandom.nextBytes(iv); // Omple l'array de iv amb valors aleatoris

        // Genera IvParameterSpec
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv); // Crea un objecte que encapsula el iv, per passar-lo
                                                                   // al xifratge

        // Genera hash
        MessageDigest messageDigest = MessageDigest.getInstance(ALGORISME_HASH); // Es crea una instància de l'algoritme
                                                                                 // SHA-256 per fer un hash de la clau
        byte[] clauHash = messageDigest.digest(clau.getBytes()); // Aplica el hash SHA-256 a la contrasenya
        SecretKeySpec secretKeySpec = new SecretKeySpec(clauHash, ALGORISME_XIFRAT); // Es crea un objecte que conté la
                                                                                     // clau AES generada a partir del
                                                                                     // hash de la contrasenya.

        // Encrypt.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] msgXifrat = cipher.doFinal(byteMsg); // Xifra el missatge com a array de bytes

        // Combinar IV i part xifrada.
        byte[] ivMsgXifrat = new byte[MIDA_IV + msgXifrat.length];
        System.arraycopy(iv, 0, ivMsgXifrat, 0, MIDA_IV);
        System.arraycopy(msgXifrat, 0, ivMsgXifrat, MIDA_IV, msgXifrat.length);

        // return iv+msgxifrat
        return ivMsgXifrat;
    }

    public String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {

        // Extreure l'IV.
        byte[] iv = Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);

        // Extreure la part xifrada.
        byte[] msgXifrat = Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);

        // Fer hash de la clau
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauHash = digest.digest(clau.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec secretKey = new SecretKeySpec(clauHash, ALGORISME_XIFRAT);

        // Generar IvParameterSpec
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Inicialitzar el desxifrat
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        // Desxifrar el missatge
        byte[] msgDesxifrat = cipher.doFinal(msgXifrat);

        // Retornar el missatge desxifrat com a String
        return new String(msgDesxifrat, StandardCharsets.UTF_8);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            return new TextXifrat(xifraAES(msg, clau));
        } catch (Exception e) {
            System.err.println("Error en xifrat amb AES: " + e.getMessage());
            return null; // No s'executarà degut a l'exit
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            return desxifraAES(xifrat.getBytes(), clau);
        } catch (Exception e) {
            System.err.println("Error en desxifrat amb AES: " + e.getMessage());
            return null; // No s'executarà degut a l'exit
        }
    }

}