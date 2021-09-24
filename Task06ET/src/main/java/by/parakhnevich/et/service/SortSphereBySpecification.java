package by.parakhnevich.et.service;

import by.parakhnevich.et.bean.Sphere;
import by.parakhnevich.et.dao.repository.specification.sortspecification.SortSpecification;

import java.util.List;

public class SortSphereBySpecification {
    public List<Sphere> execute(List<Sphere> list, SortSpecification<Sphere> specification) {
        list.sort(specification.getComparator());
        return list;
    }

}
