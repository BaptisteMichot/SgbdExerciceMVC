package exercicemvc.Utilities;

import java.util.ArrayList;

public class Utilities {

    
    //Vérifie si oui ou non un objet est une ArrayList de String
    public boolean isArrayListString(Object obj) {
          
        if(!(obj instanceof ArrayList<?>)){
            return false;
        }
        ArrayList<?> liste = (ArrayList<?>) obj;
        
        for (Object element : liste) {

            if(!(element instanceof String)){
                return false;
            }
        }
        return true;
    }
    
}
