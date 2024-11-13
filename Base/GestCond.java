import java.util.ArrayList;

public class GestCond {
    ArrayList<Condition> conditions;
    ArrayList<Integer> connecteur;

    public ArrayList<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(ArrayList<Condition> conditions) {
        this.conditions = conditions;
    }

    public ArrayList<Integer> getConnecteur() {
        return connecteur;
    }

    public void setConnecteur(ArrayList<Integer> connecteur) {
        this.connecteur = connecteur;
    }

    public GestCond(Condition condition) {
        conditions = new ArrayList<>();
        conditions.add(condition);
        connecteur = new ArrayList<>();
    }

    public void and(Condition condition) {
        this.conditions.add(condition);
        this.connecteur.add(1);
    }

    public void or(Condition condition) {
        this.conditions.add(condition);
        this.connecteur.add(2);
    }
}
