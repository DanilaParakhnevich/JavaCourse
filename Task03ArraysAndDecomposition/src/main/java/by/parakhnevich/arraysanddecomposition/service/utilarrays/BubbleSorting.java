package by.parakhnevich.arraysanddecomposition.service.utilarrays;

import by.parakhnevich.arraysanddecomposition.bean.Array;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Bubble sorting
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.service.utilarrays.Sorting
 */
public class BubbleSorting implements Sorting{
    private final Logger logger = LogManager.getLogger(BubbleSorting.class.getName());

    public void sort(Array<Number> array){
        for (int i = 0 ;i < array.getLength();i++){
            for (int j = 0 ; j + 1 < array.getLength() - i ; ++j){
                if (CompareTwoNumbers.compare(array.get(j),array.get(j + 1))){
                    swap(array, j, j+1);
                }
            }
        }
    }
}
