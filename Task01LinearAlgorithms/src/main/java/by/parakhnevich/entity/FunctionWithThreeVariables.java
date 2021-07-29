package by.parakhnevich.entity;

public class FunctionWithThreeVariables {
    private final double first;
    private final double second;
    private final double third;
    private static final String FUNCTION = "( ( a - 3 ) * b / 2 ) + c";

    public FunctionWithThreeVariables(double first, double second, double third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public double getFirst() {
        return first;
    }

    public double getSecond() {
        return second;
    }

    public double getThird() {
        return third;
    }

    @Override
    public String toString() {
        return "FunctionWithThreeVariables{" +
                " function=" + FUNCTION +
                ", first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }
}
