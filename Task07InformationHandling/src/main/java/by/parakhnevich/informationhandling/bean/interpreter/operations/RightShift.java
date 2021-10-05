package by.parakhnevich.informationhandling.bean.interpreter.operations;

import by.parakhnevich.informationhandling.bean.interpreter.PartOfExpression;

public class RightShift implements PartOfExpression {
    PartOfExpression leftPartOfExpression;
    PartOfExpression rightPartOfExpression;

    public RightShift(PartOfExpression leftPartOfExpression, PartOfExpression rightPartOfExpression) {
        this.leftPartOfExpression = leftPartOfExpression;
        this.rightPartOfExpression = rightPartOfExpression;
    }

    public double interpret(PartOfExpression context) {
        return Double.longBitsToDouble(
                Double.doubleToLongBits(leftPartOfExpression.interpret(context))<<
                Double.doubleToLongBits(rightPartOfExpression.interpret(context)));
    }
}
