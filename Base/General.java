import java.util.ArrayList;
import java.util.List;

/**
 * General
 */
public class General {

    public static boolean customContains(ArrayList<Object> list, Object target) {
        for (Object item : list) {
            if (item.equals(target)) { // Utilise equals pour comparer chaque objet
                return true;
            }
        }
        return false;
    }

    public static <T, E> ArrayList<T> convertArrayList(List<E> list, Class<T> clazz) {
        ArrayList<T> newList = new ArrayList<>();
        
        for (E element : list) {
            if (clazz.isInstance(element)) { // Vérifie si l'élément est du type désiré
                newList.add(clazz.cast(element)); // Cast dynamique et ajout à la nouvelle liste
            }
        }
        
        return newList;
    }

    public static <T,E> ArrayList<T> removeDouble(ArrayList<E> list, Class<T> class1){
        ArrayList<T> objects= new ArrayList<>();
        // objects.add(class1.cast(list.getFirst()));
        for (Object li : list) {
            if (!objects.contains(class1.cast(li))) {
                objects.add(class1.cast(li));
            }
        }
        return objects;
    }
    
    static String getOperation(int num) throws Exception{
        String retour ="";
        switch (num) {
            case 1:
                retour = "=";
                break;
            case 2:
                retour = "!=";
                break;
            case 3:
                retour = ">";
                break;
            case 4:
                retour = "<";
                break;
            case 5:
                retour = ">=";
                break;
            case 6:
                retour = "<=";
                break;
            
            default:
                retour = "unreachable";
                break;
        }
        if (retour.equals("unreachable")) {
            throw new Exception("Signe non existant");
        } else {
            return retour;
        }
    }

    public static int getIntValue(Object obj) throws Exception {
        if (obj instanceof Integer) {
            return (Integer) obj;
        } else if (obj instanceof Byte) {
            return ((Byte) obj).intValue();
        } else if (obj instanceof Short) {
            return ((Short) obj).intValue();
        } else if (obj instanceof Long) {
            return ((Long) obj).intValue();
        } else if (obj instanceof Float) {
            return ((Float) obj).intValue();
        } else if (obj instanceof Double) {
            return ((Double) obj).intValue();
        } else if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj); // Conversion String -> int
            } catch (NumberFormatException e) {
                throw new Exception("Object non numérisable");
            }
        } else {
            throw new Exception("Object non numérisable");
        }
    }
    

    public static boolean operate(Object val1, int operation, Object val2){
        boolean status = false;
        switch (operation) {
            case 1:
                if(val1.equals(val2)){
                    status = true;
                }
                break;
            case 2:
                if(!val1.equals(val2)) status =true;
                break;
            case 3:
                try {
                    if(getIntValue(val2) > getIntValue(val2)) status =true;
                } catch (Exception e) {
                    status = false;
                }
                break;
            case 4:
                try {
                    if(getIntValue(val2) < getIntValue(val2)) status =true;
                } catch (Exception e) {
                    status = false;
                }
                break;
            case 5:
                try {
                    if(getIntValue(val2) >= getIntValue(val2)) status =true;
                } catch (Exception e) {
                    status = false;
                }
                break;
            case 6:
                try {
                    if(getIntValue(val2) <= getIntValue(val2)) status =true;
                } catch (Exception e) {
                    status = false;
                }
                break;
        
            default:
                status =false;
                break;
        }
        return status;
    }

    public static <T, E> ArrayList<T> intersectArray(ArrayList<E> objects, ArrayList<E> objects2, Class<T> class1){
        ArrayList<T> inter = new ArrayList<>();
        for (Object object : objects) {
            if (objects2.contains(object)) {
                inter.add(class1.cast(object));
            }
        }
        for (Object object : objects2) {
            if (objects.contains(object)) {
                inter.add(class1.cast(object));
            }
        }
        inter = removeDouble(inter, class1);
        System.out.println(inter.size());
        return inter;
    }

    public static <T> int indexOfGeneric(ArrayList<T> list, T element) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(element)) { // Vérifie si l'élément est égal
                return i;
            }
        }
        return -1; // Retourne -1 si l'élément n'est pas trouvé
    }

    public static String convertToString(Object obj) {
        if (obj == null) {
            return "null";  // Si l'objet est null, renvoie "null"
        }
        return String.valueOf(obj);  // Utilise String.valueOf pour convertir l'objet
    }
    
}