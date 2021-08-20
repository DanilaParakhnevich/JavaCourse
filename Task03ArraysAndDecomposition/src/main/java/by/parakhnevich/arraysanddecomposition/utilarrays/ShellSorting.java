package by.parakhnevich.arraysanddecomposition.utilarrays;

import by.parakhnevich.arraysanddecomposition.bean.Array;

/**
 * Shell sorting
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.utilarrays.Sorting
 */
public class ShellSorting extends Sorting {
    public void sort(Array<Number> array){
        for (int step = array.getLength() / 2 ; step > 0 ; step /= 2) {
            for (int index1 = step ; index1 < array.getLength() ; index1++) {
                for (int index2 = index1 - step ; index2 >= 0; index2-=step) {
                    if (CompareTwoNumbers.compare(array.get(index2),array.get(index2 + step))) {
                        super.swap(array, index2, index2 + step);
                    }
                }
            }
        }
    }
}
