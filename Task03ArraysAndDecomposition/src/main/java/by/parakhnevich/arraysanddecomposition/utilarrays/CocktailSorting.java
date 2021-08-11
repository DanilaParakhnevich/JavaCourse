package by.parakhnevich.arraysanddecomposition.utilarrays;

import by.parakhnevich.arraysanddecomposition.entity.Array;

public class CocktailSorting extends Sorting {
    public void sort(Array<Number> array) {
        int begin = 0;
        int end = array.getLength()-1;
        while (begin <= end) {
            for (int index = begin ; index + 1 <= end ; index++) {
                if (array.getDoubleValue(index) > array.getDoubleValue(index+1)) {
                    super.swap(array,index,index+1);
                }
            }
            end--;
            for (int index = end ; index-1 >= begin ; index--) {
                if (array.getDoubleValue(index) < array.getDoubleValue(index-1)) {
                    super.swap(array,index,index-1);
                }
            }
            begin++;
        }
    }
}
