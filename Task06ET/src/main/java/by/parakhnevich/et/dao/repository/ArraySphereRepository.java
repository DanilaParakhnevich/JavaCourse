package by.parakhnevich.et.dao.repository;

import by.parakhnevich.et.bean.Sphere;
import by.parakhnevich.et.dao.CloudscapeSphereDAO;
import by.parakhnevich.et.dao.CloudscapeDAOFactory;
import by.parakhnevich.et.dao.repository.specification.findspecification.FindSpecification;
import by.parakhnevich.et.dao.repository.specification.sortspecification.SortSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class ArraySphereRepository implements Repository<Sphere>{
    Logger logger = LogManager.getLogger(ArraySphereRepository.class);
    List<Sphere> storage;
    private static ArraySphereRepository instance = null;
    private static long actualId = 0;
    CloudscapeDAOFactory factory = new CloudscapeDAOFactory();

    private ArraySphereRepository() {
    }

    public void load(String name) {
        try {
            resetActualId();
            storage = new CloudscapeSphereDAO().getAll(name);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    @Override
    public void add(Sphere circle) {
        circle.setId(actualId);
        storage.add(circle);
        incActualId();
    }

    @Override
    public List<Sphere> getAll() {
        return storage;
    }

    @Override
    public List<Sphere> findBySpecification(FindSpecification<Sphere> specification) {
        storage.removeIf(sphere -> !specification.isSatisfiedBy(sphere));
        return storage;
    }

    @Override
    public List<Sphere> sortBySpecification(SortSpecification<Sphere> specification) {
        storage.sort(specification.getComparator());
        return storage;
    }

    @Override
    public void update(Sphere oldT, Sphere newT) {
        if (storage.contains(oldT)) {
            newT.setId(newT.getId());
            int oldPersonIndex = storage.indexOf(oldT);
            storage.set(oldPersonIndex, newT);
        } else {
            logger.error("Sphere not found");
        }
    }

    @Override
    public void remove(long id) {
        for (Sphere sphere1 : storage) {
            if (sphere1.getId() == id) {
                storage.remove(sphere1);
                break;
            }
        }
    }

    public void save() {
        factory.getCircleDAO().save(storage);
    }

    public static ArraySphereRepository getInstance() {
        if (instance == null) {
            instance = new ArraySphereRepository();
        }
        return instance;
    }

    public static long getAndIncActualId() {
        return actualId++;
    }

    public static long getAndDecActualId() {
        return actualId--;
    }

    public static void incActualId() {
        ++actualId;
    }

    public static void decActualId() {
        --actualId;
    }

    public static long getActualId() {
        return actualId;
    }

    public static void resetActualId() {
        actualId = 0;
    }
}
