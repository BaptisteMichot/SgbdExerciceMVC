package exercicemvc.Utilities;

import java.util.ArrayList;

public class Utilities {

    /**
     * Convertit une liste en ArrayList<String> si c'est bien une chaine de caractères
     *
     * @param list la liste à convertir
     * @return ArrayList<String> si la conversion est possible, sinon null
     */
    public ArrayList<String> convertToStringList(ArrayList<?> list) {
        try {
            ArrayList<String> stringList = new ArrayList<>();
            for (Object obj : list) {
                if (obj instanceof String) {
                    stringList.add((String) obj);
                } else {
                    return null;
                }
            }
            return stringList;
        } catch (ClassCastException e) {
            return null;
        }
    }
    
}
