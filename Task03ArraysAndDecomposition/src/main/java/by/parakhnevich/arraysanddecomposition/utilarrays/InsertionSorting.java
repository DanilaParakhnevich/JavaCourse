package by.parakhnevich.arraysanddecomposition.utilarrays;

import by.parakhnevich.arraysanddecomposition.bean.Array;

/**
 * Insertion sorting
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.utilarrays.Sorting
 */
public class InsertionSorting extends Sorting{
    public void sort(Array<Number> array){
        for (int index1 = 1; index1 < array.getLength(); index1++){
            if (array.getIntValue(index1) < array.getIntValue(index1-1)){
                super.swap(array,index1,index1-1);
                for (int index2 = index1 - 1; index2 - 1 >= 0 ;index2--){
                    if (CompareTwoNumbers.compare(array.get(index2-1),array.get(index2))){
                        super.swap(array,index2,index2-1);
                    }
                }
            }
        }
    }
}
