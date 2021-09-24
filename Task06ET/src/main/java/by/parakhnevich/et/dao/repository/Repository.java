package by.parakhnevich.et.dao.repository;


import java.util.List;

public interface Repository<T>{
    public void add(T t);

    public void remove(long id);

    public List<T> getAll();

    public void update(T oldT, T newT);
}
