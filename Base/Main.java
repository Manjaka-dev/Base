import java.util.ArrayList;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {

        /* creation des ensemble dom */
        ArrayList<Object> ens1 = new ArrayList<>();
        ens1.add("int");
        ens1.add("double");

        ArrayList<Object> ens2 = new ArrayList<>();
        ens2.add("manjaka");
        ens2.add("mir");
        ens2.add("sandratra");
        ens2.add("miaritsoa");
        ens2.add("nyando");

        ArrayList<Object> ens3 = new ArrayList<>();
        ens3.add("test");
        ens3.add(1);
        ens3.add(true);

        /* creation des domaine */
        Domaine dom1 = new Domaine(ens1, 2);
        Domaine dom2 = new Domaine(ens2, 3);
        Domaine dom3 = new Domaine(ens3, 3);

        /* creation des atributs */
        ArrayList<Atribut> att1 = new ArrayList<>();
        att1.add(new Atribut("id1", dom1));
        att1.add(new Atribut("nom", dom2));

        ArrayList<Atribut> att2 = new ArrayList<>();
        att2.add(new Atribut("id2", dom1));
        att2.add(new Atribut("truc", dom3));

        /* creation des Relation */
        Relation relation1 = new Relation("eleve", att1);
        Relation relation2 = new Relation("machin", att2);

        /* creation List data */
        ArrayList<Object> data1 = new ArrayList<>();
        data1.add(1);
        data1.add("manjaka");
        ArrayList<Object> data2 = new ArrayList<>();
        data2.add(2);
        data2.add("mir");
        ArrayList<Object> data3 = new ArrayList<>();
        data3.add(3);
        data3.add("miaritsoa");
        ArrayList<Object> data4 = new ArrayList<>();
        data4.add(4);
        data4.add("sandratra");

        ArrayList<Object> data5 = new ArrayList<>();
        data5.add(1);
        data5.add("test");
        ArrayList<Object> data6 = new ArrayList<>();
        data6.add(2);
        data6.add(1);
        ArrayList<Object> data7 = new ArrayList<>();
        data7.add(3);
        data7.add(true);

        /* ajout des donnes */
        try {
            relation1.addValues(data1);
            relation1.addValues(data2);
            relation1.addValues(data3);
            relation1.addValues(data4);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            relation2.addValues(data5);
            relation2.addValues(data6);
            relation2.addValues(data7);
        } catch (Exception e) {
            e.printStackTrace();
        }

        relation1.showRelation();

        relation2.showRelation();

        try {
            Relation union = Relation.union(relation1, relation2);
            union.showRelation();

            Relation produit = Relation.produitCartesien(relation1, relation2);
            produit.showRelation();

            Relation jointure = relation1.thetaJointure(new Condition("id1", 1, "id2"), relation2);
            jointure.showRelation();

            Relation intersection = Relation.intersection(relation1, relation2);
            intersection.showRelation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}