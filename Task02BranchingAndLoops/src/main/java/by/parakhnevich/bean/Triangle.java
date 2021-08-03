package by.parakhnevich.bean;

import java.util.List;

public class Triangle {
    private double angleA;
    private double angleB;
    private double angleC;

    public Triangle(List<Number> list) {
        angleA = list.get(0).doubleValue();
        angleB = list.get(1).doubleValue();
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
