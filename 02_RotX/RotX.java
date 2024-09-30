public class RotX {
    
    public static final char[] lletresMin = 
    "aàáäâãbcçdeèéëêfghiìíïîjklmnñoòóöôõpqrstuùúüûvwxyýÿz".toCharArray();

    public static final char[] lletresMaj = 
    "AÀÁÄÂÃBCÇDEÈÉËÊFGHIÌÍÏÎJKLMNÑOÒÓÖÔÕPQRSTUÙÚÜÛVWXYÝZ".toCharArray();

    public static int rot = 0;

    public static String xifraRotX(String cadena) {

        StringBuilder cadenaXifrada = new StringBuilder();

        // Recorrer la cadena
        for (int i = 0; i < cadena.length(); i++) {
            // Si es lletra minuscula
            if (Character.isLetter(cadena.charAt(i)) && Character.isLowerCase(cadena.charAt(i))) {

                // Comprobar en quina posició de l'array s'ubica
                for (int j = 0; j < lletresMin.length; j++) {
                    if (cadena.charAt(i) == lletresMin[j]) {
                        int index = (j + rot) % lletresMin.length;
                        cadenaXifrada.append(lletresMin[index]);
                        break;
                    }
                }
            }
            // Si es lletra majuscula
            if (Character.isLetter(cadena.charAt(i)) && Character.isUpperCase(cadena.charAt(i))) {

                // Comprobar en quina posició de l'array s'ubica
                for (int j = 0; j < lletresMaj.length; j++) {
                    if (cadena.charAt(i) == lletresMaj[j]) {
                        int index = (j + rot) % lletresMaj.length;
                        cadenaXifrada.append(lletresMaj[index]);
                        break;
                    }
                }

            }
            // Si es qualsevol altre cosa
            if (!Character.isLetter(cadena.charAt(i))) {
                cadenaXifrada.append(cadena.charAt(i));
            }

        }

        return cadenaXifrada.toString();
    }

    public static String desxifraRotX(String cadena) {
        StringBuilder cadenaDesxifrada = new StringBuilder();

        // Recorrer la cadena
        for (int i = 0; i < cadena.length(); i++) {
            // Si es lletra minuscula
            if (Character.isLetter(cadena.charAt(i)) && Character.isLowerCase(cadena.charAt(i))) {

                // Comprobar en quina posició de l'array s'ubica
                for (int j = 0; j < lletresMin.length; j++) {
                    if (cadena.charAt(i) == lletresMin[j]) {
                        int index = (j - rot + lletresMin.length) % lletresMin.length;
                        cadenaDesxifrada.append(lletresMin[index]);
                        break;
                    }
                }
            }
            // Si es lletra majuscula
            if (Character.isLetter(cadena.charAt(i)) && Character.isUpperCase(cadena.charAt(i))) {

                // Comprobar en quina posició de l'array s'ubica
                for (int j = 0; j < lletresMaj.length; j++) {
                    if (cadena.charAt(i) == lletresMaj[j]) {
                        int index = (j - rot + lletresMaj.length) % lletresMaj.length;
                        cadenaDesxifrada.append(lletresMaj[index]);
                        break;
                    }
                }

            }
            // Si es qualsevol altre cosa
            if (!Character.isLetter(cadena.charAt(i))) {
                cadenaDesxifrada.append(cadena.charAt(i));
            }

        }

        return cadenaDesxifrada.toString();
    }

    public static void forcaBrutaRotX(String cadenaXifrada) {
        for (int i = 1; i < lletresMin.length; i++) {
            rot = i;
            System.out.println("Desplaçament " + rot + ": " + desxifraRotX(cadenaXifrada));
        }
    }


    public static void main(String[] args) {
        String text = "HoLa MunDo?! yeAh";
        rot = 4;
        String textXifrat = xifraRotX(text);
        String textDesxifrat = desxifraRotX(textXifrat);

        System.out.println("Text xifrat: " + textXifrat);
        System.out.println("Text desxifrat: " + textDesxifrat);

        rot = 8;
        textXifrat = xifraRotX(text);
        textDesxifrat = desxifraRotX(textXifrat);

        System.out.println("Text xifrat: " + textXifrat);
        System.out.println("Text desxifrat: " + textDesxifrat);

        System.out.println("Desxifrar amb força bruta:");
        forcaBrutaRotX(textXifrat);
    }
}
