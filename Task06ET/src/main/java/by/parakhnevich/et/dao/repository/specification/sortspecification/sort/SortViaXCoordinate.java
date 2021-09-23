package by.parakhnevich.et.dao.repository.specification.sortspecification.sort;

public class SortViaXCoordinate extends SortSpheresList {
    public SortViaXCoordinate() {
        super();
        this.comparator = ((o1, o2) ->
                (int)(o1.getPoint().getX() - o2.getPoint().getX()));
    }
}
