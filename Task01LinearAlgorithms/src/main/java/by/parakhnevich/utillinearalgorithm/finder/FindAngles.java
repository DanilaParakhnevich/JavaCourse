package by.parakhnevich.utillinearalgorithm.finder;

import by.parakhnevich.entity.Triangle;

import static java.lang.Math.pow;

public final class FindAngles {
    Triangle triangle;

    public FindAngles(Triangle triangle) {
        this.triangle = triangle;
    }

    public double countAngleA() {
        return Math.acos((pow(triangle.getSecond() , 2) + pow(triangle.getThird() , 2) - pow(triangle.getFirst() , 2))
                / (2 * triangle.getSecond() * triangle.getThird()));
    }

    public double countAngleB() {
        return Math.acos((pow(triangle.getFirst(), 2) + pow(triangle.getThird(), 2) -
                pow(triangle.getSecond(), 2)) / (2 * triangle.getFirst() * triangle.getThird()));
    }

    public double countAngleC() {
        return Math.acos((pow(triangle.getSecond(), 2) + pow(triangle.getFirst(), 2) -
                pow(triangle.getThird(), 2)) / (2 * triangle.getSecond() * triangle.getFirst()));
    }

}
