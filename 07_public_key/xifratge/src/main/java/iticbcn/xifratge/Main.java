package iticbcn.xifratge;

import java.security.KeyPair;
import javax.xml.bind.DatatypeConverter;

public class Main {
    public static void main(String args[]) throws Exception {

        ClauPublica cp = new ClauPublica();

        String msg = "Missatge de prova per xifrar áéíóú àèìòù äëïöü";

        // Genera el parell de claus (clau pública i privada)
        KeyPair parellClaus = cp.generaParellClausRSA();

        // Xifra el missatge amb la clau pública
        byte[] msgXifrat = cp.xifraRSA(msg, parellClaus.getPublic());

        System.out.println("=================================");
        System.out.print("Text xifrat: ");
        System.out.println(DatatypeConverter.printHexBinary(msgXifrat));

        // Desxifra el missatge amb la clau privada
        String msgDesxifrat = cp.desxifraRSA(msgXifrat, parellClaus.getPrivate());
        System.out.println("=================================");
        System.out.println("Text desxifrat: " + msgDesxifrat);

        // Mostra la clau pública en format hexadecimal
        String strClauPub = DatatypeConverter.printHexBinary(parellClaus.getPublic().getEncoded());
        System.out.println("=================================");
        System.out.println("Clau pública: " + strClauPub);

        // Mostra la clau privada en format hexadecimal
        String strClauPriv = DatatypeConverter.printHexBinary(parellClaus.getPrivate().getEncoded());
        System.out.println("=================================");
        System.out.println("Clau privada: " + strClauPriv);
    }
}
