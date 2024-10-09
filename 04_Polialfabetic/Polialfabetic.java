import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Polialfabetic {

    public static final char[] abecedari = "aàáäâãbcçdeèéëêfghiìíïîjklmnñoòóöôõpqrstuùúüûvwxyýÿz".toCharArray();

    public static char[] permutat = permutaAlfabet();

    public static int clauSecreta = 132445632;
    public static Random semilla;

    public static void main(String[] args) {
        String msgs[] = { "Test 01 àrbritre, coixí, Perímetre",

                "Test 02 Taüll, DÍA, año",
                "Test 03 Peça, Òrrius, Bòvila" };
        String msgsXifrats[] = new String[msgs.length];
        System.out.println("Xifratge: \n --------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }
        System.out.println("Desxifratge: \n -----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }

    public static char[] permutaAlfabet() {

        List<Character> llista = new ArrayList<>();

        for (char i : abecedari) {
            llista.add(i);
        }
        Collections.shuffle(llista);

        char[] permutat = new char[abecedari.length];

        for (int i = 0; i < llista.size(); i++) {
            permutat[i] = llista.get(i);
        }

        return permutat;
    }

    public static String xifraPoliAlfa(String msg) {

        StringBuilder xifrat = new StringBuilder();

        // Recorrer la cadena
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i); // Guardo la lletra actual

            // Comprovar si la lletra es majúscula
            boolean esMaj = Character.isUpperCase(c);

            c = Character.toLowerCase(c); // En qualsevol cas la passo a minuscula per buscar

            int pos = trobarPosicio(abecedari, c); // Busca en quina posició esta la lletra, -1 si no la troba

            // Si ha trobat la posició
            if (pos != -1) {
                permutaAlfabet();

                if (esMaj) {
                    xifrat.append(Character.toUpperCase(permutat[pos])); // Si es majuscula afegirlo com a tal
                } else {
                    xifrat.append(permutat[pos]);
                }
            } else {
                xifrat.append(c); // Afegir el caracter tal cual, pels espais i altres signes
            }
        }
        return xifrat.toString();
    }

    public static String desxifraPoliAlfa(String msgXifrat) {

        StringBuilder desxifrat = new StringBuilder();

        // Recorrer la cadena xifrada
        for (int i = 0; i < msgXifrat.length(); i++) {
            char c = msgXifrat.charAt(i); // Guardo la lletra actual

            // Comprovar si la lletra es majúscula
            boolean esMaj = Character.isUpperCase(c);

            c = Character.toLowerCase(c); // En qualsevol cas la passo a minuscula per buscar

            int pos = trobarPosicio(permutat, c); // Busca en quina posició esta la lletra, -1 si no la troba

            // Si ha trobat la posició
            if (pos != -1) {
                permutaAlfabet();

                if (esMaj) {
                    desxifrat.append(Character.toUpperCase(abecedari[pos])); // Si es majuscula afegirlo com a tal
                } else {
                    desxifrat.append(abecedari[pos]);
                }
            } else {
                desxifrat.append(c); // Afegir el caracter tal cual, pels espais i altres signes
            }
        }
        return desxifrat.toString();

    }

    private static void initRandom(int password) {
        semilla = new Random(password); // Utilitzar la contrasenya com a semilla
    }

    // Funció per trobar la posició d'un caràcter a un array
    private static int trobarPosicio(char[] array, char c) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) {
                return i;
            }
        }
        return -1; // Si no es troba
    }

}
