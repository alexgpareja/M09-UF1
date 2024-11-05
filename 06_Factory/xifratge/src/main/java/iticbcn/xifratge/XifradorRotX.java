package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {

    public static final char[] lletresMin = "aàáäâãbcçdeèéëêfghiìíïîjklmnñoòóöôõpqrstuùúüûvwxyýÿz".toCharArray();

    public static final char[] lletresMaj = "AÀÁÄÂÃBCÇDEÈÉËÊFGHIÌÍÏÎJKLMNÑOÒÓÖÔÕPQRSTUÙÚÜÛVWXYÝZ".toCharArray();

    public int rot = 0;

    public String xifraRotX(String cadena, int rot) {

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

    public String desxifraRotX(String cadena, int rot) {
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

    public void forcaBrutaRotX(String cadenaXifrada) {
        for (int i = 1; i < lletresMin.length; i++) {
            rot = i;
            System.out.println("Desplaçament " + rot + ": " + desxifraRotX(cadenaXifrada, rot));
        }
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        clauCorrecta(clau);
        int clauValue = convertirClauAInt(clau);
        return new TextXifrat(xifraRotX(msg, clauValue).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        clauCorrecta(clau);
        int clauValue = convertirClauAInt(clau);
        return desxifraRotX(xifrat.toString(), clauValue);
    }

    private void clauCorrecta(String clau) throws ClauNoSuportada {
        try {
            int num = convertirClauAInt(clau);
            if (num < 0 || num > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    private int convertirClauAInt(String clau) throws NumberFormatException {
        return Integer.parseInt(clau);
    }

}
