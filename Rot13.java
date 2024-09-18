public class Rot13 {

    public static final char[] lletresMin = {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 
        'n','ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };
    
    public static final char[] lletresMaj = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
        'N','Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };
    

    public static String xifraRot13(String cadena) {
        
        String cadenaXifrada = "";
        
        // Recorrer la cadena
        for (int i=0; i<cadena.length(); i++){
            // Si es lletra minuscula
            if (Character.isLetter(cadena.charAt(i)) && Character.isLowerCase(cadena.charAt(i))) {
                
                if (i + 13 > lletresMin.length) {
                    cadenaXifrada = cadena.substring(0, i-1) + lletresMin[(i + 13) - lletresMin.length];
                }
            }
            // Si es lletra majuscula
            if (Character.isLetter(cadena.charAt(i)) && Character.isUpperCase(cadena.charAt(i))) {

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





        return cadenaDesxifrada;
    }


    public static void main(String[] args) {
        String text = "HoLa MunDo?! yeAh";
        System.out.println(xifraRot13(text));
    }
}