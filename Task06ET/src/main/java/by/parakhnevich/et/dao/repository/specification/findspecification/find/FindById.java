package by.parakhnevich.et.dao.repository.specification.findspecification.find;

import by.parakhnevich.et.bean.Sphere;
import by.parakhnevich.et.dao.repository.specification.findspecification.FindSpecification;

public class FindById implements FindSpecification<Sphere> {
    long id;

    public FindById(long id) {
        this.id = id;
    }

    @Override
    public boolean isSatisfiedBy(Sphere circle) {
        return this.id == circle.getId();
    }
}
