package by.parakhnevich.et.bean;

import java.util.ArrayList;
import java.util.List;

public class Sphere {
    private final double radius;
    Point point;
    long id;

    public static class Point {
        List<Double> coordinates;

        Point(double x, double y, double z) {
            coordinates = new ArrayList<>();
            this.coordinates.add(x);
            this.coordinates.add(y);
            this.coordinates.add(z);
        }

        Point(List<Double> coordinates) {
            this.coordinates = new ArrayList<>(coordinates);
        }

        public double getX() {
            return this.coordinates.get(0);
        }

        public double getY() {
            return this.coordinates.get(1);
        }

        public double getZ() {return this.coordinates.get(2);}

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point1 = (Point) o;
            return getX() == point1.getX() && getY() == point1.getY();
        }

        @Override
        public int hashCode() {
            return 31 * (int)getX() + (int)getY();
        }

        @Override
        public String toString() {
            return "Point{" +
                    "coordinates=" + coordinates.toString() +
                    '}';
        }
    }

    public Sphere(List<Double> list) {
        this.radius = list.remove(0);
        this.point = new Point(list);
    }

    public Sphere(double radius, double x, double y, double z) {
        this.radius = radius;
        point = new Point(x, y, z);
    }

    public double getRadius() {
        return this.radius;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Point getPoint() {
        return this.point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sphere circle = (Sphere) o;
        return this.radius == circle.radius && circle.point.equals(this.point);
    }

    @Override
    public int hashCode() {
        return (int)this.radius * 31 + point.hashCode();
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                ", point=" + point +
                ", id=" + id +
                '}';
    }


}
