package by.parakhnevich.entity;

public class Expression {
    private final double value;
    private static final String FUNCTION = "2 * x^4 - 3 * x^3 + 4 * x^2 - 5 * x + 6";

    public Expression(double value){
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "function=" + FUNCTION +
                ", value=" + value +
                '}';
    }
}
