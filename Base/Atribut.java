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
}