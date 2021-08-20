package by.parakhnevich.arraysanddecomposition.service.utilarrays;

import by.parakhnevich.arraysanddecomposition.bean.Array;

/**
 * Merge sorting
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.service.utilarrays.Sorting
 */
public class MergeSorting implements Sorting{
    public void sort(Array<Number> array) {
        int middle = array.getLength()/2;
        sort(0, middle, array);
        sort(middle, array.getLength(), array);
        sort(array, middle);
    }

    private void sort(Array<Number> array, int middle){
        int firstIndex = 0;
        int secondIndex = middle;
        Array<Number> result = new Array<>(array);
        for (int i = 0 ; i < array.getLength() ; ++i){
            if (secondIndex >= array.getLength() || firstIndex < middle &&
                    CompareTwoNumbers.compare(result.get(secondIndex), result.get(firstIndex)))
            {
                array.put (i, result.get(firstIndex));
                firstIndex++;
            }
            else {
                array.put (i, result.get(secondIndex));
                secondIndex++;
            }
        }
    }

    private void sort(int from,int to, Array<Number> array){
        for (int i = from; i < to; ++i) {
            for (int j = i + 1; j < to; ++j){
                if (CompareTwoNumbers.compare(array.get(i), array.get(j))) {
                    swap(array, i, j);
                }
            }
        }
    }
}
