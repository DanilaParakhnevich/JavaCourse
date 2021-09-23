package by.parakhnevich.et.service.validator;

import by.parakhnevich.et.bean.Sphere;

public class SphereValidator {
    public boolean validate(Sphere circle) {
        return circle.getRadius() > 0;
    }
}
