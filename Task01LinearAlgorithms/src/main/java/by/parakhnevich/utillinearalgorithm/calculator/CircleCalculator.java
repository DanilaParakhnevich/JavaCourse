package by.parakhnevich.utillinearalgorithm.calculator;

import by.parakhnevich.entity.Circle;

public class CircleCalculator{
    Circle circle;
    public CircleCalculator(Circle circle) {
        this.circle = circle;
    }

    public double countCircuit(){
        return 2 * Math.PI * circle.getRadius();
    }

    public double countArea(){
        return Math.PI * circle.getRadius() * circle.getRadius();
    }

}
