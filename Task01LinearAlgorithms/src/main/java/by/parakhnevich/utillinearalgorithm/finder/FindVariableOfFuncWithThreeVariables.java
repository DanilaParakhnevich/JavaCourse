package by.parakhnevich.utillinearalgorithm.finder;

import by.parakhnevich.entity.FunctionWithThreeVariables;

public class FindVariableOfFuncWithThreeVariables {
    private final FunctionWithThreeVariables function;
    public FindVariableOfFuncWithThreeVariables(FunctionWithThreeVariables function) {
        this.function = function;
    }

    public double countResult() {
        return ((function.getFirst() - 3) * function.getSecond() / 2)+function.getThird();
    }
}
