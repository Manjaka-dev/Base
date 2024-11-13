import java.util.ArrayList;

/**
 * Relation
 */
public class Relation {

    ArrayList<Atribut> atributs;
    ArrayList<Nuplet> nuplets;
    String nom;

    public Relation(String nom, ArrayList<Atribut> atributs) {
        this.nom = nom;
        this.atributs = new ArrayList<>(atributs);
        nuplets = new ArrayList<Nuplet>();
    }

    public void addValues(ArrayList<Object> values) throws Exception{
        Nuplet newNuplet = new Nuplet(atributs);
        int numAt=0;
        for (Object object : values) {
            newNuplet.addValues(object, numAt);
            numAt++;
        }
        if (!Nuplet.nupletContains(nuplets, newNuplet)) {
            nuplets.add(newNuplet);
        }
    }

    public void showRelation(){
        for (Atribut atribut : this.atributs) {
            atribut.showAtribut();
        }
        for (Nuplet nuplet : this.nuplets) {
            nuplet.showNuplet();
        }
    }

    ArrayList<Atribut> getAtributByName(ArrayList<String> noms){
        noms = ((ArrayList<String>)General.removeDouble(noms , String.class));
        ArrayList<Atribut> atribSelect= new ArrayList<>();
        for (String nom : noms) {
            for (Atribut atribut : this.atributs) {
                if (atribut.nom == nom) {
                    atribSelect.add(atribut);
                }
            }
        }
        return atribSelect;
    }

    Atribut getAtributByName(String nom){
        Atribut atribSelect= null;
        for (Atribut atribut : this.atributs) {
            if (atribut.nom == nom) {
                atribSelect = atribut;
            }
        }
        return atribSelect;
    }

    ArrayList<Integer> getIndexAtt(ArrayList<Atribut> selectatributs){
        ArrayList<Integer> index = new ArrayList<>();
        for (Atribut sAtribut : selectatributs) {
            index.add(General.indexOfGeneric(this.atributs, sAtribut));
        }
        return index;
    }

    Integer getIndexAtt(Atribut selectatributs){
        Integer index = General.indexOfGeneric(this.atributs, selectatributs);
        return index;
    }

    public Relation select (ArrayList<String> noms) throws Exception{
        ArrayList<Atribut> selectAtrib = getAtributByName(noms);
        ArrayList<Integer> indexAtt = getIndexAtt(selectAtrib);
        ArrayList<Atribut> newAtributs= new ArrayList<>();
        for (Integer integer : indexAtt) {
            newAtributs.add(this.atributs.get(integer.intValue()));
        }
        String temp="";
        for (String nom : noms) {
            temp+=nom+" ";
        }
        Relation relation = new Relation(temp, newAtributs);
        
        for (Integer integer : indexAtt) {
            ArrayList<Object> val= new ArrayList<>();
            for (Nuplet nuplet : this.nuplets) {
                val.add(nuplet.getLigne(integer.intValue()));
            }
            relation.addValues(val);
        }
        return relation;
        
    }

    /*
     * 1 =
     * 2 !=
     * 3 >
     * 4 <
     * 5 >=
     * 6 <=
     */

    public Relation selectOneCond(Condition condition) throws Exception{
        Relation relation = new Relation(General.getOperation(condition.getOperation()), this.atributs);
        Atribut atribut= getAtributByName(condition.getNomAtt());
        Integer index = getIndexAtt(atribut);
        
        for (Nuplet nuplet : this.nuplets) {
            ArrayList<Object> values= new ArrayList<>();
            if (General.operate(nuplet.values.get(index.intValue()), condition.getOperation(), condition.getVal())) {
                for (Object value : nuplet.values) {
                    values.add(value);
                }
                relation.addValues(values);
            }
        }
        return relation;
    }

    public int getLigne(Object object){
        int ligne=0;
        for (Nuplet nuplet : nuplets) {
            ligne = nuplet.getNumLigne(object);
        }
        return ligne;
    }

    public void removeLigne(Object object){
        int nbLigne = getLigne(object);
        this.nuplets.remove(nbLigne);
    }

