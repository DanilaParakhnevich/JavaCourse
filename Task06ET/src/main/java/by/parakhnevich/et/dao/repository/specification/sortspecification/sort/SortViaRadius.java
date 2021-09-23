package by.parakhnevich.et.dao.repository.specification.sortspecification.sort;

public class SortViaRadius extends SortSpheresList {
    public SortViaRadius() {
        super();
        this.comparator = ((o1, o2) ->
                (int)(o1.getRadius() - o2.getRadius()));
    }
}
