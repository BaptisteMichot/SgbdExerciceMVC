package exercicemvc.Utilities;

import java.util.ArrayList;

public class Utilities {

    /**
     * Convertit une liste en ArrayList<String> si c'est bien une chaine de caractères
     *
     * @param liste la liste à convertir
     * @return ArrayList<String> si la conversion est possible, sinon null
     */
    public ArrayList<String> convertToStringList(ArrayList<?> liste) {
        try {
            ArrayList<String> listeString = new ArrayList<>();
            for (Object obj : liste) {
                if (obj instanceof String) {
                    listeString.add((String) obj);
                } else {
                    return null;
                }
            }
            return listeString;
        } catch (ClassCastException e) {
            return null;
        }
    }
    
}
