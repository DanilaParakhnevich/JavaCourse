package by.parakhnevich.et.service;

import by.parakhnevich.et.bean.Sphere;
import by.parakhnevich.et.dao.repository.specification.findspecification.FindSpecification;

import java.util.List;

public class FindSphereBySpecification {
    public List<Sphere> execute(List<Sphere> list, FindSpecification<Sphere> specification) {
        list.removeIf((sphere -> !specification.isSatisfiedBy(sphere)));
        return list;
    }
}
