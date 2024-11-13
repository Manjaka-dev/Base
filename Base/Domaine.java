import java.util.ArrayList;

/**
 * Domaine
 */
public class Domaine {

    ArrayList<Object> doms;
    int typeDomaine; // vas de 1 a 4
    private ArrayList<String> tableType = new ArrayList<>();

    public Domaine(ArrayList<String> doms, int typeDomaine) {
        this.doms= new ArrayList<>();
        this.doms.addAll(doms);
        this.typeDomaine = typeDomaine;
        tableType.add("int");
        tableType.add("float");
        tableType.add("double");
        tableType.add("char");
        tableType.add("varchar");
        tableType.add("text");
        tableType.add("time");
        tableType.add("date");
        tableType.add("datetime");
    }

    public int getTypeDomaine() {
        return typeDomaine;
    }

    public void setTypeDomaine(int typeDomaine) {
        this.typeDomaine = typeDomaine;
    }

    public ArrayList<Object> getDom() {
        return doms;
    }

    public void setDom(ArrayList<Object> doms) {
        this.doms = doms;
    }

    public void addDom(String dom) {
        this.doms.add(dom);
    }

    boolean checkType(String newDom) {
        int tempType = 0;
        if (newDom.startsWith("[")) {
            tempType = 1;
        } else if (this.tableType.contains(newDom)) {
            tempType = 2;
        } else {
            tempType = 3;
        }
        if (tempType == this.typeDomaine) {
            return true;
        }
        return false;
    }

    boolean testRegex(String mots) {
        if (mots == null) {
            return false;
        }
        for (Object dom : this.doms) {

            if (mots.matches(General.convertToString(dom)) == true) {
                return true;
            }
        }
        return false;
    }

    boolean testByType(Object object, String type) {
        boolean test = false;
        switch (type) {
            case "int":
                if (object.getClass().getSimpleName().equals("Integer")) {
                    test = true;
                    break;
                }
                break;
            case "float":
                if (object.getClass().getSimpleName().equals("Float")) {
                    test = true;
                    break;
                }
                break;
            case "double":
                if (object.getClass().getSimpleName().equals("Double")) {
                    test = true;
                    break;
                    
                }
                break;
            case "char":
                if (object.getClass().getSimpleName().equals(type)) {
                    test = true;
                
                    break;
                }
                break;
            case "varchar":
                if (object.getClass().getSimpleName().equals("String")) {
                    test = true;
                    
                    break;
                }
                break;
            case "text":
                if (object.getClass().getSimpleName().equals("String")) {
                    test = true;
                    break;
                }
                break;
            case "time":
                if (object.getClass().getSimpleName().equals("Time")) {
                    test = true;
                    break;
                }
                break;
            case "date":
                if (object.getClass().getSimpleName().equals("Date")) {
                    test = true;
                    break;
                }
                break;
            case "datetime":
                if (object.getClass().getSimpleName().equals("Datetime")) {
                    test = true;
                    break;
                }
                break;
            default:
                test = false;
                break;
            }
            return test;
    }

    boolean testType2(Object object){
        for (Object dom : doms) {
            if (testByType(object, General.convertToString(dom)) == true) {
                return true;
            }
        }
        return false;
    }

    public boolean appartient(Object object) {
        boolean test = true;
        switch (this.typeDomaine) {
            case 1:
                test = testRegex(object.toString());
                break;

            case 2:
                test = testType2(object);
                break;

            default:
                test = false;
                break;
        }
        return test;
    }

    @Override
    public boolean equals(Object obj) {
        Domaine domaine= ((Domaine)obj);
        if (domaine.typeDomaine == this.typeDomaine && this.doms.equals(domaine.doms)) {
            return true;
        }
        return false;
    }

}