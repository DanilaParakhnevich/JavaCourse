package by.parakhnevich.et.dao.repository;

import by.parakhnevich.et.dao.repository.specification.findspecification.FindSpecification;
import by.parakhnevich.et.dao.repository.specification.sortspecification.SortSpecification;

import java.util.List;

public interface Repository<T>{
    public void add(T t);

    public void remove(long id);

    public List<T> getAll();

    public List<T> findBySpecification(FindSpecification<T> specification);

    public List<T> sortBySpecification(SortSpecification<T> specification);

    public void update(T oldT, T newT);
}
