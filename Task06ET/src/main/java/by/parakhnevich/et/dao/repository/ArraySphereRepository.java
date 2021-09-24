package by.parakhnevich.et.dao.repository;

import by.parakhnevich.et.bean.Sphere;
import by.parakhnevich.et.dao.CloudscapeSphereDAO;
import by.parakhnevich.et.dao.CloudscapeDAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArraySphereRepository implements Repository<Sphere>{
    Logger logger = LogManager.getLogger(ArraySphereRepository.class);
    List<Sphere> storage;
    private static ArraySphereRepository instance = null;
    CloudscapeDAOFactory factory = new CloudscapeDAOFactory();

    private ArraySphereRepository() {
    }

    public void load(String name) {
        try {
            storage = new CloudscapeSphereDAO().getAll(name);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    @Override
    public void add(Sphere circle) {
        storage.add(circle);
    }

    @Override
    public List<Sphere> getAll() {
        return new ArrayList<>(storage);
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
}
