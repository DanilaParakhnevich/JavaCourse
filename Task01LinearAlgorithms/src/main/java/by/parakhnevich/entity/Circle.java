package by.parakhnevich.entity;

public class Circle {
    private final double radius;

    // TODO: 03.08.2021 hashCode
    public Circle(double radius){
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }


    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }
}
