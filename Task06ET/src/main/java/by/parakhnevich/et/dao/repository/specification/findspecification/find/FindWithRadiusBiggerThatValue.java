package by.parakhnevich.et.dao.repository.specification.findspecification.find;

import by.parakhnevich.et.bean.Sphere;
import by.parakhnevich.et.dao.repository.specification.findspecification.FindSpecification;

public class FindWithRadiusBiggerThatValue implements FindSpecification<Sphere> {
    double radius;

    public FindWithRadiusBiggerThatValue(double radius) {
        this.radius = radius;
    }

    @Override
    public boolean isSatisfiedBy(Sphere circle) {
        return circle.getRadius() >= radius;
    }
}
