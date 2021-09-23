package by.parakhnevich.et.dao.repository.specification.sortspecification.sort;

public class SortViaYCoordinate extends SortSpheresList {
    public SortViaYCoordinate() {
        super();
        this.comparator = ((o1, o2) ->
                (int)(o1.getPoint().getY() - o2.getPoint().getY()));
    }
}
