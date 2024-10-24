package iticbcn.xifratge;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class XifradorMonoalfabetic implements Xifrador {

    public static final char[] abecedari = "aàáäâãbcçdeèéëêfghiìíïîjklmnñoòóöôõpqrstuùúüûvwxyýÿz".toCharArray();

    public final char[] permutat;

    // Constructor que inicialitza la permutació
    public XifradorMonoalfabetic() {
        this.permutat = permutaAlfabet(abecedari); // Inicialitza la permutació en el constructor
    }

    public char[] permutaAlfabet(char[] abecedari) {

        List<Character> llista = new ArrayList<>();

        for (char i : abecedari) {
            llista.add(i);
        }
        Collections.shuffle(llista); // Barreja els elements de la llista

        char[] permutat = new char[abecedari.length];

        for (int i = 0; i < llista.size(); i++) {
            permutat[i] = llista.get(i);
        }

        return permutat;
    }

    public String xifraMonoAlfa(String cadena) {
        StringBuilder cadenaXifrada = new StringBuilder();

        // Recorrer la cadena a xifrar
        for (int i = 0; i < cadena.length(); i++) {

            char c = cadena.charAt(i);

            if (Character.isLowerCase(c)) {
                int posicio = trobarPosicio(abecedari, c);

                // Si ha trobat la posició
                if (posicio != 1) {
                    cadenaXifrada.append(permutat[posicio]); // Guardo la lletra que hi ha en la posicio trobada
                } else {
                    cadenaXifrada.append(c); // Guardo tot allo que no sigui una lletra
                }
            } else if (Character.isUpperCase(c)) {
                char cmin = Character.toLowerCase(c); // El converteixo a minuscula per buscar

                int posicio = trobarPosicio(abecedari, cmin);

                // Si ha trobat la posició
                if (posicio != 1) {
                    cadenaXifrada.append(Character.toUpperCase(permutat[posicio])); // Guardo la lletra que hi ha en la
                                                                                    // posicio trobada COM A MAJUSCULA
                } else {
                    cadenaXifrada.append(c); // Guardo tot allo que no sigui una lletra
                }
            } else {
                cadenaXifrada.append(c); // Espais i altres caracters
            }
        }
        return cadenaXifrada.toString();
    }

    public String desxifraMonoAlfa(String cadena) {
        StringBuilder cadenaDesxifrada = new StringBuilder();

        for (int i = 0; i < cadena.length(); i++) {

            char c = cadena.charAt(i);

            if (Character.isLowerCase(c)) {

                int pos = trobarPosicio(permutat, c);

                if (pos != -1) {
                    cadenaDesxifrada.append(abecedari[pos]);
                } else {
                    cadenaDesxifrada.append(c);
                }
            }
            // Si és majúscula, buscar minúscula equivalent per desxifrar
            else if (Character.isUpperCase(c)) {

                char minusc = Character.toLowerCase(c);

                int pos = trobarPosicio(permutat, minusc);

                if (pos != -1) {
                    cadenaDesxifrada.append(Character.toUpperCase(abecedari[pos]));
                } else {
                    cadenaDesxifrada.append(c);
                }
            } else {
                cadenaDesxifrada.append(c);
            }
        }

        return cadenaDesxifrada.toString();
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

    private void mostraAlfabet(char[] abc) {
        for (char c : abc) {
            System.out.print(c);
        }
        System.out.println(); // Salt de linea al acabar
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'xifra'");
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'desxifra'");
    }

}
