package by.parakhnevich.et.dao.repository.specification.sortspecification.sort;

public class SortViaZCoordinate extends SortSpheresList {
    public SortViaZCoordinate() {
        super();
        this.comparator = ((o1, o2) ->
                (int)(o1.getPoint().getZ() - o2.getPoint().getZ()));
    }
}
