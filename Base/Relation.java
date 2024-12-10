import java.util.ArrayList;
import java.util.List;
/**
 * Relation
 */
public class Relation {

    List<Atribut> atributs;
    List<Nuplet> nuplets;
    String nom;

    public Relation(String nom, List<Atribut> atributs) {
        this.nom = nom;
        this.atributs = new ArrayList<>(atributs);
        nuplets = new ArrayList<Nuplet>();
    }

    public void addValues(List<Object> values) throws Exception {
        Nuplet newNuplet = new Nuplet(atributs);
        int numAt = 0;
        for (Object object : values) {
            newNuplet.addValues(object, numAt);
            numAt++;
        }
        if (!Nuplet.nupletContains(nuplets, newNuplet)) {
            nuplets.add(newNuplet);
        }
    }

    public void showRelation() {
        System.out.println("//////////////////");
        System.out.println(this.nom);
        for (Atribut atribut : this.atributs) {
            atribut.showAtribut();
        }
        for (Nuplet nuplet : this.nuplets) {
            nuplet.showNuplet();
        }
        System.out.println("\n\n");
    }

    List<Atribut> getAtributByName(List<String> noms) {
        noms = ((List<String>) General.removeDouble(noms, String.class));
        List<Atribut> atribSelect = new ArrayList<>();
        for (String nom : noms) {
            for (Atribut atribut : this.atributs) {
                if (atribut.nom == nom) {
                    atribSelect.add(atribut);
                }
            }
        }
        return atribSelect;
    }

    Atribut getAtributByName(String nom) {
        Atribut atribSelect = null;
        for (Atribut atribut : this.atributs) {
            if (atribut.nom == nom) {
                atribSelect = atribut;
            }
        }
        return atribSelect;
    }

    List<Integer> getIndexAtt(List<Atribut> selectatributs) {
        List<Integer> index = new ArrayList<>();
        for (Atribut sAtribut : selectatributs) {
            index.add(General.indexOfGeneric(this.atributs, sAtribut));
        }
        return index;
    }

    Integer getIndexAtt(Atribut selectatributs) {
        Integer index = General.indexOfGeneric(this.atributs, selectatributs);
        return index;
    }

