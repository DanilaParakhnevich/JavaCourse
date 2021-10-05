package by.parakhnevich.informationhandling.bean.interpreter.operations;

import by.parakhnevich.informationhandling.bean.interpreter.PartOfExpression;

public class UnaryNot implements PartOfExpression {
    PartOfExpression partOfExpression;

    public UnaryNot(PartOfExpression partOfExpression) {
        this.partOfExpression = partOfExpression;
    }

    public double interpret(PartOfExpression context) {
        return Double.longBitsToDouble(
                ~Double.doubleToLongBits(partOfExpression.interpret(context)));
    }
}
