package by.parakhnevich.arraysanddecomposition.utilarrays;

import by.parakhnevich.arraysanddecomposition.entity.Array;

public class InsertionSorting extends Sorting{
    public void sort(Array<Number> array){
        for (int index1 = 1; index1 < array.getLength(); index1++){
            if (array.getDoubleValue(index1) < array.getDoubleValue(index1-1)){
                super.swap(array,index1,index1-1);
                for (int index2 = index1 - 1; index2 - 1 >= 0 ;index2--){
                    if (array.getDoubleValue(index2-1) > array.getDoubleValue(index2)){
                        super.swap(array,index2,index2-1);
                    }
                }
            }
        }
    }
}
