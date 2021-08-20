package by.parakhnevich.arraysanddecomposition.bean;


import by.parakhnevich.arraysanddecomposition.service.utilarrays.ArrayValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * Class {@code Object} is an entity that abstract
 * array
 * @autor Danila Parakhnevich
 * @version 1.0
 */
public class Array <T extends Number>{
    private T[] arr;
    private static final ArrayValidator validator = new ArrayValidator();
    private final Logger logger = LogManager.getLogger(Array.class.getName());

    //initialization of array of necessary size
    public Array(int number){
        this.arr = (T[]) new Number[number];
    }

    public Array(){
        arr = null;
    }
    //this constructor copy array from list
    //first value is size of new array
    public Array(List<T> list){
        arr = (T[]) new Number[list.get(0).intValue()];
        list.remove(0);
        list.toArray(arr);
    }

    //this constructor copy array from another array
    public Array(T[] array){
        copyArray(array);
    }
    //this constructor copy array from another object Array
    public Array(Array<T> array){
        copyArray(array.arr);
    }

    public int getLength(){
        return arr.length;
    }

    public void put(int index,T element) throws ArrayIndexOutOfBoundsException{
        if (validator.checkingBounds(index,(Array<Number>) this)) {
            arr[index] = element;
            return;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public int getIntValue(int index) throws  ArrayIndexOutOfBoundsException{
        return get(index).intValue();
    }
    //return object from array under necessary index
    public T get(int index) throws ArrayIndexOutOfBoundsException{
        if (validator.checkingBounds(index,(Array<Number>) this)) {
            return arr[index];
        }
        throw new ArrayIndexOutOfBoundsException();
    }


    private void copyArray(T[] array){
        this.arr = (T[]) new Number[array.length];
        this.arr = Arrays.copyOf(array, array.length);
    }

    public String getInfo(){
        StringBuilder result = new StringBuilder();
        for (T element : this.arr) {
            result.append(element).append(" ");
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Array<?> array1 = (Array<?>) o;
        return Arrays.equals(arr, array1.arr);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(arr);
    }

    @Override
    public String toString() {
        return "Array{" +
                "array=" + Arrays.toString(arr) +
                '}';
    }

}