    static boolean checkAtributs(Relation relation, Relation relation2) throws Exception{
        if (relation.atributs.size() != relation2.atributs.size()) {
            throw new Exception("le nombre d' atribut est different");
        }
        int count = 0;
        for (int i = 0; i < relation.atributs.size(); i++) {
            if (relation.atributs.get(i).equals(relation2.atributs.get(i))) {
                count ++;
            }
        }
        if (count == relation.atributs.size()) {
            return true;
        }
        return false;
    }

    public static Relation union(Relation relation, Relation relation2) throws Exception{
        if (relation == null) {
            return relation2;
        }
        if (relation2 == null) {
            return relation;
        }
        Relation union =  new Relation(relation.nom+relation2.nom, relation.atributs);
        if (checkAtributs(relation, relation2)) {
            for (Nuplet nuplet : relation.nuplets) {
                ArrayList<Object> temp = new ArrayList<>();
                for (Object value : nuplet.values) {
                    temp.add(value);
                }
                relation.addValues(temp);
            }
            for (Nuplet nuplet : relation2.nuplets) {
                ArrayList<Object> temp = new ArrayList<>();
                for (Object value : nuplet.values) {
                    temp.add(value);
                }
                relation.addValues(temp);
            }
        }
        return union;
    }

    public static Relation produitScalaire(Relation relation, Relation relation2) throws Exception{
        ArrayList<Atribut> atributs = new ArrayList<>();
        for (Atribut atribut : relation.atributs) {
            atributs.add(atribut);
        }
        for (Atribut atribut : relation2.atributs) {
            atributs.add(atribut);
        }
        Relation prod = new Relation("prodiuit", atributs);
        int max = relation.nuplets.size();
        if (relation2.nuplets.size() > max) {
            max = relation2.nuplets.size();
        }
        for (int i = 0; i < max; i++) {
            ArrayList<Object> temp = new ArrayList<>();
            if (relation.nuplets.get(i)!= null) {
                temp.addAll(relation.nuplets.get(i).values);
            } else {
                for (int j = 0; j < relation.nuplets.get(0).atributs.size(); j++) {
                    temp.add(null);
                }
            }
            if (relation2.nuplets.get(i)!= null) {
                temp.addAll(relation2.nuplets.get(i).values);
            } else {
                for (int j = 0; j < relation2.nuplets.get(0).atributs.size(); j++) {
                    temp.add(null);
                }
            }
            prod.addValues(temp);
        }
        return prod;
    }

    public static Relation intersection(Relation relation, Relation relation2) throws Exception{
        if (relation == null) {
            return relation2;
        }
        if (relation2 == null) {
            return relation;
        }
        ArrayList<Atribut> interAtribut = General.intersectArray(relation.atributs, relation.atributs, Atribut.class);
        Relation intersection= new Relation("", interAtribut);
        ArrayList<Integer> indexs = Atribut.getIndex(relation.atributs, interAtribut);
        for (Nuplet nuplet : relation.nuplets) {
            ArrayList<Object> temp = new ArrayList<>();
            for (Integer index : indexs) {
                temp.add(nuplet.values.get(index.intValue()));
            }
            intersection.addValues(temp);
        }
        for (Nuplet nuplet : relation2.nuplets) {
            ArrayList<Object> temp = new ArrayList<>();
            for (Integer index : indexs) {
                temp.add(nuplet.values.get(index.intValue()));
            }
            intersection.addValues(temp);
        }
        return intersection;
    }

    public Relation selectOr(ArrayList<Condition> conditions) throws Exception{
        Relation relation = null;
        for (Condition condition : conditions) {
            Relation temp = relation.selectOneCond(condition);
            relation = union(relation, temp);
        }
        return relation;
    }

    public Relation selectAnd(ArrayList<Condition> conditions) throws Exception{
        Relation relation = null;
        for (Condition condition : conditions) {
            Relation temp = relation.selectOneCond(condition);
            relation = intersection(relation, temp);
        }
        return relation;
    }

    public Relation thetaJointure(Condition condition, Relation relation) throws Exception{
        Relation relation2 = produitScalaire(this, relation);
        
    }

}