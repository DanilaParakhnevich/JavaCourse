package by.parakhnevich.et.dao.repository.specification.sortspecification;

import by.parakhnevich.et.dao.repository.specification.Specification;

import java.util.Comparator;

public interface SortSpecification<T> extends Specification<T> {
    public Comparator<T> getComparator();
}
