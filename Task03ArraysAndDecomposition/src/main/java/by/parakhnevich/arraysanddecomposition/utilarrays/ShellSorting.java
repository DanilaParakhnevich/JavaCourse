package by.parakhnevich.arraysanddecomposition.utilarrays;

import by.parakhnevich.arraysanddecomposition.entity.Array;
import by.parakhnevich.arraysanddecomposition.utilarrays.Sorting;

public class ShellSorting extends Sorting {
    public void sort(Array array){
        for (int step = array.getLength() / 2 ; step > 0 ; step /= 2) {
            for (int index1 = step ; index1 < array.getLength() ; index1++) {
                for (int index2 = index1 - step ; index2 >= 0; index2-=step) {
                    if (array.getDoubleValue(index2)
                            > array.getDoubleValue(index2 + step)) {
                        super.swap(array, index2, index2 + step);
                    }
                }
            }
        }
    }
}
