package by.parakhnevich.arraysanddecomposition.service.utilarrays;

import by.parakhnevich.arraysanddecomposition.bean.Array;

/**
 * Cocktail sorting
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.service.utilarrays.Sorting
 */
public class CocktailSorting implements Sorting {
    public void sort(Array<Number> array) {
        int begin = 0;
        int end = array.getLength()-1;
        while (begin <= end) {
            boolean isSwapped = false;
            for (int i = begin ; i + 1 <= end ; i++) {
                if (CompareTwoNumbers.compare(array.get(i), array.get(i+  1))) {
                    swap(array,i,i + 1);
                    isSwapped = true;
                }
            }
            end--;
            for (int i = end ; i-1 >= begin ; i--) {
                if (array.getIntValue(i) < array.getIntValue(i-1)) {
                    swap(array,i,i-1);
                    isSwapped = true;
                }
            }
            begin++;
            if (!isSwapped) {
                return;
            }
        }
    }
}

