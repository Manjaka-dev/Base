import java.util.ArrayList;
import java.util.Objects;

/**
 * Atribut
 */
public class Atribut {

    String nom;
    Domaine domaine;

    public Atribut(String nom, Domaine domaine) {
        this.nom = nom;
        this.domaine = domaine;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Domaine getDomaine() {
        return domaine;
    }

    public void setDomaine(Domaine domaine) {
        this.domaine = domaine;
    }

    public boolean checDomaine(Object object) {
        return this.domaine.appartient(object);
    }

    public void showAtribut() {
        System.out.print(this.nom);
        System.out.print("\t\t");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Atribut atribut = (Atribut) obj;
        return Objects.equals(nom, atribut.nom) &&
                Objects.equals(domaine, atribut.domaine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, domaine);
    }

    public static ArrayList<Integer> getIndex(ArrayList<Atribut> atributs, ArrayList<Atribut> atributs2) {
        ArrayList<Integer> indexs = new ArrayList<>();
        for (Atribut atribut : atributs2) {
            indexs.add(General.indexOfGeneric(atributs, atribut));
        }
        return indexs;
    }

    static Atribut unionAtribut(Atribut atribut, Atribut atribut2) {
        String nom = atribut.nom + " " + atribut2.nom;
        Domaine domaine = Domaine.unionDomaine(atribut.domaine, atribut2.domaine);
        return new Atribut(nom, domaine);
    }

    public static ArrayList<Atribut> unionAtribus(ArrayList<Atribut> atributs, ArrayList<Atribut> atributs2)
            throws Exception {
        if (atributs.size() != atributs2.size()) {
            throw new Exception("le nombre d'atribut doit etre egal");
        }
        ArrayList<Atribut> union = new ArrayList<>();
        for (int i = 0; i < atributs.size(); i++) {
            union.add(Atribut.unionAtribut(atributs.get(i), atributs2.get(i)));
        }
        return union;
    }
}