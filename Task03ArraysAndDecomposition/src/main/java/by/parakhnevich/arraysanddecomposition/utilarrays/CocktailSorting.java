package by.parakhnevich.arraysanddecomposition.utilarrays;

import by.parakhnevich.arraysanddecomposition.bean.Array;

/**
 * Cocktail sorting
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.utilarrays.Sorting
 */
public class CocktailSorting extends Sorting {
    public void sort(Array<Number> array) {
        int begin = 0;
        int end = array.getLength()-1;
        while (begin <= end) {
            for (int index = begin ; index + 1 <= end ; index++) {
                if (CompareTwoNumbers.compare(array.get(index), array.get(index+  1))) {
                    super.swap(array,index,index + 1);
                }
            }
            end--;
            for (int index = end ; index-1 >= begin ; index--) {
                if (array.getIntValue(index) < array.getIntValue(index-1)) {
                    super.swap(array,index,index-1);
                }
            }
            begin++;
        }
    }
}
