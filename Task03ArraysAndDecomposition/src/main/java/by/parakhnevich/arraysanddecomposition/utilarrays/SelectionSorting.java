package by.parakhnevich.arraysanddecomposition.utilarrays;

import by.parakhnevich.arraysanddecomposition.entity.Array;

public class SelectionSorting extends Sorting{
    public void sort(Array<Number> array){
        for (int index1 = 0 ; index1 < array.getLength(); index1++){
            int min_index = index1;
            for (int index2 = index1 + 1 ; index2 < array.getLength(); index2++){
                if (array.getDoubleValue(min_index) > array.getDoubleValue(index2)){
                    min_index = index2;
                }
            }
            if (min_index != index1){
                super.swap(array, min_index, index1);
            }
        }
    }
}
