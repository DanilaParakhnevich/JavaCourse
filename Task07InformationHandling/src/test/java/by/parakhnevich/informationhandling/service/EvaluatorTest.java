package by.parakhnevich.informationhandling.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EvaluatorTest {
    String expression1 = "5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1)";
    String expression2 = "2";
    String expression3 = "~6&9|(3&4)";
    Evaluator evaluator1 = new Evaluator(expression1);
    Evaluator evaluator2 = new Evaluator(expression2);
    Evaluator evaluator3 = new Evaluator(expression3);

    @Test
    void interpret() {
        assertEquals(evaluator1.interpret(null), 5);
        assertEquals(evaluator2.interpret(null),2);
        assertEquals(evaluator3.interpret(null),9);
    }
}