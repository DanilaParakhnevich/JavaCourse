package by.parakhnevich.et.service.validator;

import by.parakhnevich.et.bean.Sphere;

public class SphereIntersectsWithPlanesValidator {
    public boolean validate(Sphere circle) {
        return Math.abs(circle.getPoint().getX()) <= circle.getRadius() &&
                Math.abs(circle.getPoint().getY()) <= circle.getRadius() &&
                Math.abs(circle.getPoint().getZ()) <= circle.getRadius();
    }
}
