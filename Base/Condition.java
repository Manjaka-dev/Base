/**
 * Condition
 */
public class Condition {

    String nomAtt;
    int operation;
    Object val;

    public Condition(String nomAtt, int operation, Object val) {
        this.nomAtt = nomAtt;
        this.operation = operation;
        this.val = val;
    }

    public String getNomAtt() {
        return nomAtt;
    }

    public int getOperation() {
        return operation;
    }

    public Object getVal() {
        return val;
    }

    @Override
    public String toString() {
        try {
            return this.nomAtt + " " + General.getOperation(operation) + " " + val.toString();
        } catch (Exception e) {
            return "operation innexistante";
        }
    }

}