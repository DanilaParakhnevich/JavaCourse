package by.parakhnevich.arraysanddecomposition.utilarrays.creating;

import by.parakhnevich.arraysanddecomposition.entity.Array;

import java.util.Random;

public class CreateRandomArray<T> {
    Random rand = new Random();
    public Array create(int number){
        Array array = new Array(number);
        for (int index = 0 ; index < number ; index++){
            array.put(index,rand.nextDouble()*1000);
        }
        return array;
    }
}
