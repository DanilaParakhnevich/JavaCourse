package by.parakhnevich.validator;

import by.parakhnevich.entity.Circle;

public class CircleValidator {
    private final Circle circle;

    public CircleValidator(Circle circle){
        this.circle = circle;
    }

    public boolean isValid(){
        return circle.getRadius() > 0;
    }
}
