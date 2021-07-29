package by.parakhnevich.entity;

public class Triangle {
    private final double first;
    private final double second;
    private final double third;

    public Triangle(double first,double second,double third){
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

    public double getThird() {
        return third;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }
}
