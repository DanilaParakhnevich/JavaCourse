package by.parakhnevich.exception;

import by.parakhnevich.entity.RegularTriangle;

public class RegularTriangleException extends Exception{
    private final double side;

    public double getSide(){
        return this.side;
    }

    public RegularTriangleException(String message,double side){
        super(message + '\n' + new RegularTriangle(side));
        this.side = side;
    }
}
