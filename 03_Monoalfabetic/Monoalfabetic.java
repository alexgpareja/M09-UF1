import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Monoalfabetic {
    
    public static final char[] abecedari = 
    "aàáäâãbcçdeèéëêfghiìíïîjklmnñoòóöôõpqrstuùúüûvwxyýÿz".toCharArray();

    public static char[] permutaAlfabet() {
         
        List<Character> llista = new ArrayList<>();

        for (char i: abecedari) {
            llista.add(i);
        }
        Collections.shuffle(llista);

        char[] permutat = new char[abecedari.length];

        for (int i = 0; i < llista.size(); i++) {
            permutat[i] = llista.get(i);
        }

        return permutat;
    }

    public String xifraMonoAlfa(String cadena) {
        StringBuilder cadenaXifrada = new StringBuilder();

        for (int i = 0; i < abecedari.length; i++) {
            char c = abecedari[i];
            boolean esMaj = false;

            if (Character.isUpperCase(c)) {
                esMaj = true;
                Character.toLowerCase(c);
            }

            // Obtenir la posició de la lletra dins de l'abecedari
            for (int j = 0; j < abecedari.length; j++) {
                c == 
            }
        }
    }

    public static void main(String[] args) {
        for (char ch : permutaAlfabet()) {
            System.out.print(ch);
        }
    }

}
