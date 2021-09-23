package by.parakhnevich.et.dao.repository.specification.findspecification;

import by.parakhnevich.et.dao.repository.specification.Specification;

public interface FindSpecification<T> extends Specification<T> {
    public boolean isSatisfiedBy(T t);
}
