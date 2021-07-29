package by.parakhnevich.exception;

import by.parakhnevich.entity.Triangle;

public class TriangleException extends Exception{
    private final double first;
    private final double second;
    private final double third;

    public TriangleException(String message,double first,double second,double third){
        super(message + '\n' + new Triangle(first, second, third));
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

    public double getThird(){
        return third;
    }
}
