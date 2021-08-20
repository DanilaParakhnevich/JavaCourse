package by.parakhnevich.arraysanddecomposition.utilarrays;

import by.parakhnevich.arraysanddecomposition.bean.Array;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Bubble sorting
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.utilarrays.Sorting
 */
public class BubbleSorting extends Sorting{
    private final Logger logger = LogManager.getLogger(BubbleSorting.class.getName());

    public void sort(Array<Number> array){
        for (int index1 = 0 ;index1 < array.getLength();index1++){
            for (int index2 = 0 ; index2 + 1 < array.getLength() - index1 ; index2++){
                if (CompareTwoNumbers.compare(array.get(index2),array.get(index2 + 1))){
                    try {
                        super.swap(array, index2, index2+1);
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        logger.error(e);
                    }
                }
            }
        }
    }
}
