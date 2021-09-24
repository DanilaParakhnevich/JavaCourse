package by.parakhnevich.et.dao.repository;

public class SphereRegister {
    int id;
    double volume;
    double area;
    boolean isSphereIntersectsAllThePlanes;
    private static int currentId = 0;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public boolean isSphereIntersectsAllThePlanes() {
        return isSphereIntersectsAllThePlanes;
    }

    public void setSphereIntersectsAllThePlanes(boolean sphereIntersectsAllThePlanes) {
        isSphereIntersectsAllThePlanes = sphereIntersectsAllThePlanes;
    }

    public static int getCurrentId() {
        return currentId;
    }

    public static void inc() {
        ++currentId;
    }

    public static void setCurrentId(int currentId) {
        SphereRegister.currentId = currentId;
    }
}
