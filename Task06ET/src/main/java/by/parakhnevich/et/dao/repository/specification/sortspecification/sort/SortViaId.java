package by.parakhnevich.et.dao.repository.specification.sortspecification.sort;

public class SortViaId extends SortSpheresList {
    public SortViaId() {
        super();
        this.comparator = (o1, o2) -> (int)(o1.getId() - o2.getId());
    }
}
