package by.parakhnevich.et.dao.repository.specification.sortspecification.sort;

import by.parakhnevich.et.bean.Sphere;
import by.parakhnevich.et.dao.repository.specification.sortspecification.SortSpecification;

import java.util.Comparator;

public class SortSpheresList implements SortSpecification<Sphere> {
    Comparator<Sphere> comparator;
    protected SortSpheresList() {}

    @Override
    public Comparator<Sphere> getComparator() {
        return comparator;
    }
}
