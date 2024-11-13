import java.util.ArrayList;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {

        ArrayList<String> doms = new ArrayList<>();
        doms.add("varchar");
        Domaine domaine = new Domaine(doms, 2);
        Atribut atribut = new Atribut("testATT", domaine);
        Atribut atribut2 = new Atribut("test2", domaine);
        Atribut atribut3 = new Atribut("test3", domaine);
        ArrayList<Atribut> atributs = new ArrayList<>();
        atributs.add(atribut);
        atributs.add(atribut2);
        atributs.add(atribut3);
        Relation relation = new Relation("test", atributs);
        ArrayList<Object> value= new ArrayList<>();
        value.add("manjaka1");
        value.add("manja1");
        value.add("man1");
        ArrayList<Object> value2= new ArrayList<>();
        value2.add("manjaka2");
        value2.add("manja2");
        value2.add("man2");
        try {
            relation.addValues(value);
            relation.addValues(value2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> select = new ArrayList<>();
        select.add("test2");
        select.add("test3");
        System.out.println(select.contains("test2"));

        try {
            Relation relation2= relation.selectOneCond(new Condition("test2", 1, "manja1"));
            Relation relation3 = Relation.intersection(relation, relation2);
            // relation2.showRelation();
            relation3.showRelation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}