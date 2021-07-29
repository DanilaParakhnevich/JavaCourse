package by.parakhnevich.utillinearalgorithm.calculator;

import by.parakhnevich.entity.RegularTriangle;

public class RegularTriangleCalc {
    RegularTriangle triangle;

    public RegularTriangleCalc(RegularTriangle triangle) {
     this.triangle = triangle;
    }

    public double countInscribedRadius() {
        return countCircumscribedRadius() / 2;
    }

    public double countCircumscribedRadius() {
        return triangle.getSide() / Math.sqrt(3);
    }

    public double countHeight() {
        return triangle.getSide() * Math.sqrt(3) / 2;
    }

    public double countArea() {
        return triangle.getSide() * triangle.getSide() * Math.sqrt(3) / 4;
    }
}
