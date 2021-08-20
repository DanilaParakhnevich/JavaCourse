package by.parakhnevich.arraysanddecomposition.service.utilarrays;

import by.parakhnevich.arraysanddecomposition.bean.Array;

/**
 * Class {@code Object} is class from which are inherited
 * classes for sorting arrays
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.service.utilarrays <--there are all the classes for sorting
 */
public interface Sorting {
    public void sort(Array<Number> array);
        //this method swap 2 elements in Array
    default void swap(Array<Number> array, int index1, int index2) throws ArrayIndexOutOfBoundsException{
        Number number = array.get(index1);
        array.put(index1, array.get(index2));
        array.put(index2, number);
    }
}
