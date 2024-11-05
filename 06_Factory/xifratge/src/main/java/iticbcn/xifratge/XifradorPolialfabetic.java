package iticbcn.xifratge;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {

    public static final char[] abecedari = "aàáäâãbcçdeèéëêfghiìíïîjklmnñoòóöôõpqrstuùúüûvwxyýÿz".toCharArray();

    public char[] permutat = permutaAlfabet();

    public int clauSecreta = 132445632;
    public Random semilla;

    public char[] permutaAlfabet() {

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

    public String xifraPoliAlfa(String msg) {
        return procesaPoliAlfa(msg, abecedari, permutat);
    }

    public String desxifraPoliAlfa(String msgXifrat) {
        return procesaPoliAlfa(msgXifrat, permutat, abecedari);
    }

    // Refactoritzat perque el codi sempre es el mateix, nomes canvia quin array es
    // l'entrada i quin la sortida
    private String procesaPoliAlfa(String msg, char[] origen, char[] desti) {
        StringBuilder text = new StringBuilder();

        // Recorrer la cadena
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i); // Guardo la lletra actual

            // Comprovar si la lletra es majúscula
            boolean esMaj = Character.isUpperCase(c);

            c = Character.toLowerCase(c); // En qualsevol cas la passo a minuscula per buscar

            int pos = trobarPosicio(origen, c); // Busca en quina posició esta la lletra, -1 si no la troba

            // Si ha trobat la posició
            if (pos != -1) {
                permutaAlfabet();

                if (esMaj) {
                    text.append(Character.toUpperCase(desti[pos])); // Si es majuscula afegirlo com a tal
                } else {
                    text.append(desti[pos]);
                }
            } else {
                text.append(c); // Afegir el caracter tal cual, pels espais i altres signes
            }
        }
        return text.toString();
    }

    // Inicialitza el Random
    private void initRandom(int password) {
        semilla = new Random(password); // Utilitzar la contrasenya com a semilla
    }

    // Funció per trobar la posició d'un caràcter a un array
    private int trobarPosicio(char[] array, char c) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) {
                return i;
            }
        }
        return -1; // Si no es troba
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        clauCorrecta(clau);
        initRandom(Integer.parseInt(clau));
        return new TextXifrat(xifraPoliAlfa(msg).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        clauCorrecta(clau);
        initRandom(Integer.parseInt(clau));
        return desxifraPoliAlfa(xifrat.toString());
    }

    private void clauCorrecta(String clau) throws ClauNoSuportada {
        try {
            long clauLong = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
    }

}
