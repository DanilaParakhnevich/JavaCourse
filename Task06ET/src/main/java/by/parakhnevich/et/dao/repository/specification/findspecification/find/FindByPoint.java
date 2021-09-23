package by.parakhnevich.et.dao.repository.specification.findspecification.find;

import by.parakhnevich.et.bean.Sphere;
import by.parakhnevich.et.dao.repository.specification.findspecification.FindSpecification;

public class FindByPoint implements FindSpecification<Sphere> {
    double x;
    double y;
    double z;

    public FindByPoint(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean isSatisfiedBy(Sphere circle) {
        return circle.getPoint().getX() == x && circle.getPoint().getY() == y &&
                circle.getPoint().getZ() == z;
    }
}
