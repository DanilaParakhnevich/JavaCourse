package by.parakhnevich.arraysanddecomposition.service.utilarrays;

import by.parakhnevich.arraysanddecomposition.bean.Array;

/**
 * Quick sorting
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.service.utilarrays.Sorting
 */
public class QuickSorting implements Sorting{
    public void sort(Array<Number> array, int left, int right) {
        if (array.getLength() == 0 || left >= right) {
            return;
        }
        int i = left;
        int j = right;
        int middle = left + (right - left) / 2;
        Number pivot = array.get(middle);
        while (i <= j) {
            while (CompareTwoNumbers.compare(pivot,array.get(i))) {
                i++;
            }
            while (CompareTwoNumbers.compare(array.get(j), pivot)) {
                j--;
            }
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        if (left < j)
            sort(array, left, j);
        if (right > i)
            sort(array, i, right);
    }

    @Override
    public void sort(Array<Number> array) {
    //its unnecessary
    }
}
