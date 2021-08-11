package by.parakhnevich.arraysanddecomposition.entity;


public class Array <T extends Number>{
    private T[] array;

    public Array(int begin,int end,Array<T> array){
        this.array = (T[]) new Number[end - begin];
        if (begin > array.getLength() || end > array.getLength() ||
        begin > end || begin < 0) throw new ArrayIndexOutOfBoundsException();
        for (int index = 0 ; index < this.array.length ; index++){
            this.put(index, array.get(begin + index));
        }
    }

    public Array(int number){
        this.array = (T[]) new Number[number];
    }

    public Array(){
        array = null;
    }

    public Array(T[] array){
        copyArray(array);
    }

    public Array(Array<T> array){
        copyArray(array);
    }

    public int getLength(){
        return array.length;
    }

    public void put(int index,T element) throws ArrayIndexOutOfBoundsException{
        try {
            checkingBounds(index);
        }
        catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        array[index] = element;
    }

    public void view(){
        for (T element:array){
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public double getDoubleValue(int index) throws  ArrayIndexOutOfBoundsException{
        return get(index).doubleValue();
    }

    public T get(int index) throws ArrayIndexOutOfBoundsException{
        try {
           checkingBounds(index);
        }
        catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return array[index];
    }

    private void checkingBounds(int index) throws ArrayIndexOutOfBoundsException{
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }



    private void copyArray(T[] array){
        this.array = (T[]) new Number[array.length];
        for (int index = 0 ; index < array.length ; index++){
            this.array[index] = array[index];
        }
    }

    private void copyArray(Array<T> array){
        this.array = (T[]) new Number[array.getLength()];
        for (int index = 0 ; index < array.getLength() ; index++){
            this.array[index] = array.array[index];
        }
    }


}
