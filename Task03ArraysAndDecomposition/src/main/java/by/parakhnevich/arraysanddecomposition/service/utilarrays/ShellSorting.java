package by.parakhnevich.arraysanddecomposition.service.utilarrays;

import by.parakhnevich.arraysanddecomposition.bean.Array;

/**
 * Shell sorting
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.service.utilarrays.Sorting
 */
public class ShellSorting implements Sorting {
    public void sort(Array<Number> array){
        for (int step = array.getLength() / 2 ; step > 0 ; step /= 2) {
            for (int i = step ; i < array.getLength() ; i++) {
                for (int j = i - step ; j >= 0; j-=step) {
                    if (CompareTwoNumbers.compare(array.get(j),array.get(j + step))) {
                         swap(array, j, j + step);
                    }
                }
            }
        }
    }
}
