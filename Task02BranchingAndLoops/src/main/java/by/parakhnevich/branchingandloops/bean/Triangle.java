package by.parakhnevich.branchingandloops.bean;

import java.util.List;
import java.util.Objects;


/**
 * Class {@code Object} serves as entity for define can a
 * triangle with such angles exist
 * @autor Danila Parakhnevich
 * @version 1.1
 * @see by.parakhnevich.branchingandloops.utilloops.TriangleDefinator
 */
public class Triangle {
    private double angleA;
    private double angleB;
    private double angleC;

    public Triangle(List<Double> list) {
        angleA = list.get(0);
        angleB = list.get(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(triangle.angleA, angleA) == 0 && Double.compare(triangle.angleB, angleB) == 0 && Double.compare(triangle.angleC, angleC) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(angleA, angleB, angleC);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "angleA=" + angleA +
                ", angleB=" + angleB +
                ", angleC=" + angleC +
                '}';
    }

    public double getAngleA() {
        return angleA;
    }

    public void setAngleA(double angleA) {
        this.angleA = angleA;
    }

    public double getAngleB() {
        return angleB;
    }

    public void setAngleB(double angleB) {
        this.angleB = angleB;
    }

    public double getAngleC() {
        return angleC;
    }

    public void setAngleC(double angleC) {
        this.angleC = angleC;
    }
}
