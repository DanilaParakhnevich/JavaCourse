package by.parakhnevich.informationhandling.bean.interpreter;

public class Number implements PartOfExpression{
    double numeric;

    public Number(double number) {
        this.numeric = number;
    }

    public double interpret(PartOfExpression context) {
        return numeric;
    }
}
