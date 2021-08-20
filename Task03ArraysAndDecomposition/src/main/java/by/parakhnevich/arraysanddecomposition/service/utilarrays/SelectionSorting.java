package by.parakhnevich.arraysanddecomposition.service.utilarrays;

import by.parakhnevich.arraysanddecomposition.bean.Array;

/**
 *Selection sorting
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.service.utilarrays.Sorting
 */
public class SelectionSorting implements Sorting{
    public void sort(Array<Number> array){
        for (int i = 0 ; i < array.getLength(); i++){
            int minIndex = i;
            for (int j = i + 1 ; j < array.getLength(); j++){
                if (CompareTwoNumbers.compare(array.get(minIndex),array.get(j))){
                    minIndex = j;
                }
            }
            if (minIndex != i){
                swap(array, minIndex, i);
            }
        }
    }
}
