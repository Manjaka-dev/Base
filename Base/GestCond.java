import java.util.ArrayList;
import java.util.List;

public class GestCond {
    List<Condition> conditions;
    List<Integer> connecteur;

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public List<Integer> getConnecteur() {
        return connecteur;
    }

    public void setConnecteur(List<Integer> connecteur) {
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
