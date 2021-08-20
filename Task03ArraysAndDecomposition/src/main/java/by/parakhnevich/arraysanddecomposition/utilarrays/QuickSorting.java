package by.parakhnevich.arraysanddecomposition.utilarrays;

import by.parakhnevich.arraysanddecomposition.bean.Array;

/**
 * Quick sorting
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.utilarrays.Sorting
 */
public class QuickSorting extends Sorting{
    public void sort(Array<Number> array, int left, int right) {
        if (array.getLength() == 0 || left >= right)
            return;
        int index1 = left;
        int index2 = right;
        int middle = left + (right - left) / 2;
        Number pivot = array.get(middle);
        while (index1 <= index2) {
            while (CompareTwoNumbers.compare(pivot,array.get(index1))) {
                index1++;
            }
            while (CompareTwoNumbers.compare(array.get(index2), pivot)) {
                index2--;
            }
            if (index1 <= index2) {
                super.swap(array, index1, index2);
                index1++;
                index2--;
            }
        }
        if (left < index2)
            sort(array, left, index2);
        if (right > index1)
            sort(array, index1, right);
    }
}
