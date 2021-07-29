package by.parakhnevich.exception;

import by.parakhnevich.entity.Circle;

public class CircleException extends Exception{
    private final double radius;

    public double getRadius(){return radius;}

    public CircleException(String message,double radius){
        super(message + '\0' + new Circle(radius));
        this.radius = radius;
    }
}
