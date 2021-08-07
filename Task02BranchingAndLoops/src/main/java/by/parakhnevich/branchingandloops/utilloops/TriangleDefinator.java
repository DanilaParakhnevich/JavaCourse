package by.parakhnevich.branchingandloops.utilloops;

import by.parakhnevich.branchingandloops.bean.Triangle;


/**
 * Class {@code Object} define could exist triangle
 * with 2 certain angles
 * TASK04
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.branchingandloops.bean.Triangle
 */
public class TriangleDefinator {
    Triangle triangle;

    public TriangleDefinator(Triangle triangle){
        this.triangle = triangle;
    }

    public boolean isTriangle(){
        return triangle.getAngleA() + triangle.getAngleB() < 180 && triangle.getAngleA() > 0
                && triangle.getAngleB() > 0;
    }

    public boolean isRectangular(){
        return (triangle.getAngleA() == 90 || triangle.getAngleB() == 90
                || (180 - (triangle.getAngleA() + triangle.getAngleB())) == 90) && isTriangle();
    }

}
