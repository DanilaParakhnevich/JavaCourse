package by.parakhnevich.arraysanddecomposition.utilarrays;

import by.parakhnevich.arraysanddecomposition.entity.Array;

public class BubbleSorting extends Sorting{
    public void sort(Array<Number> array){
        for (int index1 = 0 ;index1 < array.getLength();index1++){
            for (int index2 = 0 ; index2 + 1 < array.getLength() - index1 ; index2++){
                if (array.getDoubleValue(index2) > array.getDoubleValue(index2 + 1)){
                    try {
                        super.swap(array, index2, index2+1);
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