    public Relation projection(List<String> noms) throws Exception {
        List<Atribut> selectAtrib = getAtributByName(noms);
        List<Integer> indexAtt = getIndexAtt(selectAtrib);
        List<Atribut> newAtributs = new ArrayList<>();
        for (Integer integer : indexAtt) {
            newAtributs.add(this.atributs.get(integer.intValue()));
        }
        String temp = "";
        for (String nom : noms) {
            temp += nom + " ";
        }
        Relation relation = new Relation(temp, newAtributs);

        for (Integer integer : indexAtt) {
            List<Object> val = new ArrayList<>();
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

    public Relation selectOneCond(Condition condition) throws Exception {
        Relation relation = new Relation(General.getOperation(condition.getOperation()), this.atributs);
        Atribut atribut = getAtributByName(condition.getNomAtt());
        Integer index = getIndexAtt(atribut);

        for (Nuplet nuplet : this.nuplets) {
            List<Object> values = new ArrayList<>();
            if (General.operate(nuplet.values.get(index.intValue()), condition.getOperation(), condition.getVal())) {
                for (Object value : nuplet.values) {
                    values.add(value);
                }
                relation.addValues(values);
            }
        }
        return relation;
    }

    public int getLigne(Object object) {
        int ligne = 0;
        for (Nuplet nuplet : nuplets) {
            ligne = nuplet.getNumLigne(object);
        }
        return ligne;
    }

    public void removeLigne(Object object) {
        int nbLigne = getLigne(object);
        this.nuplets.remove(nbLigne);
    }

    static boolean checkAtributs(Relation relation, Relation relation2) throws Exception {
        if (relation.atributs.size() != relation2.atributs.size()) {
            throw new Exception("le nombre d' atribut est different");
        }
        int count = 0;
        for (int i = 0; i < relation.atributs.size(); i++) {
            if (relation.atributs.get(i).equals(relation2.atributs.get(i))) {
                count++;
            }
        }
        if (count == relation.atributs.size()) {
            return true;
        }
        return false;
    }

    public static Relation union(Relation relation, Relation relation2) throws Exception {
        if (relation == null) {
            return relation2;
        }
        if (relation2 == null) {
            return relation;
        }
        if (relation.atributs.size() != relation2.atributs.size()) {
            throw new Exception("les relation doivent avoir le meme nombre d'atribut");
        }
        String nom = relation.nom + " union " + relation2.nom;
        List<Atribut> atributs = Atribut.unionAtribus(relation.atributs, relation2.atributs);
        Relation union = new Relation(nom, atributs);
        for (Nuplet nuplet : relation.nuplets) {
            union.addValues(nuplet.getValues());
        }
        for (Nuplet nuplet : relation2.nuplets) {
            union.addValues(nuplet.getValues());
        }
        return union;
    }

    public static Relation intersection(Relation relation, Relation relation2) throws Exception {
        if (relation == null) {
            return relation2;
        }
        if (relation2 == null) {
            return relation;
        }
        if (relation.atributs.size() != relation2.atributs.size()) {
            throw new Exception("le nombre d'atribut doit etre egal");
        }
        String nom = "intersection entre " + relation.nom + " " + relation2.nom;
        List<Atribut> atributs = Atribut.unionAtribus(relation.atributs, relation2.atributs);
        Relation intersection = new Relation(nom, atributs);
        for (Nuplet nuplet : relation.nuplets) {
            if (General.customContains(relation2.nuplets, nuplet)) {
                intersection.addValues(nuplet.values);
            }
        }
        return intersection;
    }

    public static Relation priveeDe(Relation relation, Relation relation2) throws Exception {
        if (relation == null) {
            return relation2;
        }
        if (relation2 == null) {
            return relation;
        }
        if (relation.atributs.size() != relation2.atributs.size()) {
            throw new Exception("le nombre d'atribut doit etre egal");
        }
        String nom = "intersection entre " + relation.nom + " " + relation2.nom;
        List<Atribut> atributs = Atribut.unionAtribus(relation.atributs, relation2.atributs);
        Relation intersection = new Relation(nom, atributs);
        for (Nuplet nuplet : relation.nuplets) {
            if (!General.customContains(relation2.nuplets, nuplet)) {
                intersection.addValues(nuplet.values);
            }
        }
        return intersection;
    }

    public static Relation produitCartesien(Relation relation, Relation relation2) throws Exception {
        List<Atribut> atributs = new ArrayList<>();
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
            List<Object> temp = new ArrayList<>();
            if (relation.nuplets.get(i) != null) {
                temp.addAll(relation.nuplets.get(i).values);
            } else {
                for (int j = 0; j < relation.nuplets.get(0).atributs.size(); j++) {
                    temp.add(null);
                }
            }
            for (int j = 0; j < relation2.nuplets.size(); j++) {
                List<Object> temp2 = new ArrayList<>(temp);
                if (relation2.nuplets.get(j) != null) {
                    temp2.addAll(relation2.nuplets.get(j).values);
                } else {
                    for (int k = 0; k < relation2.nuplets.get(j).atributs.size(); k++) {
                        temp2.add(null);
                    }
                }
                prod.addValues(temp2);
            }

        }
        return prod;
    }

    public static Relation selection(GestCond cond, Relation relation) throws Exception {
        Relation select = null;
        for (int i = 0; i < cond.conditions.size(); i++) {
            Relation temp = relation.selectOneCond(cond.conditions.get(i));
            if (i < cond.conditions.size() - 1) {
                switch (cond.connecteur.get(i)) {
                    case 1:
                        select = intersection(select, temp);
                        break;
                    case 2:
                        select = union(select, temp);
                        break;
                    default:
                        break;
                }
            }
        }
        return select;
    }

    // public Relation NaturalJoin(Relation relation, List<String> atributsJoin) throws Exception {
    //     Relation relation2 = produitCartesien(this, relation);
        
    // }

    public Relation thetaJointure(Condition condition, Relation relation) throws Exception {
        Relation relation2 = produitCartesien(this, relation);
        String nom = "jointure de " + this.nom + " et " + relation.nom + " a " + condition.toString();
        Relation jointure = new Relation(nom, relation2.atributs);

        Atribut atribut = relation2.getAtributByName(condition.getNomAtt());
        Integer index = relation2.getIndexAtt(atribut);

        Atribut atribut2 = relation2.getAtributByName(condition.getVal().toString());
        Integer index2 = relation2.getIndexAtt(atribut2);
        System.out.println(index2);

        for (Nuplet nuplet : relation2.nuplets) {
            if (General.operate(nuplet.values.get(index), condition.operation, nuplet.values.get(index2))) {
                jointure.addValues(nuplet.values);
            }
        }

        return jointure;
    }

}