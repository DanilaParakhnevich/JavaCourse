package by.parakhnevich.et.service.calculator;

import by.parakhnevich.et.bean.Sphere;

import static java.lang.Math.pow;

public class SphereCalculator {
    Sphere sphere;

    public SphereCalculator(Sphere circle) {
        this.sphere = circle;
    }

    public double calculateVolume() {
        return 4.0 / 3.0 *
                Math.PI * pow(sphere.getRadius(), 3);
    }

    public double calculateArea() {
        return 4 * Math.PI * pow(sphere.getRadius(), 2);
    }
}
