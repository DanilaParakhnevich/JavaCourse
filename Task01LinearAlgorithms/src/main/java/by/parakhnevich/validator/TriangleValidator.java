package by.parakhnevich.validator;

import by.parakhnevich.entity.Triangle;

public class TriangleValidator {
    private Triangle triangle;

    public TriangleValidator(Triangle triangle) {
        this.triangle = triangle;
    }

    public boolean isValid(){
        double a = triangle.getFirst();
        double b = triangle.getSecond();
        double c = triangle.getThird();
        return (a + b > c && a + c > b && b + c > a && a > 0 && b > 0 && c > 0);
    }
}
