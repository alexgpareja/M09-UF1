public class Rot13 {

    public static final char[] lletresMin = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    public static final char[] lletresMaj = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    public static String xifraRot13(String cadena) {

        String cadenaXifrada = "";

        // Recorrer la cadena
        for (int i = 0; i < cadena.length(); i++) {
            // Si es lletra minuscula
            if (Character.isLetter(cadena.charAt(i)) && Character.isLowerCase(cadena.charAt(i))) {

                // Comprobar en quina posició de l'array s'ubica
                for (int j = 0; j < lletresMin.length; j++) {
                    if (cadena.charAt(i) == lletresMin[j]) {
                        int index = (j + 13) % lletresMin.length;
                        cadenaXifrada += lletresMin[index];
                        break;
                    }
                }
            }
            // Si es lletra majuscula
            if (Character.isLetter(cadena.charAt(i)) && Character.isUpperCase(cadena.charAt(i))) {

                // Comprobar en quina posició de l'array s'ubica
                for (int j = 0; j < lletresMaj.length; j++) {
                    if (cadena.charAt(i) == lletresMaj[j]) {
                        int index = (j + 13) % lletresMaj.length;
                        cadenaXifrada += lletresMaj[index];
                        break;
                    }
                }

            }
            // Si es qualsevol altre cosa
            if (!Character.isLetter(cadena.charAt(i))) {
                cadenaXifrada += "" + cadena.charAt(i);
            }

        }

        return cadenaXifrada;
    }

    public static String desxifraRot13(String cadena) {
        String cadenaDesxifrada = "";

        // Recorrer la cadena
        for (int i = 0; i < cadena.length(); i++) {
            // Si es lletra minuscula
            if (Character.isLetter(cadena.charAt(i)) && Character.isLowerCase(cadena.charAt(i))) {

                // Comprobar en quina posició de l'array s'ubica
                for (int j = 0; j < lletresMin.length; j++) {
                    if (cadena.charAt(i) == lletresMin[j]) {
                        int index = (j - 13 + lletresMin.length) % lletresMin.length;
                        cadenaDesxifrada += lletresMin[index];
                        break;
                    }
                }
            }
            // Si es lletra majuscula
            if (Character.isLetter(cadena.charAt(i)) && Character.isUpperCase(cadena.charAt(i))) {

                // Comprobar en quina posició de l'array s'ubica
                for (int j = 0; j < lletresMaj.length; j++) {
                    if (cadena.charAt(i) == lletresMaj[j]) {
                        int index = (j - 13 + lletresMaj.length) % lletresMaj.length;
                        cadenaDesxifrada += lletresMaj[index];
                        break;
                    }
                }

            }
            // Si es qualsevol altre cosa
            if (!Character.isLetter(cadena.charAt(i))) {
                cadenaDesxifrada += "" + cadena.charAt(i);
            }

        }

        return cadenaDesxifrada;
    }

    public static void main(String[] args) {
        String text = "HoLa MunDo?! yeAh";
        String textXifrat = xifraRot13(text);
        String textDesxifrat = desxifraRot13(textXifrat);

        System.out.println("Text xifrat: " + textXifrat);
        System.out.println("Text desxifrat: " + textDesxifrat);
    }
}