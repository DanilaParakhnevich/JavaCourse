package by.parakhnevich.utilloops;

import by.parakhnevich.bean.Triangle;

public class TriangleDefinator {
    Triangle triangle;

    public TriangleDefinator(Triangle triangle){
        this.triangle = triangle;
    }

    public boolean isTriangle(){
        return triangle.getAngleA() + triangle.getAngleB() < 180 && triangle.getAngleA() != 0
                && triangle.getAngleB() != 0;
    }

    public boolean isRectangular(){
        return (triangle.getAngleA() == 90 || triangle.getAngleB() == 90
                || (180 - triangle.getAngleA() + triangle.getAngleB()) == 90) && isTriangle();
    }

}
