package by.parakhnevich.utillinearalgorithm.finder;

import by.parakhnevich.entity.Expression;

public class FindExpression {
    private final Expression expression;

    public FindExpression(Expression expression) {
        this.expression = expression;
    }

    public double countResult() {
        double value = expression.getValue();
        return value * (value * (value * (2 * value - 3) + 4) - 5) + 6;
    }
}
