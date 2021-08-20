package by.parakhnevich.arraysanddecomposition.service.utilarrays;

import by.parakhnevich.arraysanddecomposition.bean.Array;

/**
 * Insertion sorting
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.service.utilarrays.Sorting
 */
public class InsertionSorting implements Sorting{
    public void sort(Array<Number> array){
        for (int i = 1; i < array.getLength(); i++){
            if (array.getIntValue(i) < array.getIntValue(i-1)){
                swap(array,i,i-1);
                for (int j = i - 1; j - 1 >= 0 ;j--){
                    if (CompareTwoNumbers.compare(array.get(j-1),array.get(j))){
                        swap(array,j,j-1);
                    }
                }
            }
        }
    }
}
