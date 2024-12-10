import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Nuplet
 */
public class Nuplet {

    List<Object> values;
    List<Atribut> atributs;

    public Nuplet(List<Atribut> atributs) {
        this.values = new ArrayList<>();
        this.atributs = new ArrayList<>(atributs);
    }

    public void setAtributs(List<Atribut> atributs) {
        this.atributs = atributs;
    }

    public List<Object> getValues() {
        return values;
    }

    public List<Atribut> getAtributs() {
        return atributs;
    }

    public boolean customEquals(Nuplet nuplet) {
        int count = 0;
        for (int i = 0; i < this.values.size(); i++) {
            if (this.values.get(i).equals(nuplet.values.get(i)))
                count++;
        }

        if (count == nuplet.values.size())
            return true;
        return false;
    }

    public void addValues(Object object, int numAt) throws Exception {
        if (this.atributs.get(numAt).checDomaine(object)) {
            this.values.add(object);
        } else {
            throw new Exception("La donne n'appartient pas au domaine");
        }
    }

    public static boolean nupletContains(List<Nuplet> nuplets, Nuplet nuplet) {
        if (nuplets.size() == 0)
            return false;
        for (Nuplet nuplet2 : nuplets) {
            if (nuplet2.customEquals(nuplet))
                return true;
        }
        return false;
    }

    public void showNuplet() {
        System.out.print("\n");
        for (Object value : values) {
            System.out.print(value + "\t\t");
        }
    }

    public void showNuplet(int ligne) {
        System.out.print(this.values.get(ligne));
    }

    public Object getLigne(int ligne) {
        return this.values.get(ligne);
    }

    public int getNumLigne(Object object) {
        int index = General.indexOfGeneric(this.values, object);
        return index;
    }

    @Override
    public boolean equals(Object obj) {
        // Vérifie si les objets sont égaux (référence ou type incorrect)
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // Cast de l'objet à Nuplet
        Nuplet other = (Nuplet) obj;

        // Vérification de l'égalité des listes values
        if (values == null) {
            if (other.values != null) {
                return false;
            }
        } else if (!values.equals(other.values)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(values, atributs);
    }
}